package it.uniba.app.gioco;

/**
 * <<Entity>>
 * Classe che rappresenta una nave presente sulla griglia di gioco.
 * La nave ha una propria categoria e lunghezza.
 * Può essere colpita e affondata.
 *
 * @author Gruppo Hamming
 */
public class Nave implements Cloneable {
    private final String tipologia;
    private int celleRimanenti;

    /**
     * Costruttore che inizializza la nave con la tipologia e il numero di celle
     * rimanenti
     * per essere distrutta.
     * 
     * @param tipologiaParam tipologia della nave
     */
    public Nave(final String tipologiaParam) {
        this.tipologia = tipologiaParam;
        this.celleRimanenti = Configurazioni.getLunghezzaNavi(tipologia);
    }

    /**
     * Metodo che restituisce il numero di celle rimanenti per distruggere la nave.
     * 
     * @return il numero di celle rimanenti per distruggere la nave.
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Metodo che restituisce il numero di celle rimanenti per distruggere la nave.
     * 
     * @return il numero di celle rimanenti per distruggere la nave.
     */
    public int getCelleRimanenti() {
        return celleRimanenti;
    }

    /**
     * Restituisce la lunghezza della nave.
     * 
     * @return lunghezza della nave
     */
    public int getLunghezza() {
        return Configurazioni.getLunghezzaNavi(tipologia);
    }

    /**
     * Metodo che indica se la nave è affondata.
     * 
     * @return true se la nave è affondata, false altrimenti.
     */
    public boolean eAffondata() {
        return celleRimanenti == 0;
    }

    /**
     * Metodo che clone la nave.
     * 
     * @return la nave clonata.
     */
    public Nave clone() throws CloneNotSupportedException {
        return (Nave) super.clone();
    }

    /**
     * Metodo che decrementa il numero di celle rimanenti per affondare la nave.
     */
    public void colpisciNave() {
        celleRimanenti--;
    }
}
