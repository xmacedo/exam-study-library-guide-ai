package br.com.xmacedo.examstudylibraryguideai.service;

import br.com.xmacedo.examstudylibraryguideai.model.Topic;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TopicService {
    private final List<Topic> topicList = new ArrayList<>();

    public List<Topic> getAll() {
        return topicList;
    }

    public void addAll(List<Topic> topics) {
        topicList.addAll(topics);
    }

    public Topic getById(Long id) {
        return topicList.stream()
                .filter(topic -> Objects.equals(topic.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAvailableTopics() {
        return topicList
                .stream()
                .map(topic -> topic.getTopic())
                .toList();
    }

}
