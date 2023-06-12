package it.uniba.app.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Classe di test per la classe Util.
 * {@link Util}
 */
class UtilTest {
    /**
     * Test sul calcolo della distanza di Levenshtein su due parole uguali.
     * Verifica che la distanza sia zero.
     * {@link Util#getLevenshteinDistance(String, String)}
     */
    @Test
    @DisplayName("Test sulla distanza di Levenshtein tra due parole uguali")
    void testGetLevenshteinDistance() {
        assertEquals(0, Util.getLevenshteinDistance("gioca", "gioca"),
                "La distanza di Levenshtein tra due parole uguali non è zero");
    }

    /**
     * Test sul suggerimento di un comando già corretto.
     * {@link Util#suggestCommand(String)}
     */
    @Test
    @DisplayName("Test sul suggerimento di un comando già corretto")
    void testSuggestCommand() {
        assertEquals("gioca", Util.suggestCommand("gioca"), "Suggerimento errato partendo da un comando corretto");
    }

    /**
     * Test sul suggerimento di un comando errato.
     * {@link Util#suggestCommand(String)}
     */
    @Test
    @DisplayName("Test sul suggerimento di un comando partendo da un comando errato")
    void testSuggestCommandErrato() {
        assertEquals("gioca", Util.suggestCommand("goica"), "Suggerimento errato partendo un comando errato");
    }


}
