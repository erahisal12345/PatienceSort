package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        PatienceSortRealisation<Integer> patienceSort = new PatienceSortRealisation<>();
        DataGenerate dataGenerate = new DataGenerate();
        dataGenerate.generate();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\PatienceSort\\src\\main\\java\\org\\example\\data.txt"));

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < line.length; i++) {
                    list.add(Integer.valueOf(line[i]));
                }
                for (int warmup = 0; warmup < 3; warmup++) {
                    patienceSort.sort(new ArrayList<>(list));
                }

                long startTime = System.nanoTime();

                patienceSort.sort(list);

                long endTime = System.nanoTime();
                System.out.println(list.size() +";"+ (double) (endTime-startTime)/1000000 +";" + patienceSort.getIterations());

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




        }

}