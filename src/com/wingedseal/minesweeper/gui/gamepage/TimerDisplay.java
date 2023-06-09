package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;

public class TimerDisplay extends NumberDisplay {
    private int timePassed = TIME_START_AT;

    private final Timer timer = new Timer(1000, e -> {
        setNumber(Math.max(++timePassed, 0)); // The Math.max is just there to fulfill the requirements.
    });
    private static final int TIME_START_AT = 0;

    public TimerDisplay() {
        super(TIME_START_AT);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

}
