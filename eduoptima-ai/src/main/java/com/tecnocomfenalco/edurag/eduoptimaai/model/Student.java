package com.tecnocomfenalco.edurag.eduoptimaai.model;

//package com.example.eduoptima.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String topic;

    @Min(1)
    @Max(5)
    private int academicRisk;

    @Min(1)
    @Max(10)
    private int priority;

    public Student() {
    }

    public Student(Long id, String name, String topic, int academicRisk, int priority) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.academicRisk = academicRisk;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public int getAcademicRisk() {
        return academicRisk;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setAcademicRisk(int academicRisk) {
        this.academicRisk = academicRisk;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}