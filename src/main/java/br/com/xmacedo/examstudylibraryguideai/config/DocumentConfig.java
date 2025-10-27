package br.com.xmacedo.examstudylibraryguideai.config;

import br.com.xmacedo.examstudylibraryguideai.service.ExamService;
import br.com.xmacedo.examstudylibraryguideai.service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DocumentConfig {

    private final VectorStore vectorStore;
    private final ExamService examService;
    private final TopicService topicService;
    private final ObjectMapper mapper;

    @Value("${app.documents.patterns:*.md}")
    private static final String PATTERN_FOR_DOCUMENTS = "*.md";

    @Value("${app.exams.path:/data/exam.json}")
    private static final String PATH_FOR_EXAMS = "/data/exam.json";

    @Value("${app.documents.path:../}")
    private static String PATH_FOR_DOCUMENTS;

    public DocumentConfig(VectorStore vectorStore, ExamService examService, TopicService topicService, ObjectMapper mapper) {
        this.vectorStore = vectorStore;
        this.examService = examService;
        this.topicService = topicService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void loadDocuments() {
        try {
            loadAwsStudyDocuments();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load AWS study documents", e);
        }
    }

    private void loadAwsStudyDocuments() throws IOException{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:" + PATH_FOR_DOCUMENTS + PATTERN_FOR_DOCUMENTS);

        List<Document> documents = new ArrayList<>();

        for (Resource resource : resources) {
            if (resource.exists() && resource.isReadable()) {
                TextReader textReader = new TextReader(resource);
                List<Document> docs = textReader.get();

                // Add metadata to identify the source file
                for (Document doc : docs) {
                    doc.getMetadata().put("source", resource.getFilename());
                    doc.getMetadata().put("type", "aws-study-material");
                }

                documents.addAll(docs);
            }
        }

        if (!documents.isEmpty()) {
            // Split documents into chunks for better retrieval
            TokenTextSplitter splitter = new TokenTextSplitter();
            List<Document> splitDocuments = splitter.apply(documents);

            // Add documents to vector store
            vectorStore.add(splitDocuments);

            System.out.println("Loaded " + splitDocuments.size() + " document chunks from " + resources.length + " study files");
        } else {
            System.out.println("No study documents found in path: " + PATH_FOR_DOCUMENTS);
        }
    }
}
