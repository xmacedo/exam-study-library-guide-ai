package br.com.xmacedo.examstudylibraryguideai.service;

import br.com.xmacedo.examstudylibraryguideai.model.Exam;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Log4j2
public class DataLoader implements CommandLineRunner {

    private final ExamService examService;
    private final ObjectMapper mapper;

    private static final String PATH_FOR_EXAMS = "/data/exam.json";
    private static final String PATH_FOR_DOCUMENTS = "/documents";

    public DataLoader(ExamService examService) {
        this.examService = examService;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws IOException {
        log.info("### DataLoader ### ");

        //
        loadExams();

        loadTopicDocuments();

        //Como ler os arquivos md
        //  Ler por processamento, carregar em memória e incluir
        //Pattern of documents
        //2-Compute-Services.md ({ID of Exam}-{Title of Topic})
        //tópicos por n

        //TODO: Load all documents from markdown files
        //TODO: Classified files by topics
    }

    private void loadExams() throws IOException {
        log.info("--> Loading exams...");
        InputStream inputStream = getClass().getResourceAsStream(PATH_FOR_EXAMS);

        List<Exam> examList = mapper.readValue(inputStream, new TypeReference<>() {});
        examService.addAll(examList);

        log.info("--> Exams loaded: {}", examList.size());
    }
    private void loadTopicDocuments() {

        File directory = new File(PATH_FOR_DOCUMENTS);

        if (!directory.exists() || !directory.isDirectory()) {
            log.info("Invalid directory path or not a directory: {}", PATH_FOR_DOCUMENTS);
            return;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            //1 list all documents into the folder
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    log.info("File: {}", file.getName());
                    //
                    String[] splitFileName = file.getName().split("-");

                    String examId = splitFileName[0];
                    String topicName = splitFileName[1];

                } else if (file.isDirectory()) {
                    log.info("Directory: {}", file.getName());
                    // Optionally, recursively list files in subdirectories
                    // listFilesAndFolders(file.getAbsolutePath());
                }
            }
        }


//        inputStream.
//        for(int i = 0; i < 10; i++) {
//            in
//        }
    }
}
