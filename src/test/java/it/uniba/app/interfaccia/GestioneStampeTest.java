package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.LivelloNonEsistenteException;
import it.uniba.app.gioco.Griglia;
import it.uniba.app.gioco.Nave;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Classe di test per la classe GestioneStampe.
 * {@link GestioneStampe}
 */
class GestioneStampeTest {
    private static ByteArrayOutputStream outputStream;
    private static PrintStream stdOut;

    /**
     * Metodo eseguito una volta all'inizio del test.
     * Serve per poter intercettare lo standard output
     * reindirizzandolo su outputStream.
     */
    @BeforeAll
    static void setUp() {
        stdOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
    }

    /**
     * Resetta outputStream prima di ogni test,
     * in modo da cancellare l'output dei test precedenti.
     */
    @BeforeEach
    void setUpEach() {
        outputStream.reset();
    }

    /**
     * Ripristina lo standard output.
     */
    @AfterAll
    static void tearDown() {
        System.setOut(stdOut);
    }


}
