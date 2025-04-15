package com.example.CourseRegistrationSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    private String CourseId;
    private String CourseName;
    private String trainer;
    private int durationInWeeks;
}
