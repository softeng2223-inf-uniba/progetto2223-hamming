package it.uniba.app.interfaccia;

import it.uniba.app.gioco.Configurazioni;

/**
 * Classe che gestisce la grafica, l'interfaccia utente e la stampa a video.
 *
 * @author Gruppo Hamming
 */
public final class Grafica {
    private Grafica() {
    }

    /**
     * Stampa il livello di difficoltà attuale.
     *
     * @param livello livello di difficoltà attuale
     */
    public static void mostraLivello(final String livello) {
        System.out.println("Livello di difficoltà: " + livello
                + " (" + Configurazioni.getTentativi(livello) + " tentativi falliti massimi)");
    }
}
