package it.uniba.app.interfaccia;

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


    /**
     * Test di stampaGrigliaColpita.
     * Il test intercetta lo standard output e verifica che sia stata
     * stampata una griglia vuota.
     * {@link GestioneStampe#stampaGrigliaColpita(Griglia)}
     */
    @Test
    @DisplayName("La griglia 10x10 vuota viene stampata correttamente")
    void testStampaGrigliaColpita() {
        String expectedOutput = """
                     A   B   C   D   E   F   G   H   I   J  \s
                   +---+---+---+---+---+---+---+---+---+---+
                1  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                2  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                3  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                4  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                5  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                6  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                7  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                8  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                9  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                10 |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+

                """;
        Griglia griglia = new Griglia();
        GestioneStampe.stampaGrigliaColpita(griglia);
        String actualOutput = outputStream.toString(StandardCharsets.UTF_8);
        actualOutput = actualOutput.replaceAll("\r", ""); // rende compatibile il test con Windows
        assertEquals(expectedOutput, actualOutput, "La griglia vuota non è stampata correttamente");
    }

    /**
     * Test di svelaGrigliaNavi.
     * Il test intercetta lo standard output e verifica che sia stata
     * stampata una griglia con delle navi usando regex per
     * riconoscere i simboli delle navi.
     * {@link GestioneStampe#svelaGrigliaNavi(Griglia)}
     */
    @Test
    @DisplayName("La griglia 10x10 con delle navi viene svelata correttamente")
    void testSvelaGrigliaNavi() {
        String expectedOutput = """
                Posizione delle navi:

                     A   B   C   D   E   F   G   H   I   J  \s
                   +---+---+---+---+---+---+---+---+---+---+
                1  | X | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                2  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                3  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                4  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                5  |   |   |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                6  |   | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                7  |   | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                8  |   | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                9  |   | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+
                10 |   | X |   |   |   |   |   |   |   |   |
                   +---+---+---+---+---+---+---+---+---+---+

                """;
        Griglia griglia = new Griglia();
        Nave nave1 = new Nave("cacciatorpediniere");
        final int r1 = 0;
        final int c1 = 0;
        Nave nave2 = new Nave("portaerei");
        final int r2 = 5;
        final int c2 = 1;
        griglia.posizionaNave(nave1, r1, c1, true);
        griglia.posizionaNave(nave2, r2, c2, false);
        GestioneStampe.svelaGrigliaNavi(griglia);
        String actualOutput = outputStream.toString(StandardCharsets.UTF_8);
        actualOutput = actualOutput.replaceAll("\r", ""); // rende compatibile il test con Windows
        actualOutput = actualOutput.replaceAll("\\u001B\\[\\d{1,2}m", ""); // rimuove i colori
        assertEquals(expectedOutput, actualOutput, "La griglia vuota non è stampata correttamente");
    }
}
