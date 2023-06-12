package it.uniba.app.gioco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Classe di test per la classe Cella.
 * {@link Cella}
 */
class CellaTest {

    /**
     * Test sul metodo eVuota di una cella non piena.
     * {@link Cella#eVuota()}
     */
    @Test
    @DisplayName("Una cella non piena è sempre vuota")
    void testCellaVuota() {
        Cella cella = new Cella();
        assertTrue(cella.eVuota(), "Una cella non piena risulta anche non vuota");
    }

    /**
     * Test sul metodo getNave di una cella non piena.
     * {@link Cella#getNave()}
     */
    @Test
    @DisplayName("Una cella non piena non contiene alcuna nave")
    void testCellaSenzaNave() {
        Cella cella = new Cella();
        assertNull(cella.getNave(), "Una cella non piena contiene una nave");
    }

    /**
     * Test sull'attacco su una cella non piena.
     * Si verifica che l'attacco sia andato a buon fine.
     * {@link Cella#attacca()}
     */
    @Test
    @DisplayName("L'attacco su una cella senza nave indica che è non è stata colpita alcuna nave")
    void testAttaccoSuCellaVuota() {
        Cella cella = new Cella();
        assertFalse(cella.attacca(), "L'attacco su una cella senza nave ha colpito una nave");
    }

    /**
     * Test sull'attacco su una cella non piena.
     * Si verifica che l'attacco abbia modificato lo stato della cella.
     * {@link Cella#attacca()}
     */
    @Test
    @DisplayName("L'attacco su una cella non piena la rende colpita")
    void testAttaccoColpisceCellaVuota() {
        Cella cella = new Cella();
        cella.attacca();
        assertTrue(cella.eColpita(), "L'attacco su una cella non piena non la rende colpita");
    }

    // TODO test su attacco su cella gia colpita...




}
