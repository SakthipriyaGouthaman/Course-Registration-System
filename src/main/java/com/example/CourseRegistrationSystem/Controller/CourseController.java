package com.example.CourseRegistrationSystem.Controller;

import com.example.CourseRegistrationSystem.Model.Course;
import com.example.CourseRegistrationSystem.Model.CourseRegistry;
import com.example.CourseRegistrationSystem.Repository.CourseRegistryRepo;
import com.example.CourseRegistrationSystem.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("courses")
    public List<Course> getAvailableCourses() {
        return courseService.availableCourses();
    }

    @GetMapping("courses/enrolled")
    public List<CourseRegistry> enrolledStudents() {
        return courseService.enrolledStudents();
    }

    @PostMapping("courses/register")
    public ResponseEntity<?> enrollCourse(
            @RequestParam("name")
            @NotNull(message = "Name is required")
            @NotBlank(message = "Name cannot be blank")
            @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
            @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
            String name,

            @RequestParam("emailId")
            @NotNull(message = "Email is required")
            @NotBlank(message = "Email cannot be blank")
            @Email(message = "Email should be valid")
            String emailId,

            @RequestParam("courseName")
            @NotNull(message = "Course name is required")
            @NotBlank(message = "Course name cannot be blank")
            @Pattern(regexp = "^(Java fullstack|Python|Spring Boot)$", message = "Invalid course selected")
            String courseName) {

        try {
            courseService.enrollCourse(name, emailId, courseName);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "Congratulations! " + name + " Enrollment Successful for " + courseName);
            successResponse.put("status", "SUCCESS");
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during registration");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}