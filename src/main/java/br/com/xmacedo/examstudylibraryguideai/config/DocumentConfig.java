package br.com.xmacedo.examstudylibraryguideai.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

@Configuration
public class DocumentConfig {

    private final VectorStore vectorStore;

    public DocumentConfig(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
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
