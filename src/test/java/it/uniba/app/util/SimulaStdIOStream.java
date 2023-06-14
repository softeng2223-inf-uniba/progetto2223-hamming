package it.uniba.app.util;

import it.uniba.app.interfaccia.Grafica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


/**
 * Classe che permette di simulare l'input dell'utente e
 * di leggere l'output stampato dall'applicazione.
 * Usata solo per fini di test.
 */
public class SimulaStdIOStream {
    private static InputStream stdIn;
    private static PrintStream stdOut;
    private static ByteArrayOutputStream outputStream;

    /**
     * Salva lo standard input e lo standard output in due variabili
     * per poterli ripristinare alla fine dei test e definisce un
     * ByteArrayOutputStream per poter leggere l'output stampato.
     */
    @BeforeAll
    static void salvaStdIOStream() {
        stdIn = System.in;
        stdOut = System.out;
        outputStream = new ByteArrayOutputStream();
    }

    /**
     * Reindirizza lo standard output in un PrintStream
     * controllato dalla classe.
     */
    @BeforeEach
    void reindirizzaStdOut() {
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
    }

    /**
     * Ripristina lo standard input e lo standard output.
     */
    @AfterEach
    void ripristinaStdIOStream() {
        System.setIn(stdIn);
        System.setOut(stdOut);
    }

    /**
     * Simula l'immissione da parte dell'utente di una stringa.
     * Sostituisce lo standard input con un ByteArrayInputStream
     * controllato dal metodo e aggiorna lo scanner.
     *
     * @param inputParam stringa da simulare
     */
    protected static void simulaInput(final String inputParam) {
        System.setIn(new ByteArrayInputStream(inputParam.getBytes(StandardCharsets.UTF_8)));
        Grafica.aggiornaScanner();
    }

    /**
     * Restituisce l'output stampato dall'applicazione e resetta
     * outputStream dopo ogni stampa, in modo da cancellare le stampe precedenti.
     *
     * @return output stampato dall'applicazione
     */
    protected static String leggiOutput() {
        String output = outputStream.toString(StandardCharsets.UTF_8);
        outputStream.reset();
        return output;
    }


}
