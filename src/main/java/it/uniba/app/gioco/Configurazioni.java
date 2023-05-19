package it.uniba.app.gioco;
import it.uniba.app.exceptions.LivelloNonEsistenteException;
import it.uniba.app.exceptions.TipologiaNonEsistenteException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe che contiene le configurazioni del gioco.
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
    private static final int RIGHE_GRIGLIA = 10;
    private static final int COLONNE_GRIGLIA = 10;

    public static int getRigheGriglia() {
        return RIGHE_GRIGLIA;
    }

    public static int getColonneGriglia() {
        return COLONNE_GRIGLIA;
    }


    //SEZIONE LIVELLI
    private  static final Map<String, Integer> CONFIGURAZIONE_LIVELLO = Map.of(
      "facile", 50,
      "medio", 30,
      "difficile", 10);

    /**
     * Restituisce il numero di tentativi di un livello di difficoltà.
     *
     * @param livello livello di difficoltà
     */
    public static int getTentativi(final String livello) {
        if (livello == null) {
            throw new LivelloNonEsistenteException("Livello non impostato");
        } else if (!CONFIGURAZIONE_LIVELLO.containsKey(livello)) {
            throw new LivelloNonEsistenteException("Livello " + livello + " non esiste");
        }
        return CONFIGURAZIONE_LIVELLO.get(livello);
    }

    //SEZIONE NAVI
    private static final ConfigurazioneNave PORTAEREI = new ConfigurazioneNave(1, 5);
    private static final ConfigurazioneNave CORAZZATA = new ConfigurazioneNave(2, 4);
    private static final ConfigurazioneNave INCROCIATORE = new ConfigurazioneNave(3, 3);
    private static final ConfigurazioneNave CACCIATORPEDINIERE = new ConfigurazioneNave(4, 2);

    private static final Map<String, ConfigurazioneNave> CONFIGURAZIONE_NAVI =
    new LinkedHashMap<String, ConfigurazioneNave>();

    static {
        CONFIGURAZIONE_NAVI.put("portaerei", PORTAEREI);
        CONFIGURAZIONE_NAVI.put("corazzata", CORAZZATA);
        CONFIGURAZIONE_NAVI.put("incrociatore", INCROCIATORE);
        CONFIGURAZIONE_NAVI.put("cacciatorpediniere", CACCIATORPEDINIERE);
    }

    public Map<String, ConfigurazioneNave> getConfigurazioneNavi() {
        return CONFIGURAZIONE_NAVI;
    }

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
