package it.uniba.app.gioco;

/**
 * <<Entity>>
 * Classe che rappresenta una cella della griglia di gioco
 * in cui è presente una nave.
 *
 * @author Gruppo Hamming
 */
public class CellaPiena extends Cella {
    private Nave nave;

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

    /**
     * Metodo che indica se la cella è vuota (non contiene navi).
     * @return false
     */
    public boolean eVuota() {
        return false;
    }

    /**
     * Metodo che clona la cella piena.
     * @return la cella piena clonata.
     */
    public CellaPiena clone() throws CloneNotSupportedException {
        CellaPiena cellaPienaClone = (CellaPiena) super.clone();
        cellaPienaClone.nave = this.nave.clone();
        return cellaPienaClone;
    }

    /**
     * Metodo che viene chiamato quando la cella con una nave viene attaccata.
     *
     * @return true se la cella conteneva una nave quando è stata colpita.
     */
    public EsitoColpo attacca() {
        setColpita();
        nave.colpisciNave();
        if (nave.eAffondata()) {
            return EsitoColpo.AFFONDATO;
        }
        return EsitoColpo.COLPITO;
    }
}
