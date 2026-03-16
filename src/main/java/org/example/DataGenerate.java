package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerate {
    Random random = new Random();
    int dataCount = 100;
    public void generate() {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\User\\IdeaProjects\\PatienceSort\\src\\main\\java\\org\\example\\data.txt")) {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>(100);

        for (int j = 0; j < 100; j++) {
            int size = random.nextInt(100, 10000);
            ArrayList<Integer> list = new ArrayList<>(random.nextInt(size));
            data.add(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                int number = random.nextInt(10000);
                list.add(number);
                fileWriter.write(number + ";");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
