package com.tecnocomfenalco.edurag.eduoptimaai.dto;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Student;
import com.tecnocomfenalco.edurag.eduoptimaai.model.Tutor;

import java.util.List;

public class OptimizationRequest {

    private List<Student> students;
    private List<Tutor> tutors;

    public OptimizationRequest() {
    }

    public OptimizationRequest(List<Student> students, List<Tutor> tutors) {
        this.students = students;
        this.tutors = tutors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}