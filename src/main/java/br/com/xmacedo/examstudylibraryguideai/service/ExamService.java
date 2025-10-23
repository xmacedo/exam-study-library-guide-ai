package br.com.xmacedo.examstudylibraryguideai.service;

import br.com.xmacedo.examstudylibraryguideai.model.Exam;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExamService {
    private final List<Exam> examList = new ArrayList<>();

    public List<Exam> getAll() {
        return examList;
    }

    public void addAll(List<Exam> exams) {
        examList.clear();
        examList.addAll(exams);
    }

    public Exam getById(Long id) {
        return examList.stream()
                .filter(exam -> Objects.equals(exam.getId(), id))
                .findFirst()
                .orElse(null);
    }

}
