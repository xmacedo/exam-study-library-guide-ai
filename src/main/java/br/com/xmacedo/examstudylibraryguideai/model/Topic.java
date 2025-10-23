package br.com.xmacedo.examstudylibraryguideai.model;

import lombok.Data;

@Data
public class Topic {
    private Long id;
    private Exam exam;
    private String topic;
}
