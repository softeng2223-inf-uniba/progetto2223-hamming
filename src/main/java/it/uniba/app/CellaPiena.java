package it.uniba.app;

/**
 * Classe che rappresenta una cella della griglia di gioco
 * in cui è presente una nave.
 * @author Gruppo Hamming
 */
public class CellaPiena extends Cella {
    private final Nave nave;

    /**
     * Costruttore della classe che inizializza la cella come non colpita e
     * imposta la nave che occupa la cella.
     * @param naveParam la nave che occupa la cella.
     */
    public CellaPiena(final Nave naveParam) {
        super();
        this.nave = naveParam;
    }

    /**
     * Metodo che restituisce la nave che occupa la cella.
     * @return la nave che occupa la cella.
     */
    public Nave getNave() {
        return nave;
    }
}
