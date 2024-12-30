package com.sina.demo.service;

import com.sina.demo.backend.dto.CompassGameDto;
import com.sina.demo.backend.dto.InfoCompassGameDto;
import com.sina.demo.backend.dto.QuestionDto;
import com.sina.demo.entity.CompassGame;
import com.sina.demo.entity.Question;
import com.sina.demo.repository.CompassGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompassGameService {
    private final CompassGameRepository compassGameRepository;
    @Autowired
    public CompassGameService(CompassGameRepository compassGameRepository) {
        this.compassGameRepository = compassGameRepository;
    }

    public CompassGameDto getCompassGameById(Long id) {
        CompassGame entity = compassGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found!"));
        return new CompassGameDto(entity.getId(), entity.getName(), entity.getHorizontalAxisPositiveName(),
                entity.getHorizontalAxisNegativeName(), entity.getVerticalAxisPositiveName(),
                entity.getVerticalAxisNegativeName(),
                entity.getQuestions().stream().map(question -> new QuestionDto(question.getId(),
                        question.getText(),question.isHorizontalQuestion(),
                        question.getAxisPower())).collect(Collectors.toSet()));
    }

    public Stream<InfoCompassGameDto> getAllCompassGames() {
       return compassGameRepository.findAll().stream().map(entity -> new InfoCompassGameDto(entity.getId(), entity.getName(),
                entity.getHorizontalAxisPositiveName(), entity.getHorizontalAxisNegativeName(),
                entity.getVerticalAxisPositiveName(), entity.getVerticalAxisNegativeName()));
    }

    public void create(CompassGameDto compassGameDto) {
        CompassGame compassGame = new CompassGame();
        compassGame.setName(compassGameDto.name());
        compassGame.setHorizontalAxisPositiveName(compassGameDto.horizontalAxisPositiveName());
        compassGame.setHorizontalAxisNegativeName(compassGameDto.horizontalAxisNegativeName());
        compassGame.setVerticalAxisPositiveName(compassGameDto.verticalAxisPositiveName());
        compassGame.setVerticalAxisNegativeName(compassGameDto.verticalAxisNegativeName());
        compassGame.setQuestions(compassGameDto.questionDtos().stream().map(questionDto -> new Question(
                questionDto.id(), questionDto.text(), questionDto.isHorizontal(), questionDto.axisPower(), compassGame
        )).collect(Collectors.toSet()));
        compassGameRepository.save(compassGame);
    }
}
