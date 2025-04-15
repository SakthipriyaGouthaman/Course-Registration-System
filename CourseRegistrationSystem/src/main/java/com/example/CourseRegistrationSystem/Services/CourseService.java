package com.example.CourseRegistrationSystem.Services;

import com.example.CourseRegistrationSystem.Model.Course;
import com.example.CourseRegistrationSystem.Model.CourseRegistry;
import com.example.CourseRegistrationSystem.Repository.CourseRegistryRepo;
import com.example.CourseRegistrationSystem.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
@Autowired
CourseRepo courseRepo;
    @Autowired
    CourseRegistryRepo courseRegistryRepo;
    public List<Course> availableCourses() {
        return courseRepo.findAll();
    }

    public List<CourseRegistry> enrolledStudents() {
        return courseRegistryRepo.findAll();
    }

    public void enrollCourse(String name, String emailId, String courseName) {
        CourseRegistry  courseRegistry=new CourseRegistry(name, emailId,courseName);
        courseRegistryRepo.save(courseRegistry);
    }
}
