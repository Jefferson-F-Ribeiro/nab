package com.example.nab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking implements Serializable {
    private List<Integer> scoresStageOne;

    public Ranking() {
        scoresStageOne = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoresStageOne.add(0);
        }
    }

    public void addScoreStageOne(int score) {
        scoresStageOne.add(score);
        Collections.sort(scoresStageOne, Collections.reverseOrder());
        if (scoresStageOne.size() > 5) {
            scoresStageOne.remove(scoresStageOne.size() - 1);
        }
    }

    public List<Integer> getScoresStageOne() {
        return scoresStageOne;
    }
}
