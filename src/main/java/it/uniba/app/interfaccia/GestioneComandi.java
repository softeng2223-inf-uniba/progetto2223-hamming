package it.uniba.app.interfaccia;

import java.util.Arrays;

import it.uniba.app.exceptions.ComandiException;
import it.uniba.app.exceptions.ComandoNonEsistenteException;
import it.uniba.app.exceptions.InputNonFormattatoException;
import it.uniba.app.exceptions.ParametriNonCorrettiException;
import it.uniba.app.exceptions.PartitaGiaIniziataException;
import it.uniba.app.exceptions.PartitaNonIniziataException;
import it.uniba.app.gioco.Configurazioni;
import it.uniba.app.gioco.Partita;

/**
 * Classe che gestisce i comandi inseriti dall'utente e li esegue.
 *
 * @author Gruppo Hamming
 */
public final class GestioneComandi {
    static final float MILLISECONDI = 1000F;
    static final int SECONDI = 60;

    private static Partita partita = null;
    private static Boolean continua = true;
    private static String livello = Configurazioni.getLivelloDefault();
    private static int tempo = 0;
    private static long tempoInizio = 0;

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
     * @throws PartitaGiaIniziataException non si può inizializzare una partita se è
     *                                     già in corso
     */
    public static void inizializzaPartita() throws PartitaGiaIniziataException {
        if (partita != null) {
            throw new PartitaGiaIniziataException("Una partita è già in corso");
        }
        partita = new Partita(livello);
    }

    /**
     * Metodo che termina la partita.
     */
    public static void cancellaPartita() {
        partita = null;
    }

