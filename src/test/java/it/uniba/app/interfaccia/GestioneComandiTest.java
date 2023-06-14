package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.ComandiException;
import it.uniba.app.exceptions.ParametriNonCorrettiException;
import it.uniba.app.exceptions.PartitaGiaIniziataException;
import it.uniba.app.exceptions.PartitaNonIniziataException;
import it.uniba.app.gioco.Configurazioni;
import it.uniba.app.gioco.Griglia;
import it.uniba.app.util.SimulaStdIOStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Classe di test per la classe GestioneComandi.
 * {@link GestioneComandi}
 */
class GestioneComandiTest extends SimulaStdIOStream {
    /**
     * Ripristina la partita.
     */
    @AfterEach
    void tearDownEach() {
        GestioneComandi.cancellaPartita();
        GestioneComandi.setTempo(0);
    }

    /**
     * Test sull'inizializzazione della partita.
     * Verifica che la partita non possa essere inizializzata se è già iniziata.
     * {@link GestioneComandi#inizializzaPartita()}
     */
    @Test
    @DisplayName("La partita non viene inizializzata se è già iniziata")
    void testInizializzaPartitaGiaIniziata() {
        ComandiUtil.iniziaPartita();
        assertThrows(PartitaGiaIniziataException.class, GestioneComandi::inizializzaPartita,
                "La partita viene inizializzata anche se è già iniziata");
    }

    /**
     * Test sull'attacco.
     * Verifica che l'attacco venga eseguito correttamente.
     * {@link GestioneComandi#attacco(String)}
     */
    @Test
    @DisplayName("L'attacco colpisce una cella")
    void testAttacco() {
        ComandiUtil.iniziaPartita();
        GestioneComandi.getPartita().posizionaNavi();
        try {
            GestioneComandi.attacco("a-1");
        } catch (PartitaNonIniziataException e) {
            fail("La partita non è iniziata");
            return;
        }
        try {
            assertTrue(GestioneComandi.getPartita().getGriglia().getCella(0, 0).eColpita(),
                    "L'attacco non colpisce la cella");
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
        }
    }

    /**
     * Test sull'attacco.
     * Verifica che l'attacco non venga eseguito se la partita non è iniziata.
     * {@link GestioneComandi#attacco(String)}
     */
    @Test
    @DisplayName("L'attacco non colpisce una cella se la partita non è iniziata")
    void testAttaccoPartitaNonIniziata() {
        assertThrows(PartitaNonIniziataException.class, () -> GestioneComandi.attacco("A-1"),
                "L'attacco colpisce una cella anche se la partita non è iniziata");
    }

