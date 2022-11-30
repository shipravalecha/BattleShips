import java.io.*;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;



public class UITest {
    Battle battle;

    //Sinking a ship should increase Loss count by 1
    @Test
    public void UI_Sinking_A_Ship() throws FileNotFoundException {
        PrintStream console = System.out;
        PrintStream fileOut = new PrintStream("./09_Battle/output/UI_Sinking_A_Ship.txt");
        String[] input = {"1,1", "1,2", "1,3", "1,4", "4,1", "3,1", "2,1", "1,1"};

        System.setOut(fileOut);

        battle = new Battle(new int[] {4},  new int[] {4});
        for(String s : input){
            System.setIn(new ByteArrayInputStream(s.getBytes()));
            battle.play();
        }

        System.out.println("Ships Sunk: " + battle.getLosses().length);

        System.setOut(console);
        Assertions.assertEquals(1, battle.getLosses().length);
        Assertions.assertEquals(1, battle.getMisses());
    }

    //Running main should be successful
    @Test
    public void UI_Test_Win() throws FileNotFoundException {
        PrintStream console = System.out;
        PrintStream fileOut = new PrintStream("./09_Battle/output/UI_Test_Win.txt");

        String[] input = {"1,1", "1,2", "1,3", "1,4", "1,5", "1,6",
                "2,1", "2,2", "2,3", "2,4", "2,5", "2,6",
                "3,1", "3,2", "3,3", "3,4", "3,5", "3,6",
                "4,1", "4,2", "4,3", "4,4", "4,5", "4,6",
                "5,1", "5,2", "5,3", "5,4", "5,5", "5,6",
                "6,1", "6,2", "6,3", "6,4", "6,5", "6,6",
        };

        System.setOut(fileOut);

        battle = new Battle(6,                        // Sea is 6 x 6 tiles
                new int[] { 2, 3, 4 },    // Ships are of sizes 2, 3, and 4
                new int[] { 2, 2, 2 });   // there are two ships of each size

        for(String s : input){
            System.setIn(new ByteArrayInputStream((s.getBytes())));
            battle.play();
        }

        System.setOut(console);
        Assertions.assertEquals(true, true);
    }

    //Missing a ship should increase miss count by 1
    @Test
    public void Miss_Should_Increase_Misses() throws FileNotFoundException {
        PrintStream console = System.out;
        PrintStream fileOut = new PrintStream("./09_Battle/output/Miss_Should_Increase_Misses.txt");
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        System.setOut(fileOut);

        battle = new Battle(new int[] {0},  new int[] {1});

        System.out.println("Shots Missed: " + battle.getMisses());
        Assertions.assertEquals(0, battle.getMisses());

        battle.play();

        System.out.println("Shots Missed: " + battle.getMisses());
        Assertions.assertEquals(1, battle.getMisses());

        System.setOut(console);

    }


    //Sinking a ship should increase Loss count by 1
    @Test
    public void UI_Sinking_A_Ship_No_File() throws InterruptedException {
        String[] input = {"1,1", "1,2", "1,3", "1,4", "4,1", "3,1", "2,1", "1,1"};

        battle = new Battle(new int[] {4},  new int[] {4});
        for(String s : input){
            System.setIn(new ByteArrayInputStream(s.getBytes()));
            battle.play();
            System.out.println(s);
            Thread.sleep(1000);
        }

        System.out.println("Ships Sunk: " + battle.getLosses().length);

        Assertions.assertEquals(1, battle.getLosses().length);
        Assertions.assertEquals(1, battle.getMisses());
    }


    //Running main should be successful
    @Test
    public void UI_Test_Win_No_File() throws InterruptedException {

        String[] input = {"1,1", "1,2", "1,3", "1,4", "1,5", "1,6",
                "2,1", "2,2", "2,3", "2,4", "2,5", "2,6",
                "3,1", "3,2", "3,3", "3,4", "3,5", "3,6",
                "4,1", "4,2", "4,3", "4,4", "4,5", "4,6",
                "5,1", "5,2", "5,3", "5,4", "5,5", "5,6",
                "6,1", "6,2", "6,3", "6,4", "6,5", "6,6",
        };

        battle = new Battle(6,                        // Sea is 6 x 6 tiles
                new int[] { 2, 3, 4 },    // Ships are of sizes 2, 3, and 4
                new int[] { 2, 2, 2 });   // there are two ships of each size

        for(String s : input){
            System.setIn(new ByteArrayInputStream((s.getBytes())));
            battle.play();
            System.out.println(s);
            Thread.sleep(1000);
        }

        Assertions.assertEquals(true, true);
    }

    //Missing a ship should increase miss count by 1
    @Test
    public void Miss_Should_Increase_Misses_No_File() {
        String input = "1,1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        battle = new Battle(new int[] {0},  new int[] {1});

        System.out.println("Shots Missed: " + battle.getMisses());
        Assertions.assertEquals(0, battle.getMisses());

        battle.play();

        System.out.println("Shots Missed: " + battle.getMisses());
        Assertions.assertEquals(1, battle.getMisses());

    }
}
