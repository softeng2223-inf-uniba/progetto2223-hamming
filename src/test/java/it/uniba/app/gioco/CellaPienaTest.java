package it.uniba.app.gioco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe di test per la classe CellaPiena.
 * {@link CellaPiena}
 */
class CellaPienaTest {
    /**
     * Test sul metodo eVuota di una cella piena.
     * {@link CellaPiena#eVuota()}
     */
    @Test
    @DisplayName("Una cella piena non è mai vuota")
    void testCellaNonVuota() {
        Nave nave = new Nave("cacciatorpediniere");
        CellaPiena cella = new CellaPiena(nave);
        assertFalse(cella.eVuota(), "Una cella piena risulta vuota");
    }

    /**
     * Test sul metodo getNave di una cella piena.
     * Deve restituire la nave con cui è stata inizializzata la cella.
     * {@link CellaPiena#getNave()}
     */
    @Test
    @DisplayName("Una cella piena contiene la nave passata al costruttore")
    void testCellaConNave() {
        String tipologia = "cacciatorpediniere";
        Nave nave = new Nave(tipologia);
        CellaPiena cella = new CellaPiena(nave);
        assertEquals(nave, cella.getNave(), "La cella piena non contiene la nave "
                + "passata al costruttore");
    }

    /**
     * Test sull'attacco su una cella piena.
     * Si verifica che l'attacco sia andato a buon fine e indica
     * che è stata colpita una nave.
     * {@link CellaPiena#attacca()}
     */
    @Test
    @DisplayName("L'attacco su una cella con nave integra indica che è stata colpita una nave")
    void testAttaccoSuCellaPiena() {
        String tipologia = "cacciatorpediniere";
        Nave nave = new Nave(tipologia);
        CellaPiena cella = new CellaPiena(nave);
        assertEquals(EsitoColpo.COLPITO, cella.attacca(), "L'attacco su una cella con una nave integra indica "
                + "che non è stata colpita una nave");
    }

    /**
     * Test sull'attacco su una cella piena.
     * Si verifica che l'attacco abbia modificato lo stato della cella.
     * {@link CellaPiena#attacca()}
     */
    @Test
    @DisplayName("L'attacco su una cella piena la rende colpita")
    void testAttaccoColpisceCellaPiena() {
        String tipologia = "cacciatorpediniere";
        Nave nave = new Nave(tipologia);
        CellaPiena cella = new CellaPiena(nave);
        cella.attacca();
        assertTrue(cella.eColpita(), "L'attacco su una cella piena non la rende colpita");
    }

    /**
     * Test sull'attacco su una cella piena.
     * Si verifica che l'attacco sulla cella abbia colpito anche la nave.
     * {@link CellaPiena#attacca()}
     */
    @Test
    @DisplayName("L'attacco su una cella piena colpisce la nave")
    void testAttaccoCellaPienaColpisceNave() {
        String tipologia = "cacciatorpediniere";
        Nave nave = new Nave(tipologia);
        int celleNave = nave.getCelleRimanenti();
        CellaPiena cella = new CellaPiena(nave);
        cella.attacca();
        assertEquals(celleNave - 1, cella.getNave().getCelleRimanenti(),
                "L'attacco su una cella piena non colpisce la nave");
    }

    // todo test su attacco su cella già colpita, nave affondata, ecc.

}
