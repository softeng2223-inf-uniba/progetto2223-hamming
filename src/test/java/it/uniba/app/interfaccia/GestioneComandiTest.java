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

/**
 * Classe di test per il comando /abbandona.
 * {@link Abbandona}
 */
class AbbandonaTest extends SimulaStdIOStream {
    /**
     * Termina la partita.
     */
    @AfterEach
    void tearDown() {
        GestioneComandi.cancellaPartita();
    }


    /**
     * Test sul comando /abbandona.
     * Da conferma al voler abbandonare la partita e verifica se interrompe la partita.
     * {@link Abbandona#esegui(String[])}
     */
    @Test
    @DisplayName("/abbandona interrompe la partita")
    void testAbbandona() {
        Abbandona abbandona = new Abbandona();
        ComandiUtil.iniziaPartita();
        simulaInput("s");
        ComandiUtil.eseguiComando(abbandona, new String[0]);
        assertFalse(GestioneComandi.partitaIniziata(), "/abbandona non ha interrotto la partita");
    }

    /**
     * Test sul comando /abbandona con conferma negativa, dunque
     * il comando non dovrebbe terminare la partita.
     * {@link Abbandona#esegui(String[])}
     */
    @Test
    @DisplayName("/abbandona con conferma negativa non interrompe la partita")
    void testAbbandonaConfermaNegativa() {
        Abbandona abbandona = new Abbandona();
        ComandiUtil.iniziaPartita();
        simulaInput("n");
        ComandiUtil.eseguiComando(abbandona, new String[0]);
        assertTrue(GestioneComandi.partitaIniziata(), "/abbandona con conferma negativa ha interrotto la partita");
    }

}

/**
 * Classe di test per il comando /tempo.
 * {@link Tempo}
 */
class TempoTest extends SimulaStdIOStream {
    /**
     * Termina la partita.
     */
    @AfterEach
    public void tearDown() {
        GestioneComandi.cancellaPartita();
    }

    /**
     * Test sul comando /tempo.
     * Imposta un limite di tempo di 20 minuti.
     * {@link Tempo#esegui(String[])}
     */
    @Test
    @DisplayName("/tempo 20 imposta un limite di tempo pari a 20 minuti")
    void testTempo() {
        final int limiteTempo = 20;
        Tempo tempo = new Tempo();
        ComandiUtil.eseguiComando(tempo, new String[]{Integer.toString(limiteTempo)});
        assertEquals(limiteTempo, GestioneComandi.getTempo(), "/tempo non imposta correttamente il limite di tempo");
    }

    /**
     * Test sul comando /tempo con parametro 0.
     * Il comando dovrebbe disattivare il limite di tempo.
     * {@link Tempo#esegui(String[])}
     */
    @Test
    @DisplayName("/tempo 0 disattiva il limite di tempo")
    void testTempoParametroZero() {
        final int limiteTempo = 0;
        Tempo tempo = new Tempo();
        ComandiUtil.eseguiComando(tempo, new String[]{Integer.toString(limiteTempo)});
        assertFalse(GestioneComandi.tempoImpostato(), "/tempo con parametro 0 non disattiva il limite di tempo");
    }

    /**
     * Test sul comando /tempo con parametro non valido.
     * Il comando non dovrebbe modificare il limite di tempo.
     * {@link Tempo#esegui(String[])}
     */
    @Test
    @DisplayName("/tempo parametroInvalido non modifica il limite di tempo")
    void testTempoParametroNonValido() {
        Tempo tempo = new Tempo();
        assertThrows(ParametriNonCorrettiException.class, () -> tempo.esegui(new String[]{"parametroInvalido"}));
    }

    /**
     * Test sul comando /tempo a partita iniziata.
     * Il comando non dovrebbe modificare il limite di tempo.
     * {@link Tempo#esegui(String[])}
     */
    @Test
    @DisplayName("/tempo non modifica il limite di tempo a partita iniziata")
    void testTempoPartitaIniziata() {
        Tempo tempo = new Tempo();
        ComandiUtil.iniziaPartita();
        assertThrows(PartitaGiaIniziataException.class, () -> tempo.esegui(new String[]{"20"}));
    }
}

/**
 * Classe di test per il comando /tentativi.
 * {@link Tentativi}
 */
class TentativiTest extends SimulaStdIOStream {
    /**
     * Ripristina la partita.
     */
    @AfterEach
    public void tearDown() {
        GestioneComandi.cancellaPartita();
    }

