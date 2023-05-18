package it.uniba.app.gioco;

/**
 * Classe che rappresenta una nave della griglia di gioco.
 * @author Gruppo Hamming
 */
public class Nave implements Cloneable {
    private final String tipologia;
    private int celleRimanenti;

    /**
     * Costruttore che inizializza la nave con la tipologia e il numero di celle rimanenti
     * per essere distrutta.
     * @param tipologiaParam
     * @param celleRimanentiParam
     */
    public Nave(final String tipologiaParam, final int celleRimanentiParam) {
        this.tipologia = tipologiaParam;
        this.celleRimanenti = celleRimanentiParam;
    }

    /**
     * Metodo che restituisce il numero di celle rimanenti per distruggere la nave.
     * @return il numero di celle rimanenti per distruggere la nave.
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Metodo che restituisce il numero di celle rimanenti per distruggere la nave.
     * @return il numero di celle rimanenti per distruggere la nave.
     */
    public int getCelleRimanenti() {
        return celleRimanenti;
    }

    /**
     * Metodo che clone la nave.
     * @return la nave clonata.
     */
    public Nave clone() throws CloneNotSupportedException {
        return (Nave) super.clone();
    }
}