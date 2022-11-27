import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SeaTest {
    Sea sea;

    // Test to validate the value returned by size method
    @ParameterizedTest(name = "Size_Method_Should_Return_Value")
    @CsvSource({
            "10, 10",
            "20, 20",
            "16, 16",
            "49, 49"
    })
    public void Size_Method_Should_Return_Value(int val, int expectedResult) {
        sea = new Sea(val);
        int actualResult = sea.size();
        Assertions.assertEquals(actualResult, expectedResult);
    }

    // Positive test to validate the length of an array
    @ParameterizedTest(name = "Test_Check_If_ArrayLength_Is_Correct")
    @CsvSource({
            "10, 100",
            "20, 400"
    })
    public void Test_Check_If_ArrayLength_Is_Correct(int val, int expectedResult) {
        sea = new Sea(val);
        Assertions.assertEquals(sea.getArray().length, expectedResult);
    }

    // Negative test to validate the length of an array
    @ParameterizedTest(name = "Test_Check_If_ArrayLength_Is_InCorrect")
    @CsvSource({
            "10, 101",
            "20, 401"
    })
    public void Test_Check_If_ArrayLength_Is_InCorrect(int val, int expectedResult) {
        sea = new Sea(val);
        Assertions.assertNotEquals(sea.getArray().length, expectedResult);
    }

    // Test for index method to check for Exceptions for x coordinate
    // if these are negative or greater than size
    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_XValues")
    @CsvSource({
            "-1, 1, Program error: x cannot be -1",
            "-2, 2, Program error: x cannot be -2",
            "-3, 3, Program error: x cannot be -3",
            "11, 2, Program error: x cannot be 11"
    })
    public void IndexMethod_ArrayIndexOutOfBoundsException_For_XValues(int x, int y) {
        sea = new Sea(10);
        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sea.indexCopy(x, y);
        });
        Assertions.assertEquals("Program error: x cannot be " + x, exception.getMessage());
    }

    // Test for index method to check for Exceptions for y coordinate
    // if these are negative or greater than size
    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_YValues")
    @CsvSource({
            "1, -1, Program error: y cannot be -1",
            "2, -2, Program error: y cannot be -2",
            "3, -3, Program error: y cannot be -3",
            "3, 11, Program error: y cannot be 11"
    })
    public void IndexMethod_ArrayIndexOutOfBoundsException_For_YValues(int x, int y) {
        sea = new Sea(10);
        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sea.indexCopy(x, y);
        });
        Assertions.assertEquals("Program error: y cannot be " + y, exception.getMessage());
    }

//    Test for index method to check for Exceptions for greater than size values
//    @ParameterizedTest(name = "IndexMethod_ArrayIndexOutOfBoundsException_For_GreaterThanSizeValues")
//    @CsvSource({"11,7", "1,90", "20,6"})
//    public void IndexMethod_ArrayIndexOutOfBoundsException_For_GreaterThanSizeValues(int x, int y) {
//        sea = new Sea(10);
//        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            sea.indexCopy(x, y);
//        });
//    }

    // Test for index method to check for valid values
    @ParameterizedTest(name = "IndexMethod_ReturnValue_For_ValidValues")
    @CsvSource({"1,7,71", "1,3,31", "2,5,52"})
    public void IndexMethod_ReturnValue_For_ValidValues(int x, int y, int expectedResult) {
        sea = new Sea(10);
        int actualResult = sea.indexCopy(x, y);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    // Test for get and set methods to check if values are correct in array
    @ParameterizedTest(name = "Test_Should_Return_True_If_Value_In_Array_Matches")
    @CsvSource({"1,7,5", "1,3,5", "2,5,5"})
    public void Test_Should_Return_True_If_Value_In_Array_Matches(int x, int y, int expectedResult) {
        sea = new Sea(10);
        sea.set(x,y,expectedResult);
        int actualResult = sea.get(x, y);
        Assertions.assertTrue(actualResult == expectedResult);
    }

    // Test_To_Check_If_X_And_Y_isEmpty
    @ParameterizedTest(name = "Test_To_Check_If_X_And_Y_isEmpty")
    @CsvSource({
            "-1, 1, False",
            "-2, 2, False",
            "1, -1, False",
            "2, -2, False",
            "11, 2, False",
            "2, 11, False"
    })
    public void Test_To_Check_If_X_And_Y_isEmpty(int x, int y, boolean expectedResult) {
        sea = new Sea(10);
        boolean actualResult = sea.isEmpty(x, y);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    // Test_To_Check_If_GetMethod_Returns_Zero
    @ParameterizedTest(name = "Test_Returns_True_If_GetMethod_Returns_Zero")
    @CsvSource({
            "1, 1, True",
            "2, 2, True",
            "4, 2, True"
    })
    public void Test_Returns_True_If_GetMethod_Returns_Zero(int x, int y, boolean expectedResult) {
        sea = new Sea(10);
        boolean actualResult = sea.isEmpty(x, y);
        Assertions.assertEquals(actualResult, expectedResult);
    }

    // Test_Check_If_GetMethod_Returns_Zero_And_NotNull
    @Test
    public void Test_Check_If_GetMethod_Returns_Zero_And_NotNull() {
        sea = new Sea(10);
        Assertions.assertNotNull(sea.encodedDump());
    }

    // Test_Check_If_GetMethod_Returns_Empty_String
    @Test
    public void Test_Check_If_GetMethod_Returns_Empty_String() {
        sea = new Sea(0);
        Assertions.assertEquals(sea.encodedDump(), "");
    }
}
