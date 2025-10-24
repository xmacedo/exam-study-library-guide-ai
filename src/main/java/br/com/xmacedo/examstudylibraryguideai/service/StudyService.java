package br.com.xmacedo.examstudylibraryguideai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    private static final String SYSTEM_PROMPT = """
            You are an AWS Solutions Architect Associate exam expert assistant. 
            You help students prepare for the AWS SAA-C03 certification exam.
            
            Use the provided AWS study materials to answer questions accurately and comprehensively.
            Focus on:
            - Key AWS services and their use cases
            - Best practices and architectural patterns
            - Common exam scenarios and decision factors
            - Cost optimization strategies
            - Security and compliance considerations
            
            If the question is not covered in the study materials, clearly state that and provide general guidance.
            Always structure your answers clearly with bullet points or numbered lists when appropriate.
            """;

    private static final String RAG_PROMPT_TEMPLATE = """
            {system_prompt}
            
            Based on the following AWS study materials:
            {documents}
            
            Question: {question}
            
            Please provide a comprehensive answer based on the study materials above.
            """;

    public StudyService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public String askQuestion(String question) {
        // Retrieve relevant documents
        List<Document> relevantDocs = vectorStore.similaritySearch(
                SearchRequest.builder().query(question).topK(5).build()
        );

        // Combine document content
        String documents = relevantDocs.stream()
                .map(doc -> "Source: " + doc.getMetadata().get("source") + "\n" + doc.getFormattedContent())
                .collect(Collectors.joining("\n\n---\n\n"));

        // Create prompt with context
        PromptTemplate promptTemplate = new PromptTemplate(RAG_PROMPT_TEMPLATE);
        Prompt prompt = promptTemplate.create(Map.of(
                "system_prompt", SYSTEM_PROMPT,
                "documents", documents.isEmpty() ? "No relevant study materials found." : documents,
                "question", question
        ));

        // Get response from AI
        return chatClient.prompt(prompt).call().content();
    }
}
