package com.example.CourseRegistrationSystem.Controller;

import com.example.CourseRegistrationSystem.Model.Course;
import com.example.CourseRegistrationSystem.Model.CourseRegistry;
import com.example.CourseRegistrationSystem.Repository.CourseRegistryRepo;
import com.example.CourseRegistrationSystem.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("courses")
    public List<Course> getAvailableCourses()
    {
     return courseService.availableCourses();
    }
    @GetMapping("courses/enrolled")
    public List<CourseRegistry> enrolledStudents()
    {
return courseService.enrolledStudents();
    }
                @PostMapping("courses/register")
                public ResponseEntity<?> enrollCourse(@RequestParam("name") String name,
                                           @RequestParam("emailId") String emailId,
                                           @RequestParam("courseName") String courseName)
                {
                    // Create validation response map
                    Map<String, String> validationErrors = new HashMap<>();
                    
                    // Validate name
                    if (name == null || name.trim().isEmpty()) {
                        validationErrors.put("name", "Name is required");
                    } else if (name.length() < 2 || name.length() > 100) {
                        validationErrors.put("name", "Name must be between 2 and 100 characters");
                    } else if (!name.matches("^[a-zA-Z\\s]+$")) {
                        validationErrors.put("name", "Name can only contain letters and spaces");
                    }
                    
                    // Validate email
                    if (emailId == null || emailId.trim().isEmpty()) {
                        validationErrors.put("emailId", "Email is required");
                    } else if (!emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        validationErrors.put("emailId", "Email should be valid");
                    }
                    
                    // Validate course name
                    if (courseName == null || courseName.trim().isEmpty()) {
                        validationErrors.put("courseName", "Course name is required");
                    } else if (!courseName.matches("^(Java fullstack|Python|Spring Boot)$")) {
                        validationErrors.put("courseName", "Invalid course selected");
                    }
                    
                    // If validation errors exist, return bad request with error details
                    if (!validationErrors.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
                    }
                    
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