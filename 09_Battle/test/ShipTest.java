import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class ShipTest {
    Ship ship;

    @ParameterizedTest(name = "Id_Function_Valid_Value")
    @CsvSource({
            "1, 1",
            "5, 5",
            "10, 10"
    })
    public void Id_Function_Valid_Value(int id, int expectedId){
        ship = new Ship(id, 5);
        int IdActualResult = ship.id();
        Assertions.assertEquals(IdActualResult, expectedId);
    }


    @ParameterizedTest(name = "Size_Function_Valid_Value")
    @CsvSource({
            "1, 1",
            "5, 5",
            "10, 10"
    })

    public void Sz_Function_Valid_Value(int sz, int expectedSz){
        ship = new Ship(1, sz);
        int IdActualResult = ship.size();
        Assertions.assertEquals(IdActualResult, expectedSz);
    }

    // return, exceptions, conditions loop

    // For boolean place:
    // arrange
    // use fake/mock to call place
    // 1. -> place --> add sea object with attributes, if placed, or not placed


    // 2. cover all switch statements : passing in paramteried tests for all cases
    // also make sure to catch all exceptions

    // 3. two scenarios: s is empty and s is not empty (passing x and y),
    // make it return false is good enough

    // 4. if , else in while loop. manipulate what it goes in to if (use mock on
    // extenship). once it goes to else, two more scenarios



    // ExtendShip -> all return statements needs to be covered ( test or statement as well)
    // Create a simple constructor for Sea s, maybe needs to mock for the isEmpty()

    // placeRandom -> test if and exception, initialize a full sea, x and y is not
    // in consideration anymore.. and it will fail (for 1000 times). -->
    // or Just use mock to make place return false, then exception would be thrown

    // wasHit -> three conditions if, else, return

    // Hit no return value ???, no worries maybe





    //ColorMode colorMock = mock(ColorMode.class);
    //when(colorMock.getColor(anyInt())).thenReturn(Color.WHITISH);
    //AdjustableLamp lamp = new AdjustableLamp(colorMock.getColor(anyInt()));
    //Color expected = Color.DIM;
    //// Act: refresh twice
    //        Color actual;
    //        actual = lamp.refresh();
    //        actual = lamp.refresh();
    //
    //        // Assert
    //        assertEquals(expected, actual);
    //        verify(colorMock, times(1)).getColor(anyInt());
}
