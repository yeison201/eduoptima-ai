package com.tecnocomfenalco.edurag.eduoptimaai.service;

import com.tecnocomfenalco.edurag.eduoptimaai.dto.OptimizationRequest;
import com.tecnocomfenalco.edurag.eduoptimaai.dto.OptimizationResponse;
import com.tecnocomfenalco.edurag.eduoptimaai.model.Student;
import com.tecnocomfenalco.edurag.eduoptimaai.model.Tutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OptimizationService {

    private final StudentService studentService;
    private final TutorService tutorService;
    private final RestClient restClient;

    @Value("${optimizer.api.url}")
    private String optimizerApiUrl;

    public OptimizationService(StudentService studentService,
                               TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
        this.restClient = RestClient.create();
    }

    public OptimizationResponse optimize() {

        List<Student> students = studentService.findAll();
        List<Tutor> tutors = tutorService.findAll();

        OptimizationRequest request = new OptimizationRequest(students, tutors);

        return restClient.post()
                .uri(optimizerApiUrl)
                .body(request)
                .retrieve()
                .body(OptimizationResponse.class);
    }
}