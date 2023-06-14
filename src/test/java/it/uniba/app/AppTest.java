package it.uniba.app;

import it.uniba.app.exceptions.InputNonFormattatoException;
import it.uniba.app.util.SimulaStdIOStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Main test class of the application.
 */
class AppTest extends SimulaStdIOStream {
    /**
     * Test the getGreeting method of the App class.
     */
    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(
                classUnderTest.getGreeting(), "app should have a greeting");
    }

    /**
     * Test di lettura di un comando valido in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput accetta un comando valido (/gioca)")
    void testLeggiInput() {
        String input = "/gioca";
        simulaInput(input);
        String inputLetto;
        try {
            inputLetto = App.leggiInput();
        } catch (InputNonFormattatoException e) {
            fail("leggiInput ha riconosciuto un comando valido come mal formattato");
            return;
        }
        assertEquals(input.toLowerCase(), inputLetto, "Il comando letto non è uguale a quello inserito");
    }

    /**
     * Test di lettura di un comando non valido in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput accetta un comando non valido ma ben formattato (/giocaa)")
    void testLeggiInputNonValido() {
        String input = "/giocaa";
        simulaInput(input);
        String inputLetto;
        try {
            inputLetto = App.leggiInput();
        } catch (InputNonFormattatoException e) {
            fail("leggiInput ha riconosciuto un comando senza slash come mal formattato");
            return;
        }
        assertEquals(input.toLowerCase(), inputLetto, "Il comando letto non è uguale a quello inserito");
    }

    /**
     * Test di lettura di un comando senza slash in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput non accetta un comando senza slash (gioca)")
    void testLeggiInputSenzaSlash() {
        String input = "gioca";
        simulaInput(input);
        assertThrows(InputNonFormattatoException.class, App::leggiInput,
                "leggiInput ha riconosciuto un comando senza slash come ben formattato");
    }

    /**
     * Test di lettura di un attacco in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput accetta un attacco valido (A-3)")
    void testLeggiInputAttacco() {
        String input = "A-3";
        simulaInput(input);
        String inputLetto;
        try {
            inputLetto = App.leggiInput();
        } catch (InputNonFormattatoException e) {
            fail("leggiInput ha riconosciuto un attacco valido come ben formattato");
            return;
        }
        assertEquals(input.toLowerCase(), inputLetto, "L'attacco letto non è uguale a quello inserito");
    }

    /**
     * Test di lettura di un attacco con riga e colonna per taglie
     * di griglia grandi in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput accetta un attacco valido per taglia di griglia extralarge (Z-26)")
    void testLeggiInputAttaccoGrande() {
        String input = "Z-26";
        simulaInput(input);
        String inputLetto;
        try {
            inputLetto = App.leggiInput();
        } catch (InputNonFormattatoException e) {
            fail("leggiInput ha riconosciuto un attacco valido come mal formattato");
            return;
        }
        assertEquals(input.toLowerCase(), inputLetto, "L'attacco letto non è uguale a quello inserito");
    }

    /**
     * Test di lettura di un attacco non valido in leggiInput.
     * {@link App#leggiInput()}
     */
    @Test
    @DisplayName("leggiInput non accetta un attacco non valido (A-555)")
    void testLeggiInputAttaccoNonValido() {
        String input = "A-555";
        simulaInput(input);
        assertThrows(InputNonFormattatoException.class, App::leggiInput,
                "leggiInput ha accettato un attacco non valido");
    }
}
