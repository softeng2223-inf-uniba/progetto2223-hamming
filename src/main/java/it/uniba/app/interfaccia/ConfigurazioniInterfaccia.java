package it.uniba.app.interfaccia;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe che contiene le configurazioni dell'interfaccia del gioco.
 * @author Gruppo Hamming
 */
public final class ConfigurazioniInterfaccia {

    private ConfigurazioniInterfaccia() {
    }

    //SEZIONE COMANDI
    private static final Map<String, Comando> COMANDI = new LinkedHashMap<String, Comando>() {
        {
            put("esci", new Esci());
            put("facile", new Facile());
            put("medio", new Medio());
            put("difficile", new Difficile());
            put("mostralivello", new MostraLivello());
        }
    };

    /**
     * Metodo che restituisce l'oggetto comando associato alla stringa (chiave) passata come parametro.
     *
     * @param comando stringa che rappresenta il comando (chiave della LinkedHashMap)
     */
    public static Comando getComando(final String comando) {
        return COMANDI.get(comando);
    }

}
