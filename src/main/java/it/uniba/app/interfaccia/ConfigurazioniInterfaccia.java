package it.uniba.app.interfaccia;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigurazioniInterfaccia {

    private ConfigurazioniInterfaccia() {
    }

    //SEZIONE COMANDI
    private static final Map<String, Comando> COMANDI = new LinkedHashMap<String, Comando>() {
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
