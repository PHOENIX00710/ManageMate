package com.example.ManageMate.Repositories;

import com.example.ManageMate.Models.User.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<Image,Long> {
}
