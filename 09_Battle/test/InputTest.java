import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class InputTest {
    Input input;

    // Test to validate Input is created
    @ParameterizedTest(name = "Input_Takes_Any_Integer_On_Creation")
    @CsvSource({
            "-1",
            "0",
            "16",
            "49"
    })
    public void Input_Takes_Any_Integer_On_Creation(int val) {
        input = new Input(val);
        Assertions.assertNotNull(input);
    }

    @Test
    public void MockTest() throws IOException {
        String data = "";
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(data.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();

        Assertions.assertEquals(false, x);

    }
}