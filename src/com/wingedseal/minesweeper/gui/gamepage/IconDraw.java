package com.wingedseal.minesweeper.gui.gamepage;

import java.awt.*;

public class IconDraw {
    private static final double NORMAL_BORDER_WIDTH_SCALE = 0.15;
    private static final double REVEALED_BORDER_WIDTH_SCALE = 0.05;
    private static final double FONT_SCALE = 0.9;

    private static final double BOMB_SCALE = 0.65;
    private static final double BOMB_SPIKE_WIDTH_SCALE = 0.15;
    private static final double BOMB_SPIKE_HEIGHT_SCALE = 1.2;

    private static final double EMOJI_SCALE = 0.65;
    private static final float EMOJI_STROKE_WIDTH = 1.5f;

    public static void drawNormalCell(Graphics g, int width, int height) {
        final int borderSizeX = (int) (width * NORMAL_BORDER_WIDTH_SCALE);
        final int borderSizeY = (int) (height * NORMAL_BORDER_WIDTH_SCALE);
        g.setColor(Color.WHITE);
        // Top left (Clockwise)
        g.fillPolygon(
                new int[]{0, width, width - borderSizeX, borderSizeX, borderSizeX, 0},
                new int[]{0, 0, borderSizeY, borderSizeY, height - borderSizeY, height},
                6
        );

        g.setColor(Color.GRAY);
        // Bottom right (Clockwise)
        g.fillPolygon(
                new int[]{width, 0, borderSizeX, width - borderSizeX, width - borderSizeX, width, width},
                new int[]{height, height, height - borderSizeY, height - borderSizeY, borderSizeY, 0},
                6
        );
    }

    public static void drawEmptyCell(Graphics g, int width, int height) {
        final int borderWidth = (int) (width * REVEALED_BORDER_WIDTH_SCALE);
        final int borderHeight = (int) (height * REVEALED_BORDER_WIDTH_SCALE);
        g.setColor(Color.GRAY);
        // Top
        g.fillRect(0, 0, width, borderHeight);
        // left
        g.fillRect(0, 0, borderWidth, height);
        // bottom
        g.fillRect(0, height - borderHeight, width, borderHeight);
        // right
        g.fillRect(width - borderWidth, 0, borderWidth, height);
    }

    public static void drawNumber(Graphics g, int width, int height, String number) {
        final int fontSize = (int) (FONT_SCALE * Math.min(width, height));
        g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
        final int numberWidth = g.getFontMetrics().stringWidth(number);
        if (number.equals("0")) return;
        switch (number) {
            case "1" -> g.setColor(new Color(1, 1, 253));
            case "2" -> g.setColor(new Color(0, 122, 0));
            case "3" -> g.setColor(new Color(249, 13, 13));
            case "4" -> g.setColor(new Color(0, 0, 122));
            case "5" -> g.setColor(new Color(122, 0, 0));
            case "6" -> g.setColor(new Color(32, 134, 134));
            case "7" -> g.setColor(new Color(25, 25, 25));
            case "8" -> g.setColor(new Color(127, 127, 127));
        }
        g.drawString(number, (width - numberWidth) / 2, (int) (FONT_SCALE * (height + fontSize)) / 2);
    }

    public static void drawFlag(Graphics g, int width, int height) {
        final int topFlagY = (int) (0.15 * height);
        final int middleFlagY = (int) (0.3 * height);
        final int bottomFlagY = (int) (0.55 * height);
        final int topBaseY = (int) (0.6 * height);
        final int bottomBaseY = (int) (0.8 * height);
        final int poleWidth = (int) (0.1 * width);
        final int leftX = (int) (0.2 * width);
        // Pole
        g.setColor(Color.BLACK);
        g.fillRect((width - poleWidth) / 2, middleFlagY, poleWidth, bottomBaseY - middleFlagY);
        g.fillPolygon(
                new int[]{leftX, width / 2, width - leftX},
                new int[]{bottomBaseY, topBaseY, bottomBaseY},
                3
        );
        // Flag
        g.setColor(new Color(193, 54, 36, 255));
        g.fillPolygon(
                new int[]{leftX, (width + poleWidth) / 2, (width + poleWidth) / 2},
                new int[]{middleFlagY, topFlagY, bottomFlagY},
                3
        );
    }

    public static void drawBomb(Graphics g, int width, int height) {
        final int bombDiameter = (int) (Math.min(width, height) * BOMB_SCALE);
        final int bombSpikeWidth = (int) (BOMB_SPIKE_WIDTH_SCALE * bombDiameter);
        final int bombSpikeHeight = (int) (BOMB_SPIKE_HEIGHT_SCALE * bombDiameter);
        g.setColor(Color.BLACK);

        // Middle circle
        g.fillOval((width - bombDiameter) / 2, (height - bombDiameter) / 2, bombDiameter, bombDiameter);

        // Spikes
        g.fillRect((width - bombSpikeWidth) / 2, (height - bombSpikeHeight) / 2, bombSpikeWidth, bombSpikeHeight);
        g.fillRect((width - bombSpikeHeight) / 2, (height - bombSpikeWidth) / 2, (bombSpikeHeight), (bombSpikeWidth));

        g.setColor(Color.WHITE);
        // Shading circle
        g.fillOval((width - bombDiameter / 2) / 2, (height - bombDiameter / 2) / 2, bombDiameter / 4, bombDiameter / 4);
    }

