import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SeaTest {
    Sea sea;

    @ParameterizedTest(name = "size_method_should_return_an_integer")
    @CsvSource({
            "10, 10",
            "20, 20",
            "16, 16",
            "49, 49"
    })
    public void size_method_should_return_an_integer(int val, int expectedResult) {
        sea = new Sea(val);
        int actualResult = sea.size();
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_XNegativeValues")
    @CsvSource({
            "-1, 1",
            "-2, 2",
            "-3, 3"
    })
//    @CsvSource({"-7,1", "8,-2", "-3,-3"})
    public void IndexMethod_ArrayIndexOutOfBoundsException_For_XNegativeValues(int x, int y) {
        sea = new Sea(10);
        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sea.indexCopy(x, y);
        });
        Assertions.assertEquals("Program error: x cannot be " + x, exception.getMessage());
    }

    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_YNegativeValues")
    @CsvSource({
            "1, -1",
            "2, -2",
            "3, -3"
    })
    public void IndexMethod_ArrayIndexOutOfBoundsException_For_YNegativeValues(int x, int y) {
        sea = new Sea(10);
        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sea.indexCopy(x, y);
        });
        Assertions.assertEquals("Program error: y cannot be " + y, exception.getMessage());
    }

    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_GreaterThanSizeValues")
    @CsvSource({"11,7", "1,90", "20,6"})
    public void IndexMethod_ArrayIndexOutOfBoundsException_For_GreaterThanSizeValues(int x, int y) {
        sea = new Sea(10);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sea.indexCopy(x, y);
        });
    }

    @ParameterizedTest(name = "IndexMethod_ReturnValue_For_ValidValues")
    @CsvSource({"1,7,71", "1,3,31", "2,5,52"})
    public void IndexMethod_ReturnValue_For_ValidValues(int x, int y, int expectedResult) {
        sea = new Sea(10);
        int actualResult = sea.indexCopy(x, y);
        Assertions.assertEquals(actualResult, expectedResult);
    }
}