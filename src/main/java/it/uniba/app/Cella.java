package it.uniba.app;

/**
 * Classe che rappresenta una cella della griglia di gioco.
 * Una cella può essere colpita o meno.
 * @author Gruppo Hamming
 */
public class Cella implements Cloneable {
    /**
     * Attributo che indica se la cella è stata colpita o meno.
     */
    private boolean colpita;

    /**
     * Costruttore della classe che initializza la cella come non colpita.
     */
    public Cella() {
        colpita = false;
    }

    /**
     * Metodo che restituisce true se la cella è stata colpita, false altrimenti.
     * @return true se la cella è stata colpita, false altrimenti.
     */
    public boolean eColpita() {
        return colpita;
    }

    /**
     * Metodo che clona la cella.
     * @return la cella clonata.
     */
    public Cella clone() throws CloneNotSupportedException {
        return (Cella) super.clone();
    }
}
