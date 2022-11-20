import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
            "4;10,3,4", "4;0"}, delimiterString = ";")
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
            "4;1,3,4;1", "4;1;2,2"}, delimiterString = ";")

    public void Test_Battle_With_Invalid_Ship_Count_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes, @ConvertWith(IntArrayConverter.class) int[] shipCounts) {


        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            new Battle(seaSize, shipSizes, shipCounts);
        });
        Assertions.assertEquals("Ship counts must match", exception.getMessage());
    }

    // Test Battle to validate the ship count. Ship count should equal to Ship Size count.
    @ParameterizedTest(name = "Battle_Ship_Count_Invalid_values")
    @CsvSource(value = {
            "6;1,3,4;1,1,1", "4;1,1;1,1"}, delimiterString = ";")
    public void Test_Battle_With_Valid_Values(int seaSize, @ConvertWith(IntArrayConverter.class) int[] shipSizes, @ConvertWith(IntArrayConverter.class) int[] shipCounts) {

        battle= new Battle(seaSize, shipSizes, shipCounts);
        Assertions.assertEquals(seaSize, battle.getSeaSize());
        Assertions.assertEquals(shipSizes.length, battle.getSizes().length);
        Assertions.assertEquals(shipCounts.length, battle.getCounts().length);
        Assertions.assertEquals(seaSize, battle.getSea().size());
    }
    // Test Battle to validate the ship count. Ship count should equal to Ship Size count.
    @ParameterizedTest(name = "Battle_Ship_Count_Invalid_values")
    @CsvSource(value = {
            "6;2,3,4;2,2,2"}, delimiterString = ";")
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
}

