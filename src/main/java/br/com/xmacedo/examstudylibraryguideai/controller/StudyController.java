package br.com.xmacedo.examstudylibraryguideai.controller;

import br.com.xmacedo.examstudylibraryguideai.service.StudyService;
import org.springframework.ai.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> topics = studyService.getAvailableTopics();
        //model.("topics", topics);
        return "index";
    }

    @PostMapping("/ask")
    @ResponseBody
    public String askQuestion(@RequestParam String question) {
        try {
            return studyService.askQuestion(question);
        } catch (Exception e) {
            return "Error processing your question: " + e.getMessage();
        }
    }

    @GetMapping("/api/topics")
    @ResponseBody
    public List<String> getTopics() {
        return studyService.getAvailableTopics();
    }
}
