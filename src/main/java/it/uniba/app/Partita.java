package it.uniba.app;

import java.util.ArrayList;

/**
 * Classe che rappresenta una partita di battaglia navale.
 * @author Gruppo Hamming
 */
public class Partita {
    private final String livello;
    private int tentativiRimasti;
    private ArrayList<Nave> navi = new ArrayList<Nave>();
    private Griglia griglia;

    /**
     * Costruttore della classe Partita che inizializza il livello della partita.
     * @param livelloParam liveello della partita
     */
    public Partita(final String livelloParam) {
        this.livello = livelloParam;
        this.griglia = new Griglia();
    }

    /**
     * Metodo che restituisce il livello della partita.
     * @return
     */
    public String getLivello() {
        return livello;
    }

    /**
     * Metodo che restituisce il numero di tentativi rimasti.
     * @return
     */
    public int getTentativiRimasti() {
        return tentativiRimasti;
    }

    /**
     * Metodo che restituisce la griglia della partita.
     */
    public Griglia getGriglia() {
        return griglia;
    }

    /**
     * Metodo che restituisce una nave dato l'indice.
     * @param indice indice della nave
     * @return
     */
    public Nave getNave(final int indice) {
        return navi.get(indice);
    }
}
