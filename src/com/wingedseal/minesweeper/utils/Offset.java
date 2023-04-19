package com.wingedseal.minesweeper.utils;


public record Offset(int deltaX, int deltaY) {

    private static final Offset[] ADJACENT_OFFSETS = {
            new Offset(-1, 0),
            new Offset(0, -1),
            new Offset(0, 1),
            new Offset(1, 0),
    };

    private static final Offset[] CORNER_OFFSETS = {
            new Offset(-1, -1),
            new Offset(-1, 1),
            new Offset(1, -1),
            new Offset(1, 1),
    };

    private static final Offset[] NEIGHBOR_OFFSETS = {
            ADJACENT_OFFSETS[0],
            ADJACENT_OFFSETS[1],
            ADJACENT_OFFSETS[2],
            ADJACENT_OFFSETS[3],
            CORNER_OFFSETS[0],
            CORNER_OFFSETS[1],
            CORNER_OFFSETS[2],
            CORNER_OFFSETS[3],
    };

    public static Offset[] get4AdjacentOffsets() {
        return ADJACENT_OFFSETS;
    }

    public static Offset[] get4CornerOffsets() {
        return CORNER_OFFSETS;
    }

    public static Offset[] get8NeighborOffsets() {
        return NEIGHBOR_OFFSETS;
    }

    public static boolean isNotInRange(int x, int y, int sizeX, int sizeY) {
        return (x < 0 || x >= sizeX || y < 0 || y >= sizeY);
    }
}
