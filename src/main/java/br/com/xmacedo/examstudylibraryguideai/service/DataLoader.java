package br.com.xmacedo.examstudylibraryguideai.service;

import br.com.xmacedo.examstudylibraryguideai.model.Exam;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ExamService examService;

    public DataLoader(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("### DataLoader ### ");

        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/exam.json");

        List<Exam> examList = mapper.readValue(inputStream, new TypeReference<>() {});
        examService.addAll(examList);

        System.out.println("--> Exams loaded: " + examList.size());
    }
}
