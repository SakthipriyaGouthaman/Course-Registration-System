package com.example.CourseRegistrationSystem.Controller;

import com.example.CourseRegistrationSystem.Model.Course;
import com.example.CourseRegistrationSystem.Model.CourseRegistry;
import com.example.CourseRegistrationSystem.Repository.CourseRegistryRepo;
import com.example.CourseRegistrationSystem.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                public String enrollCourse(@RequestParam("name") String name,
                                           @RequestParam("emailId") String emailId,
                                           @RequestParam("courseName") String courseName)
                {
                    courseService.enrollCourse(name,emailId,courseName);
        return "Congratulations! "+name+" Enrollment Successfull for "+courseName;
    }
}
