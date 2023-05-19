package it.uniba.app.util;

import java.util.Scanner;

/**
 * Classe di utilità contenente dei metodi generici utili per l'applicazione.
 */
public final class Util {
    private static Scanner scan = new Scanner(System.in, "UTF-8");

    private Util() { }

    public static String getString() {
        return scan.nextLine();
    }


    /**
     * Metodo che calcola la distanza di Levenshtein tra due stringhe.
     */
    public static int getLevenshteinDistance(final String s, final String t) {
        int[][] d = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= t.length(); j++) {
            d[0][j] = j;
        }
        for (int j = 1; j <= t.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    int substitutionCost = d[i - 1][j - 1] + 1;
                    int insertionCost = d[i][j - 1] + 1;
                    int deletionCost = d[i - 1][j] + 1;
                    int minCost = Math.min(substitutionCost, Math.min(insertionCost, deletionCost));
                    d[i][j] = minCost;
                }
            }
        }
        return d[s.length()][t.length()];
    }
}
