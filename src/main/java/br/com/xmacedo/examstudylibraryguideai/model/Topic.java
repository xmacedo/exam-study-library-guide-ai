package br.com.xmacedo.examstudylibraryguideai.model;

import java.util.UUID;

public class Topic {
    private UUID id;
    private Exam exam;
    private String topic;

    public Topic(Exam exam, String topic) {
        this.id = UUID.randomUUID();
        this.exam = exam;
        this.topic = topic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
