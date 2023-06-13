package it.uniba.app.gioco;
import it.uniba.app.exceptions.LivelloNonEsistenteException;
import it.uniba.app.exceptions.TipologiaNonEsistenteException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe che contiene le configurazioni del gioco.
 *
 * @author Gruppo Hamming
 */
public final class Configurazioni {

    private Configurazioni() { }

    // SEZIONE PARTITA
    private static final int TENTATIVI_MASSIMI_POSIZIONAMENTO = 100;

    /**
     * Metodo che restituisce il numero massimo di tentativi per posizionare casualmente una nave sulla griglia.
     * @return numero massimo di tentativi
     */
    public static int getTentativiMassimiPosizionamento() {
        return TENTATIVI_MASSIMI_POSIZIONAMENTO;
    }

    //SEZIONE GRIGLIA

    public static final int DIMENSIONI_GRIGLIA_STANDARD = 10;
    public static final int DIMENSIONI_GRIGLIA_LARGE = 18;
    public static final int DIMENSIONI_GRIGLIA_EXTRA_LARGE = 26;

    private static int righeGriglia = DIMENSIONI_GRIGLIA_STANDARD;
    private static int colonneGriglia = DIMENSIONI_GRIGLIA_STANDARD;

    public static int getRigheGriglia() {
        return righeGriglia;
    }

    public static void setRigheGriglia(final int righe) {
        righeGriglia = righe;
    }

    public static int getColonneGriglia() {
        return colonneGriglia;
    }

    public static void setColonneGriglia(final int colonne) {
        colonneGriglia = colonne;
    }


    //SEZIONE LIVELLI
    private static final Map<String, Integer> CONFIGURAZIONE_LIVELLO = new LinkedHashMap<String, Integer>();
    private static final Integer LIVELLO_FACILE = 50;
    private static final Integer LIVELLO_MEDIO = 30;
    private static final Integer LIVELLO_DIFFICILE = 10;
    private static final String LIVELLO_DEFAULT = "medio";


    /**
     * Modifica il numero di tentativi di un livello di difficoltà.
     * @param livello livello di difficoltà
     * @param tentativi numero di tentativi
     */
    public static void setTentativi(final String livello, final int tentativi) {
        if (!CONFIGURAZIONE_LIVELLO.containsKey(livello)) {
            throw new LivelloNonEsistenteException("Livello " + livello + " non esiste");
        }
        CONFIGURAZIONE_LIVELLO.put(livello, tentativi);
    }


    /**
     * Restituisce il numero di tentativi di un livello di difficoltà.
     *
     * @param livello livello di difficoltà
     * @return numero di tentativi
     */
    public static int getTentativi(final String livello) {
        if (!CONFIGURAZIONE_LIVELLO.containsKey(livello)) {
            throw new LivelloNonEsistenteException("Livello " + livello + " non esiste");
        }
        return CONFIGURAZIONE_LIVELLO.get(livello);
    }

    /**
     * Crea un nuovo livello di difficoltà custom.
     * @param tentativi numero di tentativi del livello custom.
     */
    public static void setCustomTentativi(final int tentativi) {
        CONFIGURAZIONE_LIVELLO.put("custom", tentativi);
    }

    /**
     * Elimina il livello di difficoltà custom.
     */
    public static void deleteCustomTentativi() {
        if (!CONFIGURAZIONE_LIVELLO.containsKey("custom")) {
            CONFIGURAZIONE_LIVELLO.remove("custom");
        }
    }

    /**
     * Restituisce il livello di default.
     * @return livello di default
     */
    public static String getLivelloDefault() {
        return LIVELLO_DEFAULT;
    }

    //SEZIONE NAVI
    private static final ConfigurazioneNave PORTAEREI = new ConfigurazioneNave(1, 5);
    private static final ConfigurazioneNave CORAZZATA = new ConfigurazioneNave(2, 4);
    private static final ConfigurazioneNave INCROCIATORE = new ConfigurazioneNave(3, 3);
    private static final ConfigurazioneNave CACCIATORPEDINIERE = new ConfigurazioneNave(4, 2);

    private static final Map<String, ConfigurazioneNave> CONFIGURAZIONE_NAVI =
    new LinkedHashMap<String, ConfigurazioneNave>();

    /**
     * Restituisce la lunghezza di una tipologia di navi.
     * @param tipologia tipologia di nave
     */
    public static int getLunghezzaNavi(final String tipologia) {
        if (!CONFIGURAZIONE_NAVI.containsKey(tipologia)) {
            throw new TipologiaNonEsistenteException("Tipologia di nave non valida");
        }
        return CONFIGURAZIONE_NAVI.get(tipologia).getLunghezza();
    }

    /**
     * Restituisce il numero di navi di una tipologia.
     * @param tipologia tipologia di nave
     * @return numero di navi di una tipologia
     */
    public static int getNumeroNaviPerTipologia(final String tipologia) {
        if (!CONFIGURAZIONE_NAVI.containsKey(tipologia)) {
            throw new TipologiaNonEsistenteException("Tipologia di nave non valida");
        }

        return CONFIGURAZIONE_NAVI.get(tipologia).getNumero();
    }

    /**
     * Restituisce le tipologie di navi.
     * @return tipologie di navi
     */
    public static Set<String> getTipologieNavi() {
        return CONFIGURAZIONE_NAVI.keySet();
    }

    // SEZIONE INIZIALIZZAZIONE CONFIGURAZIONI LIVELLI E NAVI
    static {
        CONFIGURAZIONE_LIVELLO.put("facile", LIVELLO_FACILE);
        CONFIGURAZIONE_LIVELLO.put("medio", LIVELLO_MEDIO);
        CONFIGURAZIONE_LIVELLO.put("difficile", LIVELLO_DIFFICILE);

        CONFIGURAZIONE_NAVI.put("portaerei", PORTAEREI);
        CONFIGURAZIONE_NAVI.put("corazzata", CORAZZATA);
        CONFIGURAZIONE_NAVI.put("incrociatore", INCROCIATORE);
        CONFIGURAZIONE_NAVI.put("cacciatorpediniere", CACCIATORPEDINIERE);
    }
}

/**
 * Classe che rappresentale le configurazioni di una tipologia di nave.
 * Contiene il numero di navi della stessa tipologia, la lunghezza e il simbolo
 * da stampare per
 * far vedere la nave sulla griglia.
 * Contiene i metodi per accedere alle configurazioni.
 *
 * @author Gruppo Hamming
 */
class ConfigurazioneNave {
    private int numero;
    private int lunghezza;

    ConfigurazioneNave(final int num, final int lun) {
        numero = num;
        lunghezza = lun;
    }

    public int getNumero() {
        return numero;
    }

    public int getLunghezza() {
        return lunghezza;
    }
}
