package br.com.xmacedo.examstudylibraryguideai.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {
    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        var builder = SimpleVectorStore.builder(embeddingModel);
        return builder.build();
    }
}
