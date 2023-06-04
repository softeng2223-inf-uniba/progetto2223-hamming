package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.ComandoNonEsistenteException;
import it.uniba.app.exceptions.ComandoNonFormattatoException;
import it.uniba.app.exceptions.PartitaGiaIniziataException;
import it.uniba.app.gioco.Configurazioni;
import it.uniba.app.gioco.Partita;
import it.uniba.app.util.Util;

/**
 * Classe che gestisce i comandi inseriti dall'utente e li esegue.
 *
 * @author Gruppo Hamming
 */
public final class GestioneComandi {
    private static Partita partita = null;
    private static Boolean continua = true;
    private static String livello = Configurazioni.getLivelloDefault();

    private GestioneComandi() {
    }

    /**
     * Restituisce la partita.
     */
    public static Partita getPartita() {
        return partita;
    }

    /**
     * Restituisce true se la partita è iniziata, false altrimenti.
     */
    public static boolean partitaIniziata() {
        return partita != null;
    }

    /**
     * Metodo che inizializza la partita.
     *
     * @throws PartitaGiaIniziataException
     */
    public static void inizializzaPartita() throws PartitaGiaIniziataException {
        if (partita != null) {
            throw new PartitaGiaIniziataException("Una partita è già in corso");
        }
        partita = new Partita(livello);
    }

    /**
     * Restituisce il livello di difficoltà impostato.
     */
    public static String getLivello() {
        return livello;
    }

    /**
     * Imposta il livello di difficoltà.
     */
    public static void setLivello(final String livelloParam) throws PartitaGiaIniziataException {
        if (partita != null) {
            throw new PartitaGiaIniziataException("Non puoi cambiare difficoltà durante una partita");
        }
        livello = livelloParam;
        System.out.println("La difficoltà è stata cambiata.");
    }

    /**
     * Imposta il valore di continua.
     */
    public static void setContinua(final Boolean continuaParam) {
        continua = continuaParam;
    }

    /**
     * Restituisce il valore di continua.
     */
    public static Boolean getContinua() {
        return continua;
    }

    /**
     * restituisce true se input è un comando altrimenti false.
     * @param input input da controllare
     * @return true se input è un comando altrimenti false
     */
    static boolean eComando(final String input) {
        return input.startsWith("/");
    }

    /**
     * Ciclo principale del menu.
     */
    public static void mainLoop() {
        continua = true;
        while (continua) {
            try {
                String input = leggiComando();
                chiamaComando(input);
            } catch (ComandoNonEsistenteException | ComandoNonFormattatoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Legge un comando da tastiera.
     */
    public static String leggiComando() throws ComandoNonFormattatoException {
        System.out.println("\nInserisci un comando: ");
        System.out.print("> ");

        String input = Util.getString();
        if (!eComando(input)) {
            throw new ComandoNonFormattatoException(input);
        }
        input = input.substring(1).toLowerCase();
        return input;
    }

    /**
     * Esegue il comando specificato.
     *
     * @param comando nome del comando da eseguire
     */
    public static void chiamaComando(final String comando) throws ComandoNonEsistenteException {
        Comando c = ConfigurazioniInterfaccia.getComando(comando.toLowerCase());

        if (c != null) {
            c.esegui();
        } else {
            throw new ComandoNonEsistenteException(comando);
        }
    }
}

class Esci extends Comando {
    Esci() {
        super("esci", "utility");
    }

    String getDescrizione() {
        return "Chiude il programma";
    }

    void esegui() {
        String input;
        while (true) {
            System.out.print("Conferma l'uscita dal programma(s/n): ");
            input = Util.getString();
            if ("s".equals(input) || "n".equals(input)) {
                break;
            }
            System.out.println("Inserire solo s o n");
        }

        if ("s".equals(input)) {
            System.out.println("Uscita dal programma");
            GestioneComandi.setContinua(false);
        } else {
            System.out.println("Uscita annullata");
        }
    }
}

class Facile extends Comando {
    Facile() {
        super("facile", "difficolta");
    }

    public String getDescrizione() {
        return "imposta la difficoltà facile";
    }

    public void esegui() {
        try {
            GestioneComandi.setLivello("facile");
        } catch (PartitaGiaIniziataException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Medio extends Comando {
    Medio() {
        super("medio", "difficolta");
    }

    public String getDescrizione() {
        return "imposta la difficoltà media";
    }

    public void esegui() {
        try {
            GestioneComandi.setLivello("medio");
        } catch (PartitaGiaIniziataException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Difficile extends Comando {
    Difficile() {
        super("difficile", "difficolta");
    }

    public String getDescrizione() {
        return "imposta la difficoltà difficile";
    }

    public void esegui() {
        try {
            GestioneComandi.setLivello("difficile");
        } catch (PartitaGiaIniziataException e) {
            System.out.println(e.getMessage());
        }
    }
}

class MostraLivello extends Comando {
    MostraLivello() {
        super("mostraLivello", "utility");
    }

    String getDescrizione() {
        return "Mostra il livello di difficoltà impostato e il corrispondente numero massimo di tentativi falliti";
    }

    void esegui() {
        Grafica.mostraLivello(GestioneComandi.getLivello());
    }
}

class Gioca extends Comando {
    Gioca() {
        super("gioca", "gioco");
    }

    String getDescrizione() {
        return "Inizia una nuova partita";
    }

    void esegui() {
        try {
            GestioneComandi.inizializzaPartita();
            GestioneComandi.getPartita().posizionaNavi();
            System.out.println("Nuova partita iniziata\n");
            Grafica.stampaGrigliaColpita(GestioneComandi.getPartita().getGriglia());
        } catch (PartitaGiaIniziataException e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone non supportato");
        }
    }
}

class MostraNavi extends Comando {
    MostraNavi() {
        super("mostraNavi", "utility");
    }

    String getDescrizione() {
        return "Mostra le navi presenti nella griglia";
    }

    void esegui() {
        Grafica.stampaNavi();
    }
}


class SvelaGriglia extends Comando {
    SvelaGriglia() {
        super("svelaGriglia", "utility");
    }

    public String getDescrizione() {
        return "Svela la griglia di gioco";
    }

    public void esegui() {
        if (!GestioneComandi.partitaIniziata()) {
            System.out.println("Non c'è nessuna partita in corso");
            return;
        }

        try {
            Grafica.svelaGrigliaNavi(GestioneComandi.getPartita().getGriglia());
        } catch (CloneNotSupportedException e) {
            System.out.println("Impossibile svelare la griglia: clonazione di griglia fallita");
        }
    }
}

/**
 * Classe che rappresenta il comando /help.
 *
 * @author Gruppo Hamming
 */
class Help extends Comando {
    Help() {
        super("help", "utility");
    }

    public String getDescrizione() {
        return "Mostra l'elenco dei comandi utilizzabili";
    }

    public void esegui() {
        Grafica.stampaHelp();
    }
}
