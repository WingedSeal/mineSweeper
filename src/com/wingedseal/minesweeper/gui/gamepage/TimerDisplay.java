package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;
import java.awt.*;

import static com.wingedseal.minesweeper.utils.NumberToCustomString.toCustomString;

public class TimerDisplay extends JLabel {
    private int timePassed = TIME_START_AT;

    private static final int TIME_START_AT = 0;
    private final Timer timer = new Timer(1000, e -> {
        setText(toCustomString(Math.max(++timePassed, 0)));  // The Math.max is just there to fulfill the requirements.
    });

    public TimerDisplay(Font font) {
        super(toCustomString(TIME_START_AT));
        setOpaque(true);
        setBackground(Color.BLACK);
        setForeground(Color.red);
        setFont(font);
        setHorizontalAlignment(CENTER);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

}
