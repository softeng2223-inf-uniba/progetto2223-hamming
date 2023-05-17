package it.uniba.app;

/**
 * Classe che rappresenta la griglia della battaglia navale.
 * @author Gruppo Hamming
*/
public class Griglia {
    private Cella[][] celle;

    /**
     * Costruttore della classe Griglia.
     * @return
     */
    public Griglia() {
        celle = new Cella[Configurazioni.getRigheGriglia()][Configurazioni.getColonneGriglia()];
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                celle[i][j] = new Cella();
            }
        }
    }

    /**
     * Metodo che restituisce la cella della griglia dati riga e colonna di essa.
     * @param riga riga della cella
     * @param colonna colonna della cella
     * @return
     */
    public Cella getCella(final int riga, final int colonna) {
        return celle[riga][colonna];
    }

    /**
     * Metodo che setta la cella della griglia dati riga e colonna di essa.
     * @param riga riga della cella
     * @param colonna colonna della cella
     * @param cella cella da settare
     */
    public void setCella(final int riga, final int colonna, final Cella cella) {
        celle[riga][colonna] = cella;
    }
}
