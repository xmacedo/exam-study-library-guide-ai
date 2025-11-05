package br.com.xmacedo.examstudylibraryguideai.controller;

import br.com.xmacedo.examstudylibraryguideai.model.Exam;
import br.com.xmacedo.examstudylibraryguideai.service.ExamService;
import br.com.xmacedo.examstudylibraryguideai.service.StudyService;
import br.com.xmacedo.examstudylibraryguideai.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class StudyController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudyController.class);

    private final StudyService studyService;
    private final TopicService topicService;
    private final ExamService examService;

    public StudyController(StudyService studyService, TopicService topicService, ExamService examService) {
        this.studyService = studyService;
        this.topicService = topicService;
        this.examService = examService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<String> topics = topicService.getAvailableTopics();
        model.addAttribute("topics", topics);

        log.info("--> Available topics: {}", topics.size());

        List<String> exams = examService.getAll().stream().map(Exam::getCompany).toList();
        model.addAttribute("exams", exams);

        log.info("--> Available exams: {}", exams.size());

        return "index";
    }

    @PostMapping("/ask")
    @ResponseBody
    public String askQuestion(@RequestParam String question, @RequestParam Integer examId) {
        log.info("Question received: {}", question);
        try {
            return studyService.askQuestion(question, examId);
        } catch (Exception e) {
            return "Error processing your question: " + e.getMessage();
        }
    }

}
