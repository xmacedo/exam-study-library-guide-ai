package br.com.xmacedo.examstudylibraryguideai.controller;

import br.com.xmacedo.examstudylibraryguideai.model.Topic;
import br.com.xmacedo.examstudylibraryguideai.service.TopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> findAll() {
        return topicService.getAll();
    }

    @GetMapping("/{id}")
    public Topic findById(@PathVariable Long id) {
        return topicService.getById(id);
    }

    @GetMapping("/available")
    public List<String> findAvailableTopics() {
        return topicService.getAvailableTopics();
    }
}
