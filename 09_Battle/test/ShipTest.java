import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


public class ShipTest {
    Ship ship;
    Ship shipTwo;

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

    @Test
    public void test_If_Ship_is_Sunk(){
        ship = new Ship(1,1);
        assertFalse(ship.isSunk());
    }

    @InjectMocks
    Sea seaMock = new Sea(1);
    @Test
    public void testPlaceRandom(){
        ship = new Ship(1,4);
        String message = "Could not place any more ships";

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, ()->{
            ship.placeRandom(seaMock);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    @InjectMocks
    Sea seaMockForPlaced = new Sea(4);
    @Test
    public void test_If_Ship_is_Placed(){
        ship = new Ship(1,4);
        ship.place(seaMockForPlaced, 1,1,1);
        String message ="Program error - placed ship "+ 1 + " twice";
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->{
            ship.place(seaMockForPlaced,1,1,1);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    @Test
    public void test_If_orientation_Is_Invalid(){
        ship = new Ship(1,4);
        String message = "Invalid orientation 5";
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->{
            ship.place(seaMockForPlaced,1,1,5);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    @ParameterizedTest(name = "test_If_orientation_Is_Valid")
    @CsvSource({
            "0,0",
            "1,1",
            "2,2",
            "3,3"
    })
    public void test_If_orientation_Is_Valid(int orient, int expectedOrient){
        ship = new Ship(1,1);
        shipTwo = new Ship(2,1);
        Assertions.assertEquals(ship.place(seaMockForPlaced, 1, 1, orient),
                shipTwo.place(seaMockForPlaced, 0,1, expectedOrient));

    }

    @Test
    public void test_If_Starting_Position_Is_Occupied(){
        ship = new Ship(1,1);
        shipTwo = new Ship(2,1);
        ship.place(seaMockForPlaced, 1,1,1);
        assertFalse(shipTwo.place(seaMockForPlaced,1,1,1));

    }

    @InjectMocks
    Sea seaMockForHit = new Sea(1);
    @Test
    public void test_If_Ship_is_Hit_And_Sunk(){
        ship = new Ship(1,1);
        ship.place(seaMockForHit,1,1,0);
        ship.hit(0,1);
        assertTrue(ship.isSunk());

    }

    @Test
    public void test_If_Ship_was_Hit(){
        ship = new Ship(1,1);
        ship.place(seaMockForHit,1,1,0);
        ship.hit(0,1);
        assertTrue(ship.wasHit(0,1));
    }

}
