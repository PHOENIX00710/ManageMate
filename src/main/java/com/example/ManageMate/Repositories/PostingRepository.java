package com.example.ManageMate.Repositories;

import com.example.ManageMate.Models.Posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting,Long> {
}
