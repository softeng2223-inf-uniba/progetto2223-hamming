package it.uniba.app.gioco;

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
     * Metodo che imposta la cella come colpita.
     */
    protected void setColpita() {
        colpita = true;
    }


    /**
     * Metodo che indica se la cella è vuota (non contiene navi).
     * @return true
     */
    public boolean eVuota() {
        return true;
    }

    /**
     * Metodo che restituisce la nave occupante la cella (null).
     * @return null
     */
    public Nave getNave() {
        return null;
    }

    /**
     * Metodo che clona la cella.
     * @return la cella clonata.
     */
    public Cella clone() throws CloneNotSupportedException {
        return (Cella) super.clone();
    }

    /**
     * Metodo che viene chiamato quando la cella viene attaccata.
     *
     * @return false perché la cella non conteneva nessuna nave quando è stata colpita.
     */
    public boolean attacca() {
        colpita = true;
        System.out.println("Acqua . . .");
        return false;
    }
}
