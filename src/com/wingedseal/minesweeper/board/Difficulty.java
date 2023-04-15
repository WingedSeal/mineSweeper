package com.wingedseal.minesweeper.board;

public enum Difficulty {
    PEACEFUL, EASY, NORMAL, HARD, HARDCORE;

    public int getNumberOfMines(int numberOfCells) {
        return switch (this) {
            case PEACEFUL -> numberOfCells / 10;
            case EASY -> numberOfCells / 8;
            case NORMAL -> numberOfCells / 6;
            case HARD -> numberOfCells / 3;
            case HARDCORE -> numberOfCells / 2;
        };
    }

    public int getMaxNeighBorOpening() {
        return switch (this) {
            case PEACEFUL, EASY -> 0;
            case NORMAL -> 2;
            case HARD -> 5;
            case HARDCORE -> 6;
        };
    }

    public int getMaxNeighBorMines() {
        return switch (this) {
            case PEACEFUL, EASY -> 4;
            case NORMAL -> 5;
            case HARD -> 6;
            case HARDCORE -> 8;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case PEACEFUL -> "Peaceful";
            case EASY -> "Easy";
            case NORMAL -> "Normal";
            case HARD -> "Hard";
            case HARDCORE -> "Hardcore";
        };
    }
}

