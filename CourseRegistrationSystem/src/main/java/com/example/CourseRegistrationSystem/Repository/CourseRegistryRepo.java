package com.example.CourseRegistrationSystem.Repository;

import com.example.CourseRegistrationSystem.Model.CourseRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistryRepo extends JpaRepository<CourseRegistry,Integer> {
}
