package it.uniba.app.gioco;

import it.uniba.app.exceptions.CellaGiaColpitaException;
import it.uniba.app.exceptions.FuoriDallaGrigliaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        try {
            partita.attaccaGriglia(0, 0);
        } catch (FuoriDallaGrigliaException | CellaGiaColpitaException e) {
            fail("L'attacco di una cella vuota non dovrebbe sollevare eccezioni");
        }
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
        int[] posNave = trovaCellaConNave(partita);

        try {
            partita.attaccaGriglia(posNave[0], posNave[1]);
        } catch (FuoriDallaGrigliaException | CellaGiaColpitaException e) {
            fail("L'attacco di una cella vuota non dovrebbe sollevare eccezioni");
        }

        assertEquals(tentativiRimasti, partita.getTentativiRimasti(),
                "L'attacco di una cella con nave varia il numero di tentativi rimasti");
    }

    /**
     * Trova le coordinate di una cella occupata da una nave.
     *
     * @param partita partita in cui cercare la cella.
     */
    private int[] trovaCellaConNave(final Partita partita) {
        Griglia griglia;
        try {
            griglia = partita.getGriglia();
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
            return new int[]{0, 0};
        }
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                if (!griglia.getCella(i, j).eVuota()) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }


    /**
     * Test sull'attacco di una cella.
     * In particolare, verifica che una cella diventi colpita.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco da partita di una cella la rende colpita")
    void testAttaccoColpisceCella() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        try {
            partita.attaccaGriglia(0, 0);
        } catch (FuoriDallaGrigliaException | CellaGiaColpitaException e) {
            fail("L'attacco di una cella vuota non dovrebbe sollevare eccezioni");
        }

        boolean colpita = false;
        try {
            colpita = partita.getGriglia().getCella(0, 0).eColpita();
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
        }

        assertTrue(colpita, "L'attacco da partita di una cella non la rende colpita");
    }

    /**
     * Test sull'attacco di una cella.
     * In particolare, verifica che sia lanciata un'eccezione quando
     * si attacca una cella già colpita.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco di una cella già colpita lancia un'eccezione")
    void testAttaccoCellaColpita() {
        String livello = "facile";
        Partita partita = new Partita(livello);
        try {
            partita.attaccaGriglia(0, 0);
        } catch (FuoriDallaGrigliaException | CellaGiaColpitaException e) {
            fail("L'attacco di una cella vuota non dovrebbe sollevare eccezioni");
        }
        assertThrows(CellaGiaColpitaException.class, () -> partita.attaccaGriglia(0, 0),
                "L'attacco di una cella già colpita non lancia un'eccezione");
    }


    /**
     * Test sull'attacco di una cella.
     * In particolare, verifica che sia lanciata un'eccezione quando
     * si attacca fuori dalla griglia.
     * {@link Partita#attaccaGriglia(int, int)}
     */
    @Test
    @DisplayName("L'attacco di una cella fuori dalla griglia lancia un'eccezione")
    void testAttaccoCellaFuoriGriglia() {
        String livello = "facile";
        final int colonnaFuoriGriglia = 10;
        Partita partita = new Partita(livello);
        assertThrows(FuoriDallaGrigliaException.class, () -> partita.attaccaGriglia(0, colonnaFuoriGriglia),
                "L'attacco di una cella fuori dalla griglia non lancia un'eccezione");
    }


}
