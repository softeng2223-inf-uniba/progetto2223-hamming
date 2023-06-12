package it.uniba.app.gioco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe di test per la classe Cella.
 * {@link Partita}
 */
class PartitaTest {
    /**
     * Test sul livello di inizializzazione della partita.
     * {@link Partita#Partita(String)}
     */
    @Test
    @DisplayName("Il livello della partita è uguale a quello di inizializzazione")
    void testLivello() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        assertEquals(partita.getLivello(), livello, "Il livello della partita non corrisponde "
                + "a quello di inizializzazione");
    }

    /**
     * Test che confronta numero di tentativi della partita con
     * quelli del livello di inizializzazione.
     * {@link Partita#Partita(String)}
     */
    @Test
    @DisplayName("Test sul numero di tentativi della partita con "
            + "quelli del livello di inizializzazione")
    void testTentativiRimastiIniziali() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        assertEquals(partita.getTentativiRimasti(), Configurazioni.getTentativi(livello),
                "Il numero di tentativi della partita non corrisponde a "
                        + "quello del livello di inizializzazione");
    }

    /**
     * Test sul posizionamento delle navi.
     * In particolare, verifica che il numero di celle piene
     * sia uguale al numero delle celle che occupano tutte le navi.
     * {@link Partita#posizionaNavi()}
     */
    @Test
    @DisplayName("Il numero di celle piene è uguale al numero delle celle che occupano tutte le navi")
    void testPosizionaNavi() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        partita.posizionaNavi();
        Griglia griglia;
        try {
            griglia = partita.getGriglia();
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
            return;
        }

        int cellePiene = 0;
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                if (!griglia.getCella(i, j).eVuota()) {
                    cellePiene++;
                }
            }
        }

        int celleNavi = 0;
        for (String tipologia : Configurazioni.getTipologieNavi()) {
            for (int i = 0; i < Configurazioni.getNumeroNaviPerTipologia(tipologia); i++) {
                celleNavi += Configurazioni.getLunghezzaNavi(tipologia);
            }
        }
        assertEquals(celleNavi, cellePiene, "Il numero di celle piene non corrisponde "
                + "al numero delle celle che occupano tutte le navi");
    }

    /**
     * Test sull'attacco di una cella.
     * In particolare, verifica che il numero di tentativi rimasti
     * sia decrementato di uno dopo un attacco andato a buon fine.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco di una cella vuota decrementa il numero di tentativi rimasti di uno")
    void testAttaccoDecrementaTentativi() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        int tentativiRimasti = partita.getTentativiRimasti();
        partita.attaccaGriglia(0, 0);
        assertEquals(tentativiRimasti - 1, partita.getTentativiRimasti(),
                "L'attacco di una cella vuota non decrementa di uno il numero di tentativi rimasti");
    }

    /**
     * Test sull'attacco di una cella.
     * In particolare, verifica che il numero di tentativi rimasti
     * non cambi dopo un attacco su una cella con nave.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco di una cella con nave non decrementa il numero di tentativi rimasti")
    void testAttaccoNonDecrementaTentativi() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        int tentativiRimasti = partita.getTentativiRimasti();
        partita.posizionaNavi();
        partita.attaccaGriglia(0, 0);
        assertEquals(tentativiRimasti, partita.getTentativiRimasti(),
                "L'attacco di una cella con nave varia il numero di tentativi rimasti");
    }


    /**  TODO Utile come test o già fatto in cella? Se utile da fare anche in griglia?
     * Test sull'attacco di una cella.
     * In particolare, verifica che una cella diventi colpita.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco da partita di una cella la rende colpita")
    void testAttaccoColpisceCella() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        partita.attaccaGriglia(0, 0);
        try {
            assertTrue(partita.getGriglia().getCella(0, 0).eColpita(),
                    "L'attacco da partita di una cella non la rende colpita");
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
        }
    }

    /* TODO
     * Test sull'attacco di una cella.
     * In particolare, verifica che sia lanciata un'eccezione quando
     * si attacca una cella già colpita.
     * {@link Partita#attaccaGriglia(int, int)}
     */

    /* TODO
     * Test sull'attacco di una cella.
     * In particolare, verifica che sia lanciata un'eccezione quando
     * si attacca fuori dalla griglia.
     */


}
