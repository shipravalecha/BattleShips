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
}
