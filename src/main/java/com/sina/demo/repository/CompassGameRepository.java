package com.sina.demo.repository;

import com.sina.demo.entity.CompassGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompassGameRepository extends JpaRepository<CompassGame, Long> {
    List<CompassGame> findAllByName(String name);
}
