package com.example.ManageMate.Repositories;

import com.example.ManageMate.Models.Application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
