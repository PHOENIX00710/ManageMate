package com.example.ManageMate.Repositories;

import com.example.ManageMate.Models.User.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeFileRepository extends JpaRepository<Resume,Long> {
}
