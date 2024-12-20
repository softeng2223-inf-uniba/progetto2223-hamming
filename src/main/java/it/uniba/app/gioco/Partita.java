package it.uniba.app.gioco;

import java.util.ArrayList;
import java.util.Random;
import it.uniba.app.exceptions.FuoriDallaGrigliaException;
import it.uniba.app.exceptions.CellaGiaColpitaException;

/**
 * <<Entity>>
 * Classe che rappresenta una partita di battaglia navale.
 * La partita è composta da una griglia e da un insieme di navi.
 * La partita ha un livello di difficoltà, un numero di tentativi
 * falliti massimo e un tempo (se impostato dal giocatore).
 *
 * @author Gruppo Hamming
 */
public class Partita {
    private final String livello;
    private int tentativiRimasti;
    private int tentativiEffettuati;
    private ArrayList<Nave> navi = new ArrayList<Nave>();
    private Griglia griglia;
    private Random random = new Random();

    /**
     * Costruttore della classe Partita che inizializza il livello della partita.
     *
     * @param livelloParam livello della partita
     */
    public Partita(final String livelloParam) {
        this.livello = livelloParam;
        this.griglia = new Griglia();
        tentativiRimasti = Configurazioni.getTentativi(livelloParam);

        for (String tipologia : Configurazioni.getTipologieNavi()) {
            for (int i = 0; i < Configurazioni.getNumeroNaviPerTipologia(tipologia); i++) {
                aggiungereNave(new Nave(tipologia));
            }
        }

        random.setSeed(System.currentTimeMillis());
    }

    /**
     * Metodo che restituisce il livello della partita.
     *
     * @return
     */
    public String getLivello() {
        return livello;
    }

    /**
     * Metodo che restituisce il numero di tentativi rimasti.
     *
     * @return
     */
    public int getTentativiRimasti() {
        return tentativiRimasti;
    }

    /**
     * Metodo che restituisce il numero di tentativi effettuati.
     *
     * @return
     */
    public int getTentativiEffettuati() {
        return tentativiEffettuati;
    }

    /**
     * Metodo che restituisce la griglia della partita.
     *
     * @throws CloneNotSupportedException
     */
    public Griglia getGriglia() throws CloneNotSupportedException {
        return griglia.clone();
    }

    /**
     * Metodo che restituisce una nave dato l'indice.
     *
     * @param indice indice della nave
     * @return
     */
    public Nave getNave(final int indice) {
        return navi.get(indice);
    }

    /**
     * Metodo che aggiunge una nave alla lista delle navi.
     *
     * @param nave nave da aggiungere alla lista
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

    /**
     * Metodo che attacca la cella selezionata e
     * decrementa il numero di tentativi rimasti
     * nel caso venga colpito il mare.
     *
     * @param riga    riga della griglia
     * @param colonna colonna della griglia
     */
    public EsitoColpo attaccaGriglia(final int riga, final int colonna)
            throws FuoriDallaGrigliaException, CellaGiaColpitaException {
        EsitoColpo esito = griglia.attaccaCella(riga, colonna);
        if (esito == EsitoColpo.ACQUA) {
            tentativiRimasti--;
        }
        tentativiEffettuati++;
        return esito;
    }

    /**
     * Metodo che controlla se tutte le navi sono affondate.
     *
     * @return true se tutte le navi sono affondate, false altrimenti
     */

    public boolean naviAffondate() {
        for (Nave nave : navi) {
            if (!nave.eAffondata()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo che controlla se i tentativi sono terminati.
     *
     * @return true se i tentativi sono terminati, false altrimenti
     */

    public boolean tentativiTerminati() {
        return tentativiRimasti == 0;
    }
}
