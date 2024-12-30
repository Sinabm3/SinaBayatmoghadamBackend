package com.sina.demo.repository;

import com.sina.demo.entity.CompassGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompassGameRepository extends JpaRepository<CompassGame, Long> {
}
