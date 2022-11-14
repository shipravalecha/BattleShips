import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.text.NumberFormat;

public class InputTest {
    Input input;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void readCoordinates_Null_Or_Empty_String_Should_Return_False() throws IOException {
        String data = "";
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(data.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();

        Assertions.assertEquals(false, x);
    }

    @ParameterizedTest(name = "readCoordinates_Valid_String_Should_Return_True")
    @CsvSource({
            "1,1",
            "2,2",
            "3,3",
            "4,4"
    })
    public void readCoordinates_Valid_String_Should_Return_True(String data, String data1) throws IOException {
        String foo = data + "," + data1;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();

        Assertions.assertTrue(x);
    }

    @ParameterizedTest(name = "readCoordinates_String_Length_Not_2_Returns_False")
    @CsvSource({
            "1,1,1",
            "2,2,2",
            "3,3,3",
            "4,4,4"
    })
    public void readCoordinates_String_Length_Not_2_Returns_False(String data, String data1, String data2) throws IOException {
        String foo = data + "," + data1 + "," + data2;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();

        Assertions.assertFalse(x);
    }

    @ParameterizedTest(name = "readCoordinates_Values_Out_Of_Range_Returns_False")
    @CsvSource({
            "-1,-1",
            "5,5",
            "0,0",
    })
    public void readCoordinates_Values_Out_Of_Range_Returns_False(String data, String data1) throws IOException {
        String foo = data + "," + data1;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();

        Assertions.assertFalse(x);
    }

    @ParameterizedTest(name = "readCoordinates_Valid_String_Should_Return_True")
    @CsvSource({
            "a,b",
            "c,d",
            "!,@",
    })
    public void readCoordinates_Non_Int_Values_Throw_Exception(String data, String data1) throws IOException, NumberFormatException {
        String foo = data + "," + data1;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean x = input.readCoordinates();
        thrown.expect(NumberFormatException.class);
    }

    @ParameterizedTest(name = "x_should_return_x")
    @CsvSource({
            "1,1",
            "2,2",
            "3,3",
            "4,4"
    })
    public void x_should_return_x(String data, String data1) throws IOException {
        String foo = data + "," + data1;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean garb = input.readCoordinates();
        String output = Integer.toString(input.x());
        Assertions.assertEquals(data, output);
    }

    @ParameterizedTest(name = "y_should_return_y")
    @CsvSource({
            "1,1",
            "2,2",
            "3,3",
            "4,4"
    })
    public void y_should_return_y(String data, String data1) throws IOException {
        String foo = data + "," + data1;
        ByteArrayInputStream fakeIS = new ByteArrayInputStream(foo.getBytes());
        input = new Input(4, fakeIS);

        boolean garb = input.readCoordinates();
        String output = Integer.toString(input.y());
        Assertions.assertEquals(data, output);
    }
}