package it.uniba.app.gioco;
import it.uniba.app.exceptions.LivelloNonEsistenteException;

import java.util.Map;

/**
 * Classe che contiene le configurazioni del gioco.
 * @author Gruppo Hamming
 */
public final class Configurazioni {

    private Configurazioni() { }

    //SEZIONE GRIGLIA
    private static final int RIGHE_GRIGLIA = 10;
    private static final int COLONNE_GRIGLIA = 10;

    public static int getRigheGriglia() {
        return RIGHE_GRIGLIA;
    }

    public static int getColonneGriglia() {
        return COLONNE_GRIGLIA;
    }


    // sezione livelli
    private  static final Map<String, Integer> CONFIGURAZIONE_LIVELLO = Map.of(
      "facile", 50,
      "medio", 30,
      "difficile", 10);

    /**
     * Restituisce il numero di tentativi di un livello di difficoltà.
     *
     * @param livello livello di difficoltà
     */
     public  static  int getTentativi(final  String livello) {
         if (!CONFIGURAZIONE_LIVELLO.containsKey(livello)) {
         throw new LivelloNonEsistenteException("Livello " + livello + " non esiste");
         }
         return CONFIGURAZIONE_LIVELLO.get(livello);
     }
}
