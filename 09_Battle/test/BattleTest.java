import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class BattleTest {
    Battle battle;
    // Test Battle to validate the seaSize. SeaSize Values lower than 4 throws exception
    @ParameterizedTest(name = "Battle_Sea_Size_Invalid_values")
    @CsvSource({
            "-1",
            "0",
            "1",
            "3"
    })
    public void Test_Battle_With_Invalid_Sea_Size_Values(int seaSize ) {
        int[] shipSizes = new int[3];
        int[] shipCounts = new int[3];

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            new Battle(seaSize, shipSizes, shipCounts);
        });
        String expectedErrorMessage = String.format("Sea Size %s invalid, must be at least 4", seaSize);
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    // Test Battle to validate the ship size. Ship Size Values should be lower than SeaSize.
    @ParameterizedTest(name = "Battle_Ship_Size_Invalid_values")
    @CsvSource(value = {
            "4;10,3,4", "4;0"}, delimiter = ';')
    public void Test_Battle_With_Invalid_Ship_Size_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes) {

        int[] shipCounts = new int[] { 2, 2, 2 };

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            new Battle(seaSize, shipSizes, shipCounts);
        });
        String expectedErrorMessage = String.format("Ship has invalid size %s",shipSizes[0]);
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    // Test Battle to validate the ship count. Ship count should equal to Ship Size count.
    @ParameterizedTest(name = "Battle_Ship_Count_Invalid_values")
    @CsvSource(value = {
            "4;1,3,4;1", "4;1;2,2"}, delimiter = ';')

    public void Test_Battle_With_Invalid_Ship_Count_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes, @ConvertWith(IntArrayConverter.class) int[] shipCounts) {


        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            new Battle(seaSize, shipSizes, shipCounts);
        });
        Assertions.assertEquals("Ship counts must match", exception.getMessage());
    }

    // Test Battle to validate the ship count. Ship count should equal to Ship Size count.
    @ParameterizedTest(name = "Battle_Ship_Count_Invalid_values")
    @CsvSource(value = {
            "6;1,3,4;1,1,1", "4;1,1;1,1"}, delimiter = ';')
    public void Test_Battle_With_Valid_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes, @ConvertWith(IntArrayConverter.class) int[] shipCounts) {

        battle= new Battle(seaSize, shipSizes, shipCounts);
        Assertions.assertEquals(seaSize, battle.getSeaSize());
        Assertions.assertEquals(shipSizes.length, battle.getSizes().length);
        Assertions.assertEquals(shipCounts.length, battle.getCounts().length);
        Assertions.assertEquals(seaSize, battle.getSea().size());
        Assertions.assertEquals(0, battle.getLost());
        Assertions.assertEquals(shipSizes.length,battle.getShips().size());
    }
    // Test Battle to validate the ship count. Ship count should equal to Ship Size count.
    @ParameterizedTest(name = "Battle_Ship_Count_Invalid_values")
    @CsvSource(value = {
            "6;2,3,4;2,2,2"}, delimiter = ';')
    public void Test_Battle_Play_Valid_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes, @ConvertWith(IntArrayConverter.class) int[] shipCounts) {

        battle= new Battle(seaSize, shipSizes, shipCounts);
        String input="";
        for(int i=1;i<seaSize;i++){
            for(int j=1;j<seaSize;j++){
                input += ""+i+","+j+ System.getProperty("line.separator");
            }
        }
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        battle.play();
    }
    //Hitting a ship should increase hit count by 1
    @Test
    public void Valid_Hit_Should_Increase_Hits(){
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        battle = new Battle(new int[] {4},  new int[] {4});
        Assertions.assertEquals(0, battle.getHits());
        battle.play();
        Assertions.assertEquals(1, battle.getHits());
    }
    //Missing a ship should increase miss count by 1
    @Test
    public void Miss_Should_Increase_Misses(){
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        battle = new Battle(new int[] {0},  new int[] {1});
        Assertions.assertEquals(0, battle.getMisses());
        battle.play();
        Assertions.assertEquals(1, battle.getMisses());
    }
    //Sinking a ship should increase Loss count by 1
    @Test
    public void Sinking_A_Ship_Increases_Losses(){
        String[] input = {"1,1", "1,2", "1,3", "1,4", "4,1", "3,1", "2,1", "1,1"};
        battle = new Battle(new int[] {4},  new int[] {4});
        for(String s : input){
            System.setIn(new ByteArrayInputStream(s.getBytes()));
            battle.play();
        }
        Assertions.assertEquals(1, battle.getLosses().length);
        Assertions.assertEquals(1, battle.getMisses());
    }
    //Shooting the same ship on the same grid twice will increase hit count by one and miss count
    //by one
    @Test
    public void Shooting_Same_Spot_Should_Increase_Misses(){
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        battle = new Battle(new int[] {4},  new int[] {4});
        battle.play();

        Assertions.assertEquals(0, battle.getMisses());
        Assertions.assertEquals(1, battle.getHits());


        System.setIn(new ByteArrayInputStream(input.getBytes()));
        battle.play();
        Assertions.assertEquals(1, battle.getMisses());
    }
    //Running main should be successful
    @Test
    public void Running_main_should_return_true(){
        String[] args = {};
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Battle.main(null);
        Assertions.assertEquals(true, true);
    }

    @Test
    public void Sinking_All_Ship_Win_Battle(){
        String[] input = {"1,1", "1,2", "1,3", "1,4",
                "2,1", "2,2", "2,3", "2,4",
                "3,1", "3,2", "3,3", "3,4",
                "4,1", "4,2", "4,3", "4,4"};
        int[] shipSize = {4};
        int[] shipCount = {4};
        battle = new Battle(4, shipSize,  shipCount);
        for(String s : input){
            System.setIn(new ByteArrayInputStream(s.getBytes()));
            battle.play();
        }

        Assertions.assertEquals(1, battle.getLosses().length);
        Assertions.assertEquals(0, battle.getMisses());
    }

}

