package br.com.xmacedo.examstudylibraryguideai.config;

import br.com.xmacedo.examstudylibraryguideai.service.ExamService;
import br.com.xmacedo.examstudylibraryguideai.service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

@Configuration
public class DocumentConfig {

    private final VectorStore vectorStore;
    private final ExamService examService;
    private final TopicService topicService;
    private final ObjectMapper mapper;

    //    @Value("${app.documents.patterns:*.md}")
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

    }
}
