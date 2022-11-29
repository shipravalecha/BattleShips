import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;


public class ShipTest {
    Ship ship;
    Ship shipTwo;

    // Parameterized test for id function with valid input
    @ParameterizedTest(name = "test_Id_Function_with_Valid_Value")
    @CsvSource({
            "1, 1",
            "5, 5",
            "10, 10"
    })
    public void test_Id_Function_with_Valid_Value(int id, int expectedId){
        ship = new Ship(id, 5);
        int IdActualResult = ship.id();
        Assertions.assertEquals(IdActualResult, expectedId);
    }

    // Parameterized test for size function with valid input
    @ParameterizedTest(name = "test_Size_Function_With_Valid_Value")
    @CsvSource({
            "1, 1",
            "5, 5",
            "10, 10"
    })
    public void test_Size_Function_With_Valid_Value(int sz, int expectedSz){
        ship = new Ship(1, sz);
        int IdActualResult = ship.size();
        Assertions.assertEquals(IdActualResult, expectedSz);
    }

    // Check if isSunk function has return default status (false)
    @Test
    public void test_If_Ship_is_Sunk(){
        ship = new Ship(1,1);
        assertFalse(ship.isSunk());
    }

    // Using mock to test placeRandom function
    @InjectMocks
    Sea seaMock = new Sea(1);
    @Test
    public void test_PlaceRandom(){
        ship = new Ship(1,4);
        String message = "Could not place any more ships";

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, ()->{
            ship.placeRandom(seaMock);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    // Using mock to test place function, with the scenario of catching exception
    // for replacing ship twice
    @InjectMocks
    Sea seaMockForPlaced = new Sea(4);
    @Test
    public void test_If_Ship_is_Placed_Twice(){
        ship = new Ship(1,4);
        ship.place(seaMockForPlaced, 1,1,1);
        String message ="Program error - placed ship "+ 1 + " twice";
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->{
            ship.place(seaMockForPlaced,1,1,1);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    // Test place function for valid input
    @Test
    public void test_If_Ship_is_Placed(){
        ship = new Ship(1, 2);
        assertTrue(ship.place(seaMockForPlaced, 0,1,0));

    }

    // Test place function for invalid orientation
    @Test
    public void test_If_Orientation_Is_Invalid(){
        ship = new Ship(1,4);
        String message = "Invalid orientation 5";
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->{
            ship.place(seaMockForPlaced,1,1,5);
        });

        Assertions.assertEquals(message, runtimeException.getMessage());

    }

    // Place two ships with exact same parameters, and compare the result
    @ParameterizedTest(name = "test_If_orientation_Is_Valid")
    @CsvSource({
            "0,0",
            "1,1",
            "2,2",
            "3,3"
    })
    public void test_If_Orientation_Is_Valid(int orient, int expectedOrient){
        ship = new Ship(1,1);
        shipTwo = new Ship(2,1);
        Assertions.assertEquals(ship.place(seaMockForPlaced, 1, 1, orient),
                shipTwo.place(seaMockForPlaced, 0,1, expectedOrient));

    }

    // Place ship one in position and try to place ship two at the same starting
    // coordinate
    @Test
    public void test_If_Starting_Position_Is_Occupied(){
        ship = new Ship(1,1);
        shipTwo = new Ship(2,1);
        ship.place(seaMockForPlaced, 1,1,1);
        assertFalse(shipTwo.place(seaMockForPlaced,1,1,1));

    }

    // Input valid coordinate for hit function and test the condition of the ship
    @InjectMocks
    Sea seaMockForHit = new Sea(1);
    @Test
    public void test_If_Ship_is_Hit_And_Sunk(){
        ship = new Ship(1,1);
        ship.place(seaMockForHit,1,1,0);
        ship.hit(0,1);
        assertTrue(ship.isSunk());

    }

    // Using orientation of Y coordinate to test hit function
    @Test
    public void test_If_Ship_is_Hit_Using_Y_Coordinate(){
        ship = new Ship(1,2);
        ship.place(seaMockForPlaced,0,1,2);
        ship.hit(0,1);
        assertFalse(ship.isSunk());

    }

    // Test if hit function is matching the player's coordinate input
    @Test
    public void test_If_Ship_was_Hit(){
        ship = new Ship(1,1);
        ship.place(seaMockForHit,1,1,0);
        ship.hit(0,1);
        assertTrue(ship.wasHit(0,1));

    }

    // Test if the ship has already been hit using Y coordinate
    @Test
    public void test_If_Ship_Was_Hit_Using_Y_Coordinate(){
        ship = new Ship(1,2);
        ship.place(seaMockForPlaced,0,1,2);
        ship.hit(0,1);
        assertTrue(ship.wasHit(0,1));

    }

    // Test if two tiles in the sea has been occupied by same ship
    @Test
    public void test_If_Ship_Collide_Tiles(){
        ship = new Ship(1,2);
        ship.place(seaMockForPlaced, 0, 1,2);
        assertTrue(ship.extendShipHelp(seaMockForPlaced, 1,1,0,0));

    }

}
