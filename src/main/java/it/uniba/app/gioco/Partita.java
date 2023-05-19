package it.uniba.app.gioco;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe che rappresenta una partita di battaglia navale.
 * @author Gruppo Hamming
 */
public class Partita {
    private final String livello;
    private int tentativiRimasti;
    private ArrayList<Nave> navi = new ArrayList<Nave>();
    private Griglia griglia;
    private Random random = new Random();

    /**
     * Costruttore della classe Partita che inizializza il livello della partita.
     * @param livelloParam liveello della partita
     */
    public Partita(final String livelloParam) {
        this.livello = livelloParam;
        this.griglia = new Griglia();

        for (String tipologia : Configurazioni.getTipologieNavi()) {
            for (int i = 0; i < Configurazioni.getNumeroNaviPerTipologia(tipologia); i++) {
                aggiungereNave(new Nave(tipologia));
            }
        }

        random.setSeed(System.currentTimeMillis());
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
     * @throws CloneNotSupportedException
     */
    public Griglia getGriglia() throws CloneNotSupportedException {
        return griglia.clone();
    }

    /**
     * Metodo che restituisce una nave dato l'indice.
     * @param indice indice della nave
     * @return
     */
    public Nave getNave(final int indice) {
        return navi.get(indice);
    }

    /**
        Metodo che aggiunge una nave alla lista delle navi.
        @param nave nave da aggiungere alla lista
     */
    public final void aggiungereNave(final Nave nave) {
        this.navi.add(nave);
    }

    /**
     * Metodo che posiziona le navi sulla griglia.
     */
    public final void posizionaNavi() {
        int i = 0;
        while (i < navi.size()) {
            boolean posizionata = false;
            int tentativiPosizionamento = 0;
            do {
                int riga = random.nextInt(Configurazioni.getRigheGriglia());
                int colonna = random.nextInt(Configurazioni.getColonneGriglia());
                boolean orizzontale = random.nextBoolean();
                posizionata = griglia.posizionaNave(navi.get(i), riga, colonna, orizzontale);
                tentativiPosizionamento++;
            } while (!posizionata && tentativiPosizionamento < Configurazioni.getTentativiMassimiPosizionamento());

            if (!posizionata) {
                griglia = new Griglia();
                i = 0;
            } else {
                i++;
            }
        }
    }
}
