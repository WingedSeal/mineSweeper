package com.wingedseal.minesweeper.gui.menupage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SizeSelector extends JPanel {
    private final SizeSelectorSlider slider = new SizeSelectorSlider();
    private final JTextField textField = new JTextField(3);

    public static final int MAX_SIZE = 70;
    public static final int MIN_SIZE = 1;
    public static final int DEFAULT_SIZE = 10;
    private static final int FONT_SIZE = 20;

    SizeSelector(String selectorLabel) {
        setLayout(new GridBagLayout());
        slider.addChangeListener(e ->
                textField.setText(Integer.toString(getValue()))
        );
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLimit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLimit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLimit();
            }

            private void setText(int number) {
                textField.setText(Integer.toString(number));
                slider.setValue(number);
            }

            private void checkLimit() {
                // Directly editing text in the same thread before
                // document lock is released will throw
                // "Attempt to mutate notification" exception
                SwingUtilities.invokeLater(() -> {
                    final int textInt;
                    if (textField.getText().equals("")) {
                        slider.setValue(DEFAULT_SIZE);
                        textField.setText("");
                        return;
                    }

                    try {
                        textInt = Integer.parseInt(textField.getText());
                    } catch (NumberFormatException error) {
                        setText(DEFAULT_SIZE);
                        return;
                    }

                    if (textInt < MIN_SIZE) {
                        setText(MIN_SIZE);
                    } else if (textInt > MAX_SIZE) {
                        setText(MAX_SIZE);
                    } else {
                        slider.setValue(textInt);
                    }

                });
            }
        });
        add(slider);
        JLabel label = new JLabel("   " + selectorLabel);
        label.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        add(label);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
        add(textField);
        textField.setText(Integer.toString(getValue()));
    }

    public int getValue() {
        return slider.getValue();
    }
}

class SizeSelectorSlider extends JSlider {
    private static final int FONT_SIZE = 15;
    SizeSelectorSlider() {
        super(0, SizeSelector.MAX_SIZE, SizeSelector.DEFAULT_SIZE);
        setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
        setMinorTickSpacing(5);
        setMajorTickSpacing(10);
        setPaintTrack(true);
        setPaintTicks(true);
        setPaintLabels(true);
    }

    @Override
    public int getValue() {
        return Math.max(super.getValue(), SizeSelector.MIN_SIZE);
    }
}