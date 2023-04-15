package com.wingedseal.minesweeper.utils;


public record Offset(int deltaX, int deltaY) {
    private static final Offset[] NEIGHBOR_OFFSETS = {
            // ADJACENT_OFFSETS
            new Offset(-1, 0),
            new Offset(0, -1),
            new Offset(0, 1),
            new Offset(1, 0),
            // CORNER_OFFSETS
            new Offset(-1, -1),
            new Offset(-1, 1),
            new Offset(1, -1),
            new Offset(1, 1),
    };

    private static final Offset[] ADJACENT_OFFSETS = {
            NEIGHBOR_OFFSETS[0],
            NEIGHBOR_OFFSETS[1],
            NEIGHBOR_OFFSETS[2],
            NEIGHBOR_OFFSETS[3],
    };

    private static final Offset[] CORNER_OFFSETS = {
            NEIGHBOR_OFFSETS[4],
            NEIGHBOR_OFFSETS[5],
            NEIGHBOR_OFFSETS[6],
            NEIGHBOR_OFFSETS[7],
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
