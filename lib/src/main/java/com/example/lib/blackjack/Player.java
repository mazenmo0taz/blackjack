package com.example.lib.blackjack;

public class Player {
    private final String name;
    private int score;
    Card[] cardsArr = new Card[11];
    private boolean lost = false;
    int index = 0;
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
