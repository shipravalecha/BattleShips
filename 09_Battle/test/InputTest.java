import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito.*;

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
}