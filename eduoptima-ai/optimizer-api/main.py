from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import pulp

app = FastAPI(
    title="EduOptima Optimizer API",
    description="API de optimización académica",
    version="1.0"
)

# =========================
# MODELOS
# =========================

class Student(BaseModel):
    id: int
    name: str
    topic: str
    academicRisk: int
    priority: int


class Tutor(BaseModel):
    id: int
    name: str
    specialty: str
    maxCapacity: int


class OptimizationRequest(BaseModel):
    students: List[Student]
    tutors: List[Tutor]


# =========================
# RUTA HOME
# =========================

@app.get("/")
def home():
    return {
        "message": "Optimizer API funcionando"
    }


# =========================
# OPTIMIZACIÓN
# =========================

@app.post("/optimize")
def optimize(request: OptimizationRequest):

    students = request.students
    tutors = request.tutors

    # Crear problema
    problem = pulp.LpProblem(
        "TutorAssignment",
        pulp.LpMaximize
    )

    # Variables binarias
    x = {}

    for student in students:
        for tutor in tutors:
            variable_name = f"x_{student.id}_{tutor.id}"

            x[(student.id, tutor.id)] = pulp.LpVariable(
                variable_name,
                cat="Binary"
            )

    # =========================
    # FUNCIÓN OBJETIVO
    # =========================

    problem += pulp.lpSum(
        (
            student.priority *
            student.academicRisk *
            x[(student.id, tutor.id)]
        )

        for student in students
        for tutor in tutors

        if student.topic.lower() == tutor.specialty.lower()
    )

    # =========================
    # RESTRICCIÓN:
    # cada estudiante máximo un tutor
    # =========================

    for student in students:

        problem += pulp.lpSum(
            x[(student.id, tutor.id)]
            for tutor in tutors
        ) <= 1

    # =========================
    # RESTRICCIÓN:
    # capacidad tutor
    # =========================

    for tutor in tutors:

        problem += pulp.lpSum(
            x[(student.id, tutor.id)]
            for student in students
        ) <= tutor.maxCapacity

    # =========================
    # RESTRICCIÓN:
    # compatibilidad temática
    # =========================

    for student in students:
        for tutor in tutors:

            if student.topic.lower() != tutor.specialty.lower():

                problem += (
                    x[(student.id, tutor.id)] == 0
                )

    # =========================
    # RESOLVER
    # =========================

    problem.solve(
        pulp.PULP_CBC_CMD(msg=False)
    )

    # =========================
    # RESULTADOS
    # =========================

    assignments = []

    for student in students:
        for tutor in tutors:

            value = pulp.value(
                x[(student.id, tutor.id)]
            )

            if value == 1:

                assignments.append({

                    "studentId": student.id,
                    "studentName": student.name,

                    "tutorId": tutor.id,
                    "tutorName": tutor.name,

                    "topic": student.topic,

                    "score":
                        student.priority *
                        student.academicRisk
                })

    return {

        "status":
            pulp.LpStatus[problem.status],

        "objectiveValue":
            pulp.value(problem.objective),

        "assignments":
            assignments
    }