    /**
     * Test sull'attacco.
     * Verifica che la partita termini una volta affondate tutte le navi.
     * {@link GestioneComandi#attacco(String)}
     */
    @Test
    @DisplayName("La partita termina se tutte le navi sono affondate")
    void testFinePartitaNaviAffondate() {
        ComandiUtil.iniziaPartita();
        GestioneComandi.getPartita().posizionaNavi();
        Griglia griglia;
        try {
            griglia = GestioneComandi.getPartita().getGriglia();
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
            return;
        }
        // trova e attacca tutte le celle delle navi
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                if (!griglia.getCella(i, j).eVuota()) {
                    try {
                        GestioneComandi.attacco(((char) ('a' + j)) + "-" + (i + 1));
                    } catch (PartitaNonIniziataException e) {
                        fail("Partita non iniziata");
                        return;
                    }
                }
            }
        }

        assertFalse(GestioneComandi.partitaIniziata(), "La partita non è terminata con tutte le navi affondate");
    }

    /**
     * Test sull'attacco.
     * Verifica che la partita termini una volta terminati i tentativi.
     * {@link GestioneComandi#attacco(String)}
     */
    @Test
    @DisplayName("La partita termina se i tentativi sono terminati")
    void testFinePartitaTentativiTerminati() {
        int tentativi = Configurazioni.getTentativi(Configurazioni.getLivelloDefault());
        ComandiUtil.iniziaPartita();
        GestioneComandi.getPartita().posizionaNavi();
        Griglia griglia;
        try {
            griglia = GestioneComandi.getPartita().getGriglia();
        } catch (CloneNotSupportedException e) {
            fail("Clonazione della griglia non supportata");
            return;
        }
        // trova e attacca tutte le celle vuote in modo da terminare i tentativi
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                if (griglia.getCella(i, j).eVuota() && tentativi > 0) {
                    try {
                        GestioneComandi.attacco(((char) ('a' + j)) + "-" + (i + 1));
                        tentativi--;
                    } catch (PartitaNonIniziataException e) {
                        fail("Partita non iniziata");
                        return;
                    }
                }
            }
        }
        assertFalse(GestioneComandi.partitaIniziata(), "La partita non è terminata con i tentativi terminati");
    }

    /**
     * Test sull'attacco.
     * Verifica che la partita termini se il tempo è scaduto.
     * {@link GestioneComandi#attacco(String)}
     */
    @Test
    @DisplayName("La partita termina se il tempo è scaduto")
    void testFinePartitaTempoScaduto() {
        final int secondiInMinuto = 60;
        final int millisecondiInSecondo = 1000;
        GestioneComandi.setTempo(1);
        ComandiUtil.iniziaPartita();
        GestioneComandi.getPartita().posizionaNavi();
        // simula che la partita sia iniziata 1 minuto fa
        GestioneComandi.setTempoInizio(System.currentTimeMillis() - secondiInMinuto * millisecondiInSecondo);
        try {
            GestioneComandi.attacco("a-1");
        } catch (PartitaNonIniziataException e) {
            fail("Partita non iniziata");
            return;
        }
        assertFalse(GestioneComandi.partitaIniziata(), "La partita non è terminata con il tempo scaduto");
    }
}

/**
 * Util class for tests.
 */
final class ComandiUtil {
    /**
     * Private constructor.
     */
    private ComandiUtil() {
    }


    /**
     * Inizia una partita facendo fallire il test in caso di eccezione.
     */
    static void iniziaPartita() {
        try {
            GestioneComandi.inizializzaPartita();
        } catch (PartitaGiaIniziataException e) {
            fail("La partita è già iniziata");
        }
    }

    /**
     * Esegue un comando facendo fallire il test in caso di eccezione.
     *
     * @param comando   comando da eseguire
     * @param parametri parametri del comando
     */
    static void eseguiComando(final Comando comando, final String[] parametri) {
        try {
            comando.esegui(parametri);
        } catch (ComandiException e) {
            fail("Eccezione nell'esecuzione del comando: " + e.getMessage());
        }
    }
}

/**
 * Classe di test per il comando /esci.
 */
class EsciTest extends SimulaStdIOStream {
    /**
     * Ripristina dall'interruzione dell'applicazione.
     */
    @AfterEach
    void tearDown() {
        GestioneComandi.setContinua(true);
    }

    /**
     * Test sul comando /esci.
     * Da conferma al voler uscire e verifica se interrompe l'applicazione.
     * {@link Esci#esegui(String[])}
     */
    @Test
    @DisplayName("/esci interrompe l'applicazione")
    void testEsci() {
        Esci esci = new Esci();
        simulaInput("s");
        ComandiUtil.eseguiComando(esci, new String[0]);
        assertFalse(GestioneComandi.getContinua(), "/esci non ha interrotto l'applicazione");
    }

    /**
     * Test sul comando /esci con conferma negativa, dunque
     * il comando non dovrebbe modificare l'applicazione.
     * {@link Esci#esegui(String[])}
     */
    @Test
    @DisplayName("/esci con conferma negativa non interrompe l'applicazione")
    void testEsciConfermaNegativa() {
        Esci esci = new Esci();
        simulaInput("n");
        ComandiUtil.eseguiComando(esci, new String[0]);
        assertTrue(GestioneComandi.getContinua(), "/esci con conferma negativa ha interrotto l'applicazione");
    }

}

