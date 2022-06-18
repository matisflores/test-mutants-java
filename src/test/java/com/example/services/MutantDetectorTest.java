package com.example.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    @Test
    void isMutant() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    void badCharacter() {
        String[] dna = {"LTGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertThrows(IllegalArgumentException.class, () -> MutantDetector.isMutant(dna));
    }
}