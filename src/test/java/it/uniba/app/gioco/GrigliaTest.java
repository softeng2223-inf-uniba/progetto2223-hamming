package it.uniba.app.gioco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe di test per la classe Griglia.
 * {@link Griglia}
 */
class GrigliaTest {
    /**
     * Test sull'inizializzazione della griglia.
     * Verifica che le celle della griglia siano celle vuote.
     * {@link Griglia#Griglia()}
     */
    @Test
    @DisplayName("La griglia viene inizializzata con celle vuote")
    void testGriglia() {
        final Griglia griglia = new Griglia();
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                assertTrue(griglia.getCella(i, j).eVuota(), "La griglia viene inizializzata con una cella piena");
            }
        }
    }

    /**
     * Test sul posizionamento di una nave.
     * Verifica che la nave sia posizionata nella posizione giusta della griglia.
     * {@link Griglia#posizionaNave(Nave, int, int, boolean)}
     */
    @Test
    @DisplayName("Una nave viene inserita in A-1 in orizzontale")
    void testPosizionaNave() {
        String tipologia = "cacciatorpediniere";
        final Griglia griglia = new Griglia();
        Nave nave = new Nave(tipologia);
        griglia.posizionaNave(nave, 0, 0, true);
        for (int i = 0; i < nave.getLunghezza(); i++) {
            assertEquals(griglia.getCella(0, i).getNave(), nave,
                    "La nave non è stata inserita nella posizione corretta");
        }
    }

    /**
     * Test sul posizionamento di una nave.
     * Verifica che una nave non possa essere posizionata fuori dalla griglia.
     * {@link Griglia#posizionaNave(Nave, int, int, boolean)}
     */
    @Test
    @DisplayName("Una nave non viene inserita in C-10 in verticale (fuori dalla griglia)")
    void testPosizionaNaveFuoriGriglia() {
        String tipologia = "cacciatorpediniere";
        final int riga = 9;
        final int colonna = 2;
        boolean orizzontale = false;
        final Griglia griglia = new Griglia();
        Nave nave = new Nave(tipologia);
        assertFalse(griglia.posizionaNave(nave, riga, colonna, orizzontale),
                "La nave è stata inserita fuori dalla griglia");
    }

    /**
     * Test sul posizionamento di una nave.
     * Verifica che una nave non possa essere posizionata su celle già occupate.
     * {@link Griglia#posizionaNave(Nave, int, int, boolean)}
     */
    @Test
    @DisplayName("Una nave non viene inserita su celle già occupate")
    void testPosizionaNaveSuCellaOccupata() {
        String tipologia = "cacciatorpediniere";
        final Griglia griglia = new Griglia();
        Nave nave = new Nave(tipologia);
        griglia.posizionaNave(nave, 0, 0, true);
        assertFalse(griglia.posizionaNave(nave, 0, 0, true), "La nave è stata inserita su una cella già occupata");
    }

}
