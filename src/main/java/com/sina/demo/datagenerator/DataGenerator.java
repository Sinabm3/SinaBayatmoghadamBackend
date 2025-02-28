package com.sina.demo.datagenerator;

import com.sina.demo.entity.CompassGame;
import com.sina.demo.entity.Question;
import com.sina.demo.repository.CompassGameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataGenerator implements CommandLineRunner {
    String defaultPassword = "21321321";

    private final CompassGameRepository compassGameRepository;
    public DataGenerator(CompassGameRepository compassGameRepository) {
        this.compassGameRepository = compassGameRepository;
    }

    @Override
    public void run(String... args) {

        if(compassGameRepository.count() > 0){
            return;
        }
        CompassGame defaultGame = new CompassGame();
        defaultGame.setName("Default Compass Game");
        defaultGame.setHorizontalAxisNegativeName("Empathy");
        defaultGame.setHorizontalAxisPositiveName("Meritocracy");
        defaultGame.setVerticalAxisNegativeName("Collectivism");
        defaultGame.setVerticalAxisPositiveName("Individualism");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        defaultGame.setPassword(encoder.encode(defaultPassword));
        defaultGame.setQuestions(new HashSet<>());

        /*
         * This interprets each question's "empathyAxis" or "collectivistAxis" from your original data
         * to decide isHorizontalQuestion (true/false) and axisPower (+1 or -1).
         */

        // #1 empathyAxis = 1 => horizontal => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "If someone cannot afford food or shelter, they should still have access to it.",
                true,
                -1,
                defaultGame
        ));

        // #2 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "If my child disagrees with my most important moral values, I will continuously try to change their mind.",
                false,
                -1,
                defaultGame
        ));

        // #3 collectivistAxis = -1 => vertical => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "Charity is more effective in improving healthcare than nationalizing the healthcare system.",
                false,
                +1,
                defaultGame
        ));

        // #4 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "A company should always have the right to choose who they serve, even if it is based on arbitrary factors like race or religion.",
                true,
                +1,
                defaultGame
        ));

        // #5 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "You should never negotiate with people you view as terrorists.",
                true,
                +1,
                defaultGame
        ));

        // #6 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "It is acceptable to kill civilians if you are also targeting people you view as terrorists.",
                true,
                +1,
                defaultGame
        ));

        // #7 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "Higher education is a commodity, not a human right, and therefore should not be free.",
                true,
                +1,
                defaultGame
        ));

        // #8 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "To protect free speech, all forms of insults and slurs should be legalized.",
                true,
                +1,
                defaultGame
        ));

        // #9 empathyAxis = 1 => horizontal => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "The purpose of imprisonment should only be rehabilitation, not punishment.",
                true,
                -1,
                defaultGame
        ));

        // #10 empathyAxis = -1 => horizontal => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "The death penalty is sometimes justified.",
                true,
                +1,
                defaultGame
        ));

        // #11 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "The community's needs should always come before individual needs.",
                false,
                -1,
                defaultGame
        ));

        // #12 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "It is more important to work for the betterment of society than for personal success.",
                false,
                -1,
                defaultGame
        ));

        // #13 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "Rich people should pay higher taxes if it means better public services for everyone.",
                false,
                -1,
                defaultGame
        ));

        // #14 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "A doctor and a farmer should get paid the same in a world, where education is free and comfortable.",
                false,
                -1,
                defaultGame
        ));

        // #15 collectivistAxis = -1 => vertical => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "Working for another company is a better option than trying to change the company you currently work at.",
                false,
                +1,
                defaultGame
        ));

        // #16 collectivistAxis = -1 => vertical => -1
        defaultGame.getQuestions().add(new Question(
                null,
                "People who hate the actions of their country should just move to another country.",
                false,
                +1,
                defaultGame
        ));

        // #17 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "Public health should be prioritized over individual freedoms during a pandemic.",
                false,
                -1,
                defaultGame
        ));

        // #18 empathyAxis = 1 => horizontal => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "Racial profiling is always wrong, even if it is based on statistics.",
                true,
                -1,
                defaultGame
        ));

        // #19 empathyAxis = 1 => horizontal => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "Deporting foreigners back to war zones is never justified.",
                true,
                -1,
                defaultGame
        ));

        // #20 collectivistAxis = 1 => vertical => +1
        defaultGame.getQuestions().add(new Question(
                null,
                "Foreigners should try to integrate and assimilate.",
                false,
                -1,
                defaultGame
        ));

         // 2) Second Compass Game (Emotional vs. Logical)

        CompassGame confidenceGame = new CompassGame();
        confidenceGame.setName("Emotional vs. Logical Confidence");
        confidenceGame.setHorizontalAxisPositiveName("High Emotional Confidence");
        confidenceGame.setHorizontalAxisNegativeName("Low Emotional Confidence");
        confidenceGame.setVerticalAxisPositiveName("High Logical Confidence");
        confidenceGame.setVerticalAxisNegativeName("Low Logical Confidence");
        defaultGame.setPassword(encoder.encode(defaultPassword));
        confidenceGame.setQuestions(new HashSet<>());
        confidenceGame.getQuestions().add(new Question(
                null,
                "I often rely on my feelings when making decisions.",
                true,
                +1,
                confidenceGame
        ));

        confidenceGame.getQuestions().add(new Question(
                null,
                "I believe rational analysis is the best way to solve problems, regardless of emotional factors.",
                true,
                -1,
                confidenceGame
        ));

        confidenceGame.getQuestions().add(new Question(
                null,
                "I feel confident expressing opinions I can't fully back up with evidence.",
                false,
                -1,
                confidenceGame
        ));

        confidenceGame.getQuestions().add(new Question(
                null,
                "I rarely trust an argument unless it is supported by objective data.",
                false,
                +1,
                confidenceGame
        ));

        compassGameRepository.save(defaultGame);
        compassGameRepository.save(confidenceGame);
    }
}
