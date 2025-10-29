package br.com.xmacedo.examstudylibraryguideai.config;

import br.com.xmacedo.examstudylibraryguideai.model.Exam;
import br.com.xmacedo.examstudylibraryguideai.model.Topic;
import br.com.xmacedo.examstudylibraryguideai.service.ExamService;
import br.com.xmacedo.examstudylibraryguideai.service.TopicService;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DocumentConfig {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DocumentConfig.class);

    private final VectorStore vectorStore;
    private final ExamService examService;
    private final TopicService topicService;
    private final ObjectMapper mapper;

    @Value("${app.documents.patterns:*.md}")
    private static final String PATTERN_FOR_DOCUMENTS = "*.md";

    @Value("${app.exams.path:/data/exam.json}")
    private static final String PATH_FOR_EXAMS = "/data/exam.json";

    @Value("${app.documents.path:./src/main/resources/documents}")
    private static String PATH_FOR_DOCUMENTS = "./src/main/resources/documents/";

    public DocumentConfig(VectorStore vectorStore, ExamService examService, TopicService topicService, ObjectMapper mapper) {
        this.vectorStore = vectorStore;
        this.examService = examService;
        this.topicService = topicService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void loadDocuments() {
        try {
            log.info("### Start DataLoader ### ");
            loadExams();
            loadStudyDocuments();

            log.info("### END DataLoader ### ");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load study documents", e);
        }
    }

    private void loadExams() throws IOException {
        log.info("--> Exams Loading...");
        InputStream inputStream = getClass().getResourceAsStream(PATH_FOR_EXAMS);

        List<Exam> examList = mapper.readValue(inputStream, new TypeReference<>() {
        });
        examService.addAll(examList);

        log.info("--> Exams loaded: {}", examList.size());
    }

    private void loadStudyDocuments() throws IOException {
        log.info("--> Loading study documents...");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:" + PATH_FOR_DOCUMENTS + PATTERN_FOR_DOCUMENTS);

        List<Document> documents = new ArrayList<>();
        List<Topic> topicList = new ArrayList<>();

        for (Resource resource : resources) {
            if (resource.exists() && resource.isReadable()) {
                TextReader textReader = new TextReader(resource);
                List<Document> docs = textReader.get();

                // Add metadata to identify the source file
                for (Document doc : docs) {
                    log.info("Document: {}", resource.getFilename());

                    String[] splitFileName = resource.getFilename().split("-");

                    String examId = splitFileName[0];
                    String topicName = splitFileName[1].replace("_", " ")
                            .replace(".md", "");

                    Exam examById = examService.getById(Long.parseLong(examId));
                    String companyName = examById.getCompany();
                    topicList.add(buildNewTopic(examById, topicName));

                    doc.getMetadata().put("source", resource.getFilename());
                    doc.getMetadata().put("type", companyName + "-study-material");
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
            log.info("Loaded {} document chunks from {} study files", splitDocuments.size(), resources.length);

            topicService.addAll(topicList);
            log.info("--> Topics loaded: {}", topicList.size());
        } else {
            log.info("No study documents found in path: {}", PATH_FOR_DOCUMENTS);
        }
    }

    private Topic buildNewTopic(Exam exam, String topicName) {
        return new Topic(exam, topicName);
    }
}
