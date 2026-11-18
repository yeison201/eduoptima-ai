package com.tecnocomfenalco.edurag.eduoptimaai.dto;

import java.util.List;

public class OptimizationResponse {

    private String status;
    private double objectiveValue;
    private List<AssignmentResult> assignments;

    public OptimizationResponse() {
    }

    public String getStatus() {
        return status;
    }

    public double getObjectiveValue() {
        return objectiveValue;
    }

    public List<AssignmentResult> getAssignments() {
        return assignments;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setObjectiveValue(double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public void setAssignments(List<AssignmentResult> assignments) {
        this.assignments = assignments;
    }
}