    /**
     * Test sul comando /tentativi.
     * Imposta un limite di tentativi pari a 15.
     * {@link Tentativi#esegui(String[])}
     */
    @Test
    @DisplayName("/tentativi 15 imposta un limite di tentativi pari a 15")
    void testTentativi() {
        final int limiteTentativi = 15;
        Tentativi tentativi = new Tentativi();
        ComandiUtil.eseguiComando(tentativi, new String[]{Integer.toString(limiteTentativi)});
        ComandiUtil.iniziaPartita();

        assertEquals(limiteTentativi, GestioneComandi.getPartita().getTentativiRimasti(),
                "/tentativi non imposta correttamente il limite di tentativi");
    }

    /**
     * Test sul comando /tentativi con parametro 0.
     * Il comando dovrebbe lanciare l'eccezione ParametriNonCorrettiException.
     * {@link Tentativi#esegui(String[])}
     */
    @Test
    @DisplayName("/tentativi 0 fallisce")
    void testTentativiParametroZero() {
        final int limiteTentativi = 0;
        Tentativi tentativi = new Tentativi();
        assertThrows(ParametriNonCorrettiException.class, () ->
                        tentativi.esegui(new String[]{Integer.toString(limiteTentativi)}),
                "/tentativi con parametro 0 non lancia ParametriNonCorrettiException");
    }

    /**
     * Test sul comando /tentativi con parametro non valido.
     * Il comando non dovrebbe modificare il limite di tentativi.
     * {@link Tentativi#esegui(String[])}
     */
    @Test
    @DisplayName("/tentativi parametroInvalido non modifica il limite di tentativi")
    void testTentativiParametroNonValido() {
        Tentativi tentativi = new Tentativi();
        assertThrows(ParametriNonCorrettiException.class, () -> tentativi.esegui(new String[]{"parametroInvalido"}),
                "/tentativi con parametro non valido non lancia ParametriNonCorrettiException");
    }

    /**
     * Test sul comando /tentativi a partita iniziata.
     * Il comando non dovrebbe modificare il limite di tentativi.
     * {@link Tentativi#esegui(String[])}
     */
    @Test
    @DisplayName("/tentativi non modifica il limite di tentativi a partita iniziata")
    void testTentativiPartitaIniziata() {
        Tentativi tentativi = new Tentativi();
        ComandiUtil.iniziaPartita();
        assertThrows(PartitaGiaIniziataException.class, () -> tentativi.esegui(new String[]{"20"}),
                "/tentativi non lancia PartitaGiaIniziataException a partita iniziata");
    }
}

/**
 * Classe di test per il comando /gioca.
 * {@link Gioca}
 */
class GiocaTest extends SimulaStdIOStream {
    /**
     * Ripristina la partita.
     */
    @AfterEach
    public void tearDown() {
        GestioneComandi.cancellaPartita();
    }

    /**
     * Test sul comando /gioca.
     * {@link Gioca#esegui(String[])}
     */
    @Test
    @DisplayName("/gioca inizia una nuova partita")
    void testGioca() {
        Gioca gioca = new Gioca();
        ComandiUtil.eseguiComando(gioca, new String[0]);
        assertTrue(GestioneComandi.partitaIniziata(), "/gioca non inizia una nuova partita");
    }

    /**
     * Test sul comando /gioca con partita in corso.
     * Il comando dovrebbe lanciare l'eccezione PartitaGiaIniziataException.
     * {@link Gioca#esegui(String[])}
     */
    @Test
    @DisplayName("/gioca a partita in corso fallisce")
    void testGiocaPartitaInCorso() {
        Gioca gioca = new Gioca();
        ComandiUtil.iniziaPartita();
        assertThrows(PartitaGiaIniziataException.class, () -> gioca.esegui(new String[0]),
                "/gioca con partita in corso non fallisce");
    }
}

abstract class DifficoltaTest extends SimulaStdIOStream {
    private final String difficolta;
    private final Comando comando;

    /**
     * Costruttore della classe di test.
     *
     * @param difficoltaParam difficoltà da impostare
     * @param comandoParam    comando da testare
     */
    DifficoltaTest(final String difficoltaParam, final Comando comandoParam) {
        difficolta = difficoltaParam;
        comando = comandoParam;
    }

    /**
     * Ripristina la difficoltà di default.
     */
    @AfterEach
    public void tearDown() {
        final int tentativiFacile = 50;
        final int tentativiMedio = 30;
        final int tentativiDifficile = 10;
        GestioneComandi.cancellaPartita();
        try {
            GestioneComandi.setLivello(Configurazioni.getLivelloDefault());
            Configurazioni.setTentativi("facile", tentativiFacile);
            Configurazioni.setTentativi("medio", tentativiMedio);
            Configurazioni.setTentativi("difficile", tentativiDifficile);
        } catch (PartitaGiaIniziataException e) {
            fail("Il ripristino della difficoltà ha lanciato un'eccezione");
        }
    }