    /**
     * Metodo che svela la griglia con le navi posizionate e termina la partita.
     *
     * @param esito esito della partita
     */
    public static void terminaPartita(final String esito) {
        try {
            Grafica.stampaFinePartita(esito, GestioneComandi.getPartita().getGriglia());
        } catch (CloneNotSupportedException e) {
            Grafica.stampaErrore("Impossibile svelare la griglia: clonazione di griglia fallita");
        }
        GestioneComandi.cancellaPartita();
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
        Grafica.stampaMessaggio("La difficoltà è stata cambiata.");
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
     * Restituisce true se input è un comando altrimenti false.
     *
     * @param input input da controllare
     * @return true se input è un comando altrimenti false
     */
    public static boolean eComando(final String input) {
        return input.startsWith("/");
    }

    /**
     * Metodo che restituisce il tempo impostato per la partita.
     *
     * @return tempo impostato per la partita
     */
    static int getTempo() {
        return tempo;
    }

    /**
     * Metodo che imposta il tempo per la partita.
     *
     * @param minuti tempo in minuti da impostare per la partita
     */
    static void setTempo(final int minuti) {
        tempo = minuti;
    }

    /**
     * Metodo che restituisce true se il tempo è stato impostato, false altrimenti.
     *
     * @return true se il tempo è stato impostato, false altrimenti
     */
    public static boolean tempoImpostato() {
        return tempo != 0;
    }

    /**
     * Metodo che inizia il conteggio del tempo della partita.
     */
    static void avviaTempo() {
        tempoInizio = System.currentTimeMillis();
    }

    /**
     * Metodo che restituisce il tempo trascorso dall'inizio della partita.
     *
     * @return tempo trascorso dall'inizio della partita
     */
    static float tempoTrascorso() {
        return (System.currentTimeMillis() - tempoInizio) / MILLISECONDI;
    }

    /**
     * Metodo che restituisce il tempo rimasto per la partita.
     *
     * @return tempo rimasto per la partita
     */
    static float tempoRimasto() {
        return tempo * SECONDI - tempoTrascorso();
    }

    /**
     * Metodo che restituisce true se il tempo è scaduto, false altrimenti.
     *
     * @return true se il tempo è scaduto, false altrimenti
     */
    public static boolean tempoScaduto() {
        return tempoTrascorso() >= tempo * SECONDI;
    }

    /**
     * Metodo che restituisce una stringa contenente i minuti e i secondi.
     *
     * @param secondi tempo in secondi
     * @return stringa contenente i minuti e i secondi
     */
    static String getMinuti(final float secondi) {
        int min = (int) (secondi / SECONDI);
        int sec = Math.round(secondi % SECONDI);
        return min + ":" + (String.valueOf(sec).length() == 2 ? sec : "0" + sec);
    }

    /**
     * Esegue il comando specificato, passandogli i parametri.
     *
     * @param comando   nome del comando da eseguire
     * @param parametri parametri da passare al comando
     */
    public static void chiamaComando(final String input)
            throws ComandiException, ComandoNonEsistenteException {
        String[] split = input.split(" ");
        String[] parametri = new String[split.length - 1];
        if (split.length > 1) {
            parametri = Arrays.copyOfRange(split, 1, split.length);
        }
        Comando c = ConfigurazioniInterfaccia.getComando(split[0].substring(1).toLowerCase());

        if (c != null) {
            c.esegui(parametri);
        } else {
            throw new ComandoNonEsistenteException(split[0]);
        }
    }

    /**
     * Metodo che attacca la cella specificata dall'input dell'utente.
     *
     * @param attacco input dell'utente
     */

    public static void attacco(final String attacco) {
        String[] cella = attacco.split("-"); // b-4 -> [b, 4]
        int colonna = cella[0].charAt(0) - 'a'; // b -> 1 // e -> 4
        int riga = Integer.parseInt(cella[1]) - 1; // "4" -> (4 - 1) = 3
        partita.attaccaGriglia(riga, colonna);
    }

    /**
     * Esegui parametrizzato dei comandi di difficoltà.
     *
     * @param difficolta nome del comando da eseguire, che rappresenta la
     *                   difficoltà.
     * @param parametri  parametri da passare al comando.
     * @throws ParametriNonCorrettiException i parametri sono più di uno
     * @throws PartitaGiaIniziataException   non si può inizializzare una partita se
     *                                       è già in corso
     */
    public static void eseguiDifficolta(final String difficolta, final String[] parametri)
            throws ParametriNonCorrettiException, PartitaGiaIniziataException {
        if (parametri.length > 1) {
            throw new ParametriNonCorrettiException(
                    "Troppi parametri per il comando. Utilizzo corretto: /" + difficolta + " [tentativi]");
        }
        try {
            if (parametri.length == 1) {
                try {
                    if (GestioneComandi.partitaIniziata()) {
                        throw new PartitaGiaIniziataException(
                                "Non puoi cambiare il numero di tentativi"
                                        + " massimi di una difficoltà durante una partita");
                    }
                    int tentativi = Integer.parseInt(parametri[0]);
                    // controlla che il numero sia maggiore di zero
                    if (tentativi <= 0) {
                        throw new ParametriNonCorrettiException(
                                "Il parametro [tentativi] deve essere maggiore di 0."
                                        + " Utilizzo corretto: /" + difficolta + " [tentativi]");
                    }
                    Configurazioni.setTentativi(difficolta, tentativi);
                    Grafica.stampaMessaggio("Numero di tentativi massimi della difficoltà "
                            + difficolta + " modificato a " + Configurazioni.getTentativi(difficolta));
                } catch (NumberFormatException e) {
                    throw new ParametriNonCorrettiException(
                            "Il parametro [tentativi] non è un numero intero. Utilizzo corretto: /" + difficolta
                                    + " [tentativi]");
                }
                return;
            }
            GestioneComandi.setLivello(difficolta);
            Configurazioni.deleteCustomTentativi();
        } catch (PartitaGiaIniziataException e) {
            Grafica.stampaWarning(e.getMessage());
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

    void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (GestioneComandi.partitaIniziata()) {
            Grafica.stampaMessaggio("Attenzione: se esci abbandonerai la partita in corso");
        }

        boolean conferma = Grafica.chiediConferma("Conferma l'uscita dal programma(s/n): ");

        if (conferma) {
            Grafica.stampaMessaggio("Uscita dal programma");
            GestioneComandi.setContinua(false);
        } else {
            Grafica.stampaMessaggio("Uscita annullata");
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

    public void esegui(final String[] parametri) {
        try {
            GestioneComandi.eseguiDifficolta(this.getNome(), parametri);
        } catch (PartitaGiaIniziataException | ParametriNonCorrettiException e) {
            Grafica.stampaWarning(e.getMessage());
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

    public void esegui(final String[] parametri) {
        try {
            GestioneComandi.eseguiDifficolta(this.getNome(), parametri);
        } catch (PartitaGiaIniziataException | ParametriNonCorrettiException e) {
            Grafica.stampaWarning(e.getMessage());
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

    public void esegui(final String[] parametri) {
        try {
            GestioneComandi.eseguiDifficolta(this.getNome(), parametri);
        } catch (PartitaGiaIniziataException | ParametriNonCorrettiException e) {
            Grafica.stampaWarning(e.getMessage());
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

    void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

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

    void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        try {
            GestioneComandi.inizializzaPartita();
            GestioneComandi.getPartita().posizionaNavi();
            Grafica.stampaMessaggio("Nuova partita iniziata\n");
            if (GestioneComandi.tempoImpostato()) {
                GestioneComandi.avviaTempo();
            }
            Grafica.stampaGrigliaColpita(GestioneComandi.getPartita().getGriglia());
        } catch (PartitaGiaIniziataException e) {
            Grafica.stampaWarning(e.getMessage());
        } catch (CloneNotSupportedException e) {
            Grafica.stampaErrore("Impossibile stampare la griglia: clonazione di griglia fallita");
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

    void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

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

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaNonIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (!GestioneComandi.partitaIniziata()) {
            throw new PartitaNonIniziataException();
        }

        try {
            Grafica.svelaGrigliaNavi(GestioneComandi.getPartita().getGriglia());
        } catch (CloneNotSupportedException e) {
            Grafica.stampaErrore("Impossibile svelare la griglia: clonazione di griglia fallita");
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

    public void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        Grafica.stampaHelp();
    }
}

/**
 * Classe rappresentante il comando /standard, che
 * imposta a 10x10 la dimensione della griglia (è il default).
 */
class Standard extends Comando {
    Standard() {
        super("standard", "difficolta");
    }

    public String getDescrizione() {
        return "Imposta la dimensione della griglia a 10x10 (default)";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaNonIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (GestioneComandi.partitaIniziata()) {
            throw new PartitaNonIniziataException();
        }

        Configurazioni.setRigheGriglia(Configurazioni.DIMENSIONI_GRIGLIA_STANDARD);
        Configurazioni.setColonneGriglia(Configurazioni.DIMENSIONI_GRIGLIA_STANDARD);
        Grafica.stampaMessaggio("Dimensione della griglia impostata a 10x10");
    }
}

/**
 * Classe rappresentante il comando /large, che
 * imposta a 18x18 la dimensione della griglia.
 */
class Large extends Comando {

    Large() {
        super("large", "difficolta");
    }

    public String getDescrizione() {
        return "Imposta la dimensione della griglia a 18x18";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaGiaIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (GestioneComandi.partitaIniziata()) {
            throw new PartitaGiaIniziataException("Non puoi cambiare la dimensione della griglia durante una partita");
        }

        Configurazioni.setRigheGriglia(Configurazioni.DIMENSIONI_GRIGLIA_LARGE);
        Configurazioni.setColonneGriglia(Configurazioni.DIMENSIONI_GRIGLIA_LARGE);
        Grafica.stampaMessaggio("Dimensione della griglia impostata a 18x18");
    }
}

/**
 * Classe rappresentante il comando /extralarge, che
 * imposta a 26x26 la dimensione della griglia.
 */
class ExtraLarge extends Comando {

    ExtraLarge() {
        super("extralarge", "difficolta");
    }

    public String getDescrizione() {
        return "Imposta la dimensione della griglia a 26x26";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaGiaIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (GestioneComandi.partitaIniziata()) {
            throw new PartitaGiaIniziataException("Non puoi cambiare la dimensione della griglia durante una partita");
        }

        Configurazioni.setRigheGriglia(Configurazioni.DIMENSIONI_GRIGLIA_EXTRA_LARGE);
        Configurazioni.setColonneGriglia(Configurazioni.DIMENSIONI_GRIGLIA_EXTRA_LARGE);
        Grafica.stampaMessaggio("Dimensione della griglia impostata a 26x26");
    }
}

/**
 * Classe rappresentante il comando /abbandona, che
 * chiede conferma all'utente:
 * se la conferma è positiva, l’applicazione risponde visualizzando
 * sulla griglia la posizione di tutte le navi e si predispone a ricevere nuovi
 * comandi;
 * se la conferma è negativa, l'applicazione si predispone a ricevere nuovi
 * tentativi o comandi.
 */
class Abbandona extends Comando {
    Abbandona() {
        super("abbandona", "gioco");
    }

    public String getDescrizione() {
        return "Abbandona la partita in corso";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaNonIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (!GestioneComandi.partitaIniziata()) {
            throw new PartitaNonIniziataException();
        }

        boolean conferma = Grafica.chiediConferma("Conferma l'abbandono della partita(s/n): ");

        if (conferma) {
            GestioneComandi.terminaPartita("abbandonata");
        } else {
            Grafica.stampaMessaggio("Abbandono della partita annullato");
        }
    }
}

/**
 * Classe rappresentante il comando /tempo, che
 * imposta il tempo massimo di gioco in minuti.
 */
class Tempo extends Comando {
    Tempo() {
        super("tempo", "difficolta");
    }

    public String getDescrizione() {
        return "Imposta il tempo massimo di gioco in minuti. Se impostato a 0, non ci sono limiti di tempo";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaGiaIniziataException {
        if (parametri.length != 1) {
            throw new InputNonFormattatoException();
        }

        if (GestioneComandi.partitaIniziata()) {
            throw new PartitaGiaIniziataException("Non puoi cambiare il tempo di gioco durante una partita");
        }

        int tempo = Integer.parseInt(parametri[0]);
        if (tempo < 0) {
            Grafica.stampaWarning(
                "Il tempo di gioco deve essere maggiore o uguale a 0 (0 in caso di nessun limite)");
            return;
        }

        GestioneComandi.setTempo(tempo);
        Grafica.stampaMessaggio(
            "Tempo di gioco impostato a: " + (tempo == 0 ? "nessun limite" : tempo + " minuti"));
    }
}

/**
 * Al comando /mostragriglia
 * l’applicazione risponde visualizzando, una griglia 10x10,
 * con le righe numerate da 1 a 10 e le colonne numerate da A a J,
 * con le navi affondate e le sole parti già colpite delle navi non affondate.
 */
class MostraGriglia extends Comando {
    MostraGriglia() {
        super("mostragriglia", "gioco");
    }

    public String getDescrizione() {
        return "Mostra la griglia di gioco";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException, PartitaNonIniziataException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (!GestioneComandi.partitaIniziata()) {
            throw new PartitaNonIniziataException();
        }

        try {
            Grafica.stampaGrigliaColpita(GestioneComandi.getPartita().getGriglia());
        } catch (CloneNotSupportedException e) {
            Grafica.stampaErrore("Impossibile svelare la griglia: clonazione di griglia fallita");
        }
    }
}

/**
 * Classe rappresentante il comando /mostratempo, che
 * mostra il tempo trascorso e il tempo rimanente di gioco.
 */
class MostraTempo extends Comando {
    MostraTempo() {
        super("mostratempo", "difficolta");
    }

    public String getDescrizione() {
        return "Mostra il tempo rimanente di gioco";
    }

    public void esegui(final String[] parametri) throws InputNonFormattatoException {
        if (parametri.length > 0) {
            throw new InputNonFormattatoException();
        }

        if (!GestioneComandi.partitaIniziata()) {
            Grafica.stampaMessaggio(GestioneComandi.tempoImpostato()
                    ? "La partita non è ancora iniziata.\nTempo di gioco impostato a " + GestioneComandi.getTempo()
                            + " minuti"
                    : "Non è stato impostato nessun limite di tempo");
            return;
        }

        if (!GestioneComandi.tempoImpostato()) {
            Grafica.stampaWarning("Non è stato impostato nessun limite di tempo");
            return;
        }

        if (!GestioneComandi.tempoScaduto()) {
            Grafica.stampaMessaggio("Tempo trascorso: "
                    + GestioneComandi.getMinuti(GestioneComandi.tempoTrascorso()) + " minuti");
            Grafica.stampaMessaggio("Tempo rimanente: "
                    + GestioneComandi.getMinuti(GestioneComandi.tempoRimasto()) + " minuti");
        } else {
            GestioneComandi.terminaPartita("persa: tempo scaduto");
        }
    }
}

/**
 * Classe rappresentante il comando /tentativi, che
 * imposta il numero massimo di tentativi falliti senza selezionare
 * una difficoltà predefinita.
 */
class Tentativi extends Comando {
    Tentativi() {
        super("tentativi", "difficolta");
    }

    public String getDescrizione() {
        return "Imposta il numero massimo di tentativi falliti senza selezionare una difficoltà predefinita";
    }

    public void esegui(final String[] parametri) throws ParametriNonCorrettiException, PartitaGiaIniziataException {
        if (parametri.length != 1) {
            throw new ParametriNonCorrettiException("Numero di parametri errato."
                    + " Utilizzo corretto: /tentativi <num_tentativi>");
        }
        if (GestioneComandi.partitaIniziata()) {
            throw new PartitaGiaIniziataException("Non puoi cambiare il numero "
                    + "di tentativi massimi durante una partita");
        }
        int tentativi = Integer.parseInt(parametri[0]);
        if (tentativi <= 0) {
            Grafica.stampaWarning("Il numero di tentativi massimi deve essere maggiore di 0");
            return;
        }
        try {
            Configurazioni.setCustomTentativi(tentativi);
            GestioneComandi.setLivello("custom");
        } catch (PartitaGiaIniziataException e) {
            Grafica.stampaWarning(e.getMessage());
        }
        Grafica.stampaMessaggio("Numero di tentativi massimi impostato a " + tentativi);
    }
}
