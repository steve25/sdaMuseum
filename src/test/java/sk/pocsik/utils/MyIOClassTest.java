package sk.pocsik.utils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MyIOClassTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @ParameterizedTest
    @ValueSource(strings = {"hi", "Hello word", "Print my message"})
    public void printMessageStringsTest(String message) {
        MyIOClass.printMessage(message);
        String excepted = ConsoleColors.WHITE + message + ConsoleColors.RESET + "\r\n\r\n";
        assertEquals(excepted, outContent.toString());
    }

    @Test
    void printErrorMessage() {
    }

    @Test
    void intInputValidation() {
    }

    @Test
    void intInputValidationBetween() {
    }

    @Test
    void answerYesNo() {
    }

    @Test
    void getStringInput() {
    }

    @Test
    void printTicketsSoldMonth() {
    }

    @Test
    void printTicketsSoldDay() {
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}