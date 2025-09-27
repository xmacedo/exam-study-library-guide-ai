package br.com.xmacedo.examstudylibraryguideai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

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
                SearchRequest.query(question).withTopK(5)
        );

        // Combine document content
        String documents = relevantDocs.stream()
                .map(doc -> "Source: " + doc.getMetadata().get("source") + "\n" + doc.getContent())
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

    public List<String> getAvailableTopics() {
        return List.of(
                "Compute Services (EC2, Lambda, ECS, EKS)",
                "Storage Services (S3, EBS, EFS, FSx)",
                "Database Services (RDS, DynamoDB, ElastiCache)",
                "Networking & VPC",
                "Security & Identity (IAM, KMS, Cognito)",
                "Application Integration (SQS, SNS, EventBridge)",
                "Monitoring & Logging (CloudWatch, CloudTrail)",
                "Management & Governance (CloudFormation, Organizations)",
                "Content Delivery (CloudFront, Route 53)",
                "Migration & Hybrid Solutions",
                "Analytics & Machine Learning",
                "Cost Optimization",
                "Well-Architected Framework",
                "Disaster Recovery",
                "Exam Tips & Scenarios"
        );
    }
}
