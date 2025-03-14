package com.sina.demo.service;

import com.sina.demo.endpoint.dto.CompassGameDto;
import com.sina.demo.endpoint.dto.InfoCompassGameDto;
import com.sina.demo.endpoint.dto.QuestionDto;
import com.sina.demo.entity.CompassGame;
import com.sina.demo.entity.Question;
import com.sina.demo.repository.CompassGameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompassGameService {
    private final CompassGameRepository compassGameRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    public CompassGameService(CompassGameRepository compassGameRepository) {
        this.compassGameRepository = compassGameRepository;
    }

    public CompassGameDto getCompassGameById(Long id) {
        CompassGame entity = compassGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found!"));
        return new CompassGameDto(entity.getId(), entity.getName(), entity.getHorizontalAxisPositiveName(),
                entity.getHorizontalAxisNegativeName(), entity.getVerticalAxisPositiveName(),
                entity.getVerticalAxisNegativeName(), null,
                entity.getQuestions().stream().map(question -> new QuestionDto(question.getId(),
                        question.getText(),question.isHorizontalQuestion(),
                        question.getAxisPower())).collect(Collectors.toSet()));
    }

    public Stream<InfoCompassGameDto> getAllCompassGames() {
       return compassGameRepository.findAll().stream().map(entity -> new InfoCompassGameDto(entity.getId(), entity.getName(),
                entity.getHorizontalAxisPositiveName(), entity.getHorizontalAxisNegativeName(),
                entity.getVerticalAxisPositiveName(), entity.getVerticalAxisNegativeName()));
    }

    public Long create(CompassGameDto compassGameDto) {
        CompassGame compassGame = new CompassGame();
        compassGame.setName(compassGameDto.name());
        compassGame.setHorizontalAxisPositiveName(compassGameDto.horizontalAxisPositiveName());
        compassGame.setHorizontalAxisNegativeName(compassGameDto.horizontalAxisNegativeName());
        compassGame.setVerticalAxisPositiveName(compassGameDto.verticalAxisPositiveName());
        compassGame.setVerticalAxisNegativeName(compassGameDto.verticalAxisNegativeName());
        compassGame.setPassword(encoder.encode(compassGameDto.password()));
        compassGame.setQuestions(compassGameDto.questionDtos().stream().map(questionDto -> new Question(
                questionDto.id(), questionDto.text(), questionDto.isHorizontal(), questionDto.axisPower(), compassGame
        )).collect(Collectors.toSet()));
        compassGameRepository.save(compassGame);
        return compassGame.getId();
    }

    public void delete(Long id, String password) {
        compassGameRepository.findById(id).ifPresentOrElse(compassGame -> {
            if(encoder.matches(password, compassGame.getPassword())){
                compassGameRepository.delete(compassGame);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password is incorrect!");
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found!");
        });
    }

    public Stream<InfoCompassGameDto> search(String name) {
        compassGameRepository.findAllByName("awdawd");
        return compassGameRepository.findAllByName(name).stream().map(entity -> new InfoCompassGameDto(entity.getId(), entity.getName(),
                entity.getHorizontalAxisPositiveName(), entity.getHorizontalAxisNegativeName(),
                entity.getVerticalAxisPositiveName(), entity.getVerticalAxisNegativeName()));
    }
    public Long edit(CompassGameDto compassGameDto, String password) {
        compassGameRepository.findById(compassGameDto.id()).ifPresentOrElse(compassGame -> {
            if(encoder.matches(password, compassGame.getPassword())){
                compassGame.setName(compassGameDto.name());
                compassGame.setHorizontalAxisPositiveName(compassGameDto.horizontalAxisPositiveName());
                compassGame.setHorizontalAxisNegativeName(compassGameDto.horizontalAxisNegativeName());
                compassGame.setVerticalAxisPositiveName(compassGameDto.verticalAxisPositiveName());
                compassGame.setVerticalAxisNegativeName(compassGameDto.verticalAxisNegativeName());
                compassGame.getQuestions().clear();
                compassGame.getQuestions().addAll(compassGameDto.questionDtos().stream().map(questionDto -> new Question(
                        questionDto.id(), questionDto.text(), questionDto.isHorizontal(), questionDto.axisPower(), compassGame
                )).collect(Collectors.toSet()));
                compassGameRepository.save(compassGame);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password is incorrect!");
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found!");
        });
        return compassGameDto.id();
    }

    public void checkPassword(Long id, String password) {
        compassGameRepository.findById(id).ifPresentOrElse(compassGame -> {
            if(!encoder.matches(password, compassGame.getPassword())){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password is incorrect!");
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found!");
        });
    }
}
