package com.alexgim.sharing.image.data.repository;

import com.alexgim.sharing.image.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
