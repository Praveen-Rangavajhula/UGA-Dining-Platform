package com.db.project.ugadining.service;

import com.db.project.ugadining.exception.NotFoundException;
import com.db.project.ugadining.model.MealPlan;
import com.db.project.ugadining.model.Student;
import com.db.project.ugadining.model.dto.StudentDto;
import com.db.project.ugadining.model.enums.Allergy;
import com.db.project.ugadining.model.enums.FoodPreference;
import com.db.project.ugadining.repository.MealPlanRepository;
import com.db.project.ugadining.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger( StudentService.class );
    private final StudentRepository studentRepository;
    private final MealPlanRepository mealPlanRepository;

    @Transactional
    public List<Student> getStudents() {
        logger.info("Obtaining all students");
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        logger.info("Obtaining student with id {}", id);
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Student with id '%d' not found", id))
                );
    }

    public Student getStudentByName(String name) {
        logger.info("Obtaining student with name {}", name);
        return studentRepository.findStudentByStudentName(name)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Student with name '%s' not found", name))
                );
    }

    public Student putNewStudent(StudentDto studentDto) {

        if (studentDto.studentName() == null || studentDto.studentPhoneNumber() == null ||
                studentDto.studentEmail() == null || studentDto.studentMealPlanId() == null) {
            throw new IllegalArgumentException("Required fields are missing in StudentDto");
        }

        MealPlan mealPlan = mealPlanRepository.findById(Objects.requireNonNull(studentDto.studentMealPlanId()))
                .orElseThrow(() -> new RuntimeException("MealPlan not found"));

        List<FoodPreference> foodPreferences = null;
        if (studentDto.studentFoodPreferences() != null) {
            foodPreferences = new ArrayList<>(studentDto.studentFoodPreferences());
        }

        List<Allergy> allergies = null;
        if (studentDto.studentAllergies() != null) {
            allergies = new ArrayList<>(studentDto.studentAllergies());
        }

        Student student = Student.builder()
                .studentName(studentDto.studentName())
                .studentPhoneNumber(studentDto.studentPhoneNumber())
                .studentEmail(studentDto.studentEmail())
                .studentFoodPreferences(foodPreferences)
                .studentAllergies(allergies)
                .studentMealPlan(mealPlan)
                .build();

        logger.info("Registering a new student {}", student);

        studentRepository.save(student);

        return student;
    }

    public void deleteStudentByName(String studentName) {
        logger.info("Deleting the student with name {}", studentName);

        boolean exists = studentRepository.existsByStudentName(studentName);
        if(!exists) {
            logger.info("Student with name {} does not exist", studentName);
            throw new NotFoundException(String.format("Student with name %s does not exist", studentName));
        }
        studentRepository.deleteByStudentName(studentName);
    }

    public void deleteStudentById(Long id) {
        logger.info("Deleting the student with id {}", id);

        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            logger.info("Student with id {} does not exist", id);
            throw new NotFoundException(String.format("Student with id %s does not exist", id));
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long studentId, StudentDto studentDto) {
        logger.info("Updating the student with id {}", studentId);

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(String.format("Student with name %s not found", studentId)));

        if (studentDto.studentName() != null) {
            existingStudent.setStudentName(studentDto.studentName());
        }

        if (studentDto.studentPhoneNumber() != null) {
            existingStudent.setStudentPhoneNumber(studentDto.studentPhoneNumber());
        }

        if (studentDto.studentEmail() != null) {
            existingStudent.setStudentEmail(studentDto.studentEmail());
        }

        if (studentDto.studentFoodPreferences() != null) {
            existingStudent.setStudentFoodPreferences(studentDto.studentFoodPreferences());
        }

        if (studentDto.studentAllergies() != null) {
            existingStudent.setStudentAllergies(studentDto.studentAllergies());
        }

        if (studentDto.studentMealPlanId() != null) {
            MealPlan studentMealPlan = mealPlanRepository.findById(studentDto.studentMealPlanId())
                    .orElseThrow(() -> new NotFoundException("MealPlan not found"));
            existingStudent.setStudentMealPlan(studentMealPlan);
        }

        return existingStudent;
    }
}
