import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;

public class SeaTest {
    Sea sea;
    @ParameterizedTest(name = "Size_Method_Should_Return_True")
    @CsvSource({
            "10, 10",
            "20, 20",
            "16, 16",
            "49, 49"
    })
    public void Size_Method_Should_Return_True(int val, int expectedResult) {
        sea = new Sea(val);
        int actualResult = sea.size();
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @ParameterizedTest(name = "Test_Check_If_ArrayLength_Is_Correct")
    @CsvSource({
            "10, 100",
            "20, 400"
    })
    public void Test_Check_If_ArrayLength_Is_Correct(int val, int expectedResult) {
        sea = new Sea(val);
        Assertions.assertEquals(sea.getArray().length, expectedResult);
    }

    @ParameterizedTest(name = "Test_Check_If_ArrayLength_Is_InCorrect")
    @CsvSource({
            "10, 101",
            "20, 401"
    })
    public void Test_Check_If_ArrayLength_Is_InCorrect(int val, int expectedResult) {
        sea = new Sea(val);
        Assertions.assertNotEquals(sea.getArray().length, expectedResult);
    }

    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_XNegativeValues")
    @CsvSource({
            "-1, 1, Program error: x cannot be -1",
            "-2, 2, Program error: x cannot be -2",
            "-3, 3, Program error: x cannot be -3"
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
            "1, -1, Program error: y cannot be -1",
            "2, -2, Program error: y cannot be -2",
            "3, -3, Program error: y cannot be -3"
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

    @ParameterizedTest(name = "Test_Should_Return_True_If_Value_In_Array_Matches")
    @CsvSource({"1,7,5", "1,3,5", "2,5,5"})
    public void Test_Should_Return_True_If_Value_In_Array_Matches(int x, int y, int value) {
        sea = new Sea(10);
        int index = sea.indexCopy(x,y);
        sea.set(x,y,value);
        int[] arr = sea.getArray();
        Assertions.assertTrue((arr[index] == value));
    }

    @ParameterizedTest(name = "GetMethod_Should_Return_Value_Test_True_If_Value_Matches")
    @CsvSource({"1,7,5", "1,3,5", "2,5,5"})
    public void GetMethod_Should_Return_Value_Test_True_If_Value_Matches(int x, int y, int value) {
        sea = new Sea(10);
        int index = sea.indexCopy(x,y);
        sea.set(x,y,value);
        int actualResult = sea.get(x,y);
        Assertions.assertTrue((actualResult == value));
    }

}