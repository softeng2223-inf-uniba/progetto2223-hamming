package it.uniba.app.gioco;

/**
 * Classe che rappresenta la griglia della battaglia navale.
 * @author Gruppo Hamming
*/
public class Griglia implements Cloneable {
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

    /**
        Metodo che posiziona una nave sulla griglia.
        @return true se la nave è stata posizionata, false altrimenti
        @param nave nave da posizionare
        @param r riga della prima cella che la nave deve occupare
        @param c colonna della prima cella che la nave deve occupare
        @param orizzontale true se la nave è orizzontale, false se è verticale
    */
    public boolean posizionaNave(final Nave nave, final int r, final int c, final boolean orizzontale) {
        if (orizzontale) {
            if (c + nave.getLunghezza() > Configurazioni.getColonneGriglia()) {
                return false;
            }
            for (int i = c; i < c + nave.getLunghezza(); i++) {
                if (!getCella(r, i).eVuota()) {
                    return false;
                }
            }
            for (int y = c; y < c + nave.getLunghezza(); y++) {
                celle[r][y] = new CellaPiena(nave);
            }
        } else {
            if (r + nave.getLunghezza() > Configurazioni.getRigheGriglia()) {
                return false;
            }
            for (int i = r; i < r + nave.getLunghezza(); i++) {
                if (!getCella(i, c).eVuota()) {
                    return false;
                }
            }
            for (int y = r; y < r + nave.getLunghezza(); y++) {
                celle[y][c] = new CellaPiena(nave);
            }
        }
        return true;
    }

    /**
     * Metodo che clona la griglia.
     * @return la griglia clonata.
     */
    public Griglia clone() throws CloneNotSupportedException {
        Griglia griglia = (Griglia) super.clone();
        griglia.celle = new Cella[Configurazioni.getRigheGriglia()][Configurazioni.getColonneGriglia()];
        for (int i = 0; i < Configurazioni.getRigheGriglia(); i++) {
            for (int j = 0; j < Configurazioni.getColonneGriglia(); j++) {
                griglia.celle[i][j] = celle[i][j].clone();
            }
        }
        return griglia;
    }
}
