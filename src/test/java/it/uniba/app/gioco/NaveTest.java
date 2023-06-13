package it.uniba.app.gioco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe di test per la classe Nave.
 * {@link Nave}
 */
class NaveTest {
    /**
     * Test sulla tipologia di inizializzazione della nave.
     * {@link Nave#Nave(String)}
     */
    @Test
    @DisplayName("La tipologia della nave corrisponde a quella passata al costruttore")
    void testGetTipologia() {
        String tipologia = "cacciatorpediniere";
        final Nave nave = new Nave(tipologia);
        assertEquals(nave.getTipologia(), tipologia, "La tipologia della nave non viene inizializzata correttamente");
    }

    /**
     * Test sulla lunghezza della nave.
     * {@link Nave#getLunghezza()}
     */
    @Test
    @DisplayName("La lunghezza della nave è coerente con la tipologia")
    void testGetLunghezza() {
        String tipologia = "cacciatorpediniere";
        final Nave nave = new Nave(tipologia);
        assertEquals(nave.getLunghezza(), Configurazioni.getLunghezzaNavi(tipologia),
                "La lunghezza della nave è diversa rispetto alla tipologia");
    }

    /**
     * Test sulle celle rimanenti della nave.
     * Verifica che il numero di celle rimanenti sia uguale alla lunghezza della nave,
     * non essendo stata colpita.
     * {@link Nave#getCelleRimanenti()}
     */
    @Test
    @DisplayName("Il numero di celle di una nave non colpita corrisponde a quello della lunghezza della nave")
    void testGetCelleRimanenti() {
        String tipologia = "cacciatorpediniere";
        final Nave nave = new Nave(tipologia);
        assertEquals(nave.getCelleRimanenti(), Configurazioni.getLunghezzaNavi(tipologia),
                "Il numero di celle rimanenti di una nave non viene inizializzato correttamente");
    }

    /**
     * Test sull'attacco su una nave.
     * Si verifica che l'attacco abbia diminuito di uno il numero di celle rimanenti.
     * {@link Nave#colpisciNave()}
     */
    @Test
    @DisplayName("L'attacco su una nave diminuisce di uno il numero di celle rimanenti")
    void testColpisciNave() {
        String tipologia = "incrociatore";
        final Nave nave = new Nave(tipologia);
        nave.colpisciNave();
        assertEquals(nave.getCelleRimanenti(), Configurazioni.getLunghezzaNavi(tipologia) - 1,
                "L'attacco su una nave non diminuisce il numero di celle rimanenti");
    }

    /**
     * Test sull'affondamento di una nave.
     * Si verifica che l'attacco abbia diminuito di uno il numero di celle rimanenti.
     * {@link Nave#colpisciNave()}
     */
    @Test
    @DisplayName("L'attacco su tutte le celle di una nave la fa affondare")
    void testEAffondata() {
        String tipologia = "incrociatore";
        final Nave nave = new Nave(tipologia);
        for (int i = 0; i < Configurazioni.getLunghezzaNavi(tipologia); i++) {
            nave.colpisciNave();
        }
        assertTrue(nave.eAffondata(), "La nave non risulta affondata dopo aver colpito tutte le sue celle");
    }

    /**
     * Test sull'attacco a una nave affondata.
     * {@link Nave#colpisciNave()}
     */
    @Test
    @DisplayName("L'attacco su una nave affondata non diminuisce il numero di celle rimanenti")
    void testColpisciNaveAffondata() {
        String tipologia = "cacciatorpediniere";
        final Nave nave = new Nave(tipologia);
        for (int i = 0; i < Configurazioni.getLunghezzaNavi(tipologia); i++) {
            nave.colpisciNave();
        }
        nave.colpisciNave();
        assertEquals(nave.getCelleRimanenti(), 0,
                "L'attacco su una nave affondata diminuisce il numero di celle rimanenti");
    }
}
