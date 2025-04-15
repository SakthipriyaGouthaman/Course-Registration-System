package com.example.CourseRegistrationSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class CourseRegistry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String emailId;
    private String courseName;

    public CourseRegistry(String name, String courseName, String emailId) {
        this.name = name;
        this.courseName = courseName;
        this.emailId = emailId;
    }

    public CourseRegistry() {
    }
}
