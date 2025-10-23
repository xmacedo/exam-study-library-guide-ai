package br.com.xmacedo.examstudylibraryguideai.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Topic {
    private UUID id;
    private Exam exam;
    private String topic;

    public Topic(Exam exam, String topic) {
        this.id = UUID.randomUUID();
        this.exam = exam;
        this.topic = topic;
    }
}
