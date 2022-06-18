package com.example.services;

import java.util.Arrays;

public class MutantDetector {
    private static final int MUTATION_THRESHOLD = 2;

    public static boolean isMutant(String[] dna) {
        if(!isValidMatrix(dna)) {
            throw new IllegalArgumentException("DNA sequence must be an square matrix of chars ATCG");
        }

        char[][] sequence = Arrays.stream(dna).map(String::toCharArray).toArray(char[][]::new);
        return hasEnoughMutations(sequence);
    }

    private static boolean isValidMatrix(String[] dna) {
        return Arrays.stream(dna).noneMatch(i -> i.length() != dna.length || !i.matches("^[ATCG]+$"));
    }

    private static boolean hasEnoughMutations(char[][] dna) {
        int horizontal = 0, vertical = 0, left = 0, right = 0;

        for (int row = 0; row <= dna.length; row++) {
            for (int col = 0; col <= dna.length; col++) {
                if (hasHorizontalMutation(dna, row, col)) {
                    horizontal++;
                }

                if (hasVerticalMutation(dna, row, col)) {
                    vertical++;
                }

                if (hasLeftMutation(dna, row, col)) {
                    left++;
                }

                if (hasRightMutation(dna, row, col)) {
                    right++;
                }

                if(horizontal + vertical + left + right >= MUTATION_THRESHOLD) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasHorizontalMutation(char[][] dna, int row, int col) {
        return col <= dna.length - 4
                && dna[row][col] == dna[row][col + 1]
                && dna[row][col] == dna[row][col + 2]
                && dna[row][col] == dna[row][col + 3];
    }

    private static boolean hasVerticalMutation(char[][] dna, int row, int col) {
        return row <= dna.length - 4
                && dna[row][col] == dna[row + 1][col]
                && dna[row][col] == dna[row + 2][col]
                && dna[row][col] == dna[row + 3][col];
    }

    private static boolean hasLeftMutation(char[][] dna, int row, int col) {
        return col <= dna.length - 4
                && row <= dna.length - 4
                && dna[row][col] == dna[row + 1][col + 1]
                && dna[row][col] == dna[row + 2][col + 2]
                && dna[row][col] == dna[row + 3][col + 3];
    }

    private static boolean hasRightMutation(char[][] dna, int row, int col) {
        return col > 3
                && row <= dna.length - 4
                && dna[row][col] == dna[row + 1][col - 1]
                && dna[row][col] == dna[row + 2][col - 2]
                && dna[row][col] == dna[row + 3][col - 3];
    }
}