    /**
     * Test sul comando di cambio difficoltà a partita in corso.
     * Il comando dovrebbe lanciare l'eccezione PartitaGiaIniziataException.
     */
    @Test
    @DisplayName("Il comando per il cambio della difficoltà a partita in corso fallisce")
    void testCambioDifficoltaPartitaInCorso() {
        ComandiUtil.iniziaPartita();
        assertThrows(PartitaGiaIniziataException.class, () -> comando.esegui(new String[0]),
                "Il comando per il cambio della difficoltà a partita in corso non fallisce");
    }

    /**
     * Test sul comando di cambio difficoltà con parametro valido.
     * Il comando dovrebbe modificare la difficoltà in base al parametro.
     */
    @Test
    @DisplayName("Il comando per il cambio della difficoltà con parametro 15 imposta correttamente i tentativi")
    void testCambioDifficoltaParametroValido() {
        final int tentativi = 15;
        try {
            comando.esegui(new String[]{Integer.toString(tentativi)});
        } catch (ComandiException e) {
            fail("Il comando per il cambio della difficoltà con parametro valido "
                    + "ha lanciato un'eccezione: + " + e.getMessage());
        }
        assertEquals(tentativi, Configurazioni.getTentativi(difficolta),
                "Il comando per il cambio della difficoltà con parametro non imposta correttamente i tentativi");
    }


    /**
     * Test sul comando di cambio difficoltà con parametro non valido.
     * Il comando non dovrebbe modificare la difficoltà.
     */
    @Test
    @DisplayName("Il comando per il cambio della difficoltà con parametro non valido fallisce")
    void testCambioDifficoltaParametroNonValido() {
        final String parametroNonValido = "parametroNonValido";
        assertThrows(ParametriNonCorrettiException.class, () -> comando.esegui(new String[]{parametroNonValido}),
                "Il comando per il cambio della difficoltà con parametro non valido non fallisce");
    }
}

/**
 * Classe di test per il comando /facile.
 * {@link Facile}
 */
class FacileTest extends DifficoltaTest {
    FacileTest() {
        super("facile", new Facile());
    }

    /**
     * Test sul comando /facile.
     * Imposta la difficoltà facile.
     * Il metodo è stato spostato da DifficoltaTest perché Checkstyle
     * non vedeva alcun metodo di test in questa classe di test.
     * {@link Facile#esegui(String[])}
     */
    @Test
    @DisplayName("/facile imposta correttamente la difficoltà")
    void testCambioDifficolta() {
        Comando comando = new Facile();
        ComandiUtil.eseguiComando(comando, new String[0]);
        assertEquals("facile", GestioneComandi.getLivello(),
                "/facile non imposta la difficoltà correttamente");
    }
}

/**
 * Classe di test per il comando /medio.
 * {@link Medio}
 */
class MedioTest extends DifficoltaTest {
    MedioTest() {
        super("medio", new Medio());
    }

    /**
     * Test sul comando /medio.
     * Imposta la difficoltà media.
     * Il metodo è stato spostato da DifficoltaTest perché Checkstyle
     * non vedeva alcun metodo di test in questa classe di test.
     * {@link Medio#esegui(String[])}
     */
    @Test
    @DisplayName("/medio imposta correttamente la difficoltà")
    void testCambioDifficolta() {
        Comando comando = new Medio();
        ComandiUtil.eseguiComando(comando, new String[0]);
        assertEquals("medio", GestioneComandi.getLivello(),
                "/medio non imposta la difficoltà correttamente");
    }
}

/**
 * Classe di test per il comando /difficile.
 * {@link Difficile}
 */
class DifficileTest extends DifficoltaTest {
    DifficileTest() {
        super("difficile", new Difficile());
    }

    /**
     * Test sul comando /difficile.
     * Imposta la difficoltà difficile.
     * Il metodo è stato spostato da DifficoltaTest perché Checkstyle
     * non vedeva alcun metodo di test in questa classe di test.
     * {@link Difficile#esegui(String[])}
     */
    @Test
    @DisplayName("/difficile imposta correttamente la difficoltà")
    void testCambioDifficolta() {
        Comando comando = new Difficile();
        ComandiUtil.eseguiComando(comando, new String[0]);
        assertEquals("difficile", GestioneComandi.getLivello(),
                "/difficile non imposta la difficoltà correttamente");
    }
}