    private static void drawBaseEmoji(Graphics g, int width, int height) {
        drawNormalCell(g, width, height);
        final int emojiDiameter = (int) (height * EMOJI_SCALE);
        g.setColor(Color.YELLOW);
        // Background circle
        g.fillOval((width - emojiDiameter) / 2, (height - emojiDiameter) / 2, emojiDiameter, emojiDiameter);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EMOJI_STROKE_WIDTH));
        // Edge circle
        g2.drawOval((width - emojiDiameter) / 2, (height - emojiDiameter) / 2, emojiDiameter, emojiDiameter);
    }

    public static void drawIdleEmoji(Graphics g, int width, int height) {
        final int emojiDiameter = (int) (height * EMOJI_SCALE);
        final int smileDiameter = (int) (0.6 * emojiDiameter);
        final int smileArcDegree = 120;
        final int eyeDiameter = (int) (0.15 * emojiDiameter);
        final int eyeXDistance = (int) (0.15 * emojiDiameter);
        final int eyeYDistance = (int) (0.15 * emojiDiameter);
        drawBaseEmoji(g, width, height);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EMOJI_STROKE_WIDTH));
        // Smile
        g2.drawArc((width - smileDiameter) / 2, (height - smileDiameter) / 2, smileDiameter, smileDiameter, 270 - smileArcDegree / 2, smileArcDegree);
        // Right eye
        g2.fillOval((width - eyeDiameter) / 2 + eyeXDistance, (height - eyeDiameter) / 2 - eyeYDistance, eyeDiameter, eyeDiameter);
        // Left eye
        g2.fillOval((width - eyeDiameter) / 2 - eyeXDistance, (height - eyeDiameter) / 2 - eyeYDistance, eyeDiameter, eyeDiameter);
    }

    public static void drawNormalCellFoundEmoji(Graphics g, int width, int height) {
        final int emojiDiameter = (int) (height * EMOJI_SCALE);
        final int eyeXDistance = (int) (0.15 * emojiDiameter);
        final int eyeYDistance = (int) (0.15 * emojiDiameter);
        final int eyeDiameter = (int) (0.16 * emojiDiameter);
        final int mouthWidth = (int) (0.2 * emojiDiameter);
        final int mouthHeight = (int) (0.25 * emojiDiameter);
        final int mouthYDistance = (int) (0.25 * emojiDiameter);
        drawBaseEmoji(g, width, height);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EMOJI_STROKE_WIDTH));
        // Surprised mouth
        g2.drawOval((width - mouthWidth) / 2, (height - mouthHeight) / 2 + mouthYDistance, mouthWidth, mouthHeight);
        // Right eye
        g2.fillOval((width - eyeDiameter) / 2 + eyeXDistance, (height - eyeDiameter) / 2 - eyeYDistance, eyeDiameter, eyeDiameter);
        // Left eye
        g2.fillOval((width - eyeDiameter) / 2 - eyeXDistance, (height - eyeDiameter) / 2 - eyeYDistance, eyeDiameter, eyeDiameter);
    }

    public static void drawWinEmoji(Graphics g, int width, int height) {
        final int emojiDiameter = (int) (height * EMOJI_SCALE);
        final int smileDiameter = (int) (0.6 * emojiDiameter);
        final int smileArcDegree = 120;
        final int glassesXDistance = (int) (0.17 * emojiDiameter);
        final int glassesYDistance = (int) (0.15 * emojiDiameter);
        final int glassesWidth = (int) (0.35 * emojiDiameter);
        final int glassesHeight = (int) (0.25 * emojiDiameter);
        final int glassesTempleDiameter = (int) (1.5 * emojiDiameter);
        final int glassesTempleYDistance = (int) (0.54 * emojiDiameter);
        final int glassesArcDegree = 77;
        drawBaseEmoji(g, width, height);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EMOJI_STROKE_WIDTH));
        // Smile
        g2.drawArc((width - smileDiameter) / 2, (height - smileDiameter) / 2, smileDiameter, smileDiameter, 270 - smileArcDegree / 2, smileArcDegree);
        // Right glasses
        g2.fillOval((width - glassesWidth) / 2 + glassesXDistance, (height - glassesHeight) / 2 - glassesYDistance, glassesWidth, glassesHeight);
        // Left glasses
        g2.fillOval((width - glassesWidth) / 2 - glassesXDistance, (height - glassesHeight) / 2 - glassesYDistance, glassesWidth, glassesHeight);
        // Glasses' temple
        g2.drawArc((width - glassesTempleDiameter) / 2, (height - glassesTempleDiameter) / 2 + glassesTempleYDistance, glassesTempleDiameter, glassesTempleDiameter, 90 - glassesArcDegree / 2, glassesArcDegree);
    }

    public static void drawLoseEmoji(Graphics g, int width, int height) {
        final int emojiDiameter = (int) (height * EMOJI_SCALE);
        final int frownDiameter = (int) (0.6 * emojiDiameter);
        final int frownXDistance = (int) (0.4 * emojiDiameter);
        final int frownArcDegree = 120;
        final int fontSize = (int) (0.4 * emojiDiameter);
        final int eyeXDistance = (int) (0.15 * emojiDiameter);
        final int eyeYDistance = (int) (0.25 * emojiDiameter);
        drawBaseEmoji(g, width, height);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EMOJI_STROKE_WIDTH));
        // Frown
        g2.drawArc((width - frownDiameter) / 2, (height - frownDiameter) / 2 + frownXDistance, frownDiameter, frownDiameter, 90 - frownArcDegree / 2, frownArcDegree);
        g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
        final int eyeWidth = g.getFontMetrics().stringWidth("x");
        // Right dead eye
        g.drawString("x", (width - eyeWidth) / 2 + eyeXDistance, (height + fontSize) / 2 - eyeYDistance);
        // Right dead eye
        g.drawString("x", (width - eyeWidth) / 2 - eyeXDistance, (height + fontSize) / 2 - eyeYDistance);
    }
}


