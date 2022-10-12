import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SeaTest {
    Sea sea;
    @Test
    public void SampleTest() {
        System.out.println("SampleTest");
    }

    @ParameterizedTest(name = "size_method_should_return_an_integer")
    @CsvSource({
            "  1,  1",
            "  2,  2",
            " 16,  16",
            "  9,  9"
    })
    public void size_method_should_return_an_integer(int val, int expectedResult) {
        sea = new Sea(val);
        int actualResult = sea.size();
        Assertions.assertEquals(actualResult, expectedResult);
    }
}
