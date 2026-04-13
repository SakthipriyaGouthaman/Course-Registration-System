package com.example.CourseRegistrationSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @NotBlank(message = "Course ID cannot be blank")
    @Size(min = 1, max = 50, message = "Course ID must be between 1 and 50 characters")
    private String CourseId;

    @NotBlank(message = "Course Name cannot be blank")
    @Size(min = 2, max = 100, message = "Course Name must be between 2 and 100 characters")
    private String CourseName;

    @NotBlank(message = "Trainer name cannot be blank")
    @Size(min = 2, max = 100, message = "Trainer name must be between 2 and 100 characters")
    private String trainer;

    @NotNull(message = "Duration in weeks cannot be null")
    @Positive(message = "Duration in weeks must be a positive number")
    private int durationInWeeks;
}