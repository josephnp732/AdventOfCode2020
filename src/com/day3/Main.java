package com.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day3/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null)
            list.add(line);

        return list.toArray(new String[list.size()]);
    }

    private static int part1(String[] entries, int right, int down) {
        int countOfTrees = 0;
        int startPosition = 0;

        for ( int i = 0; i < entries.length; i += down) {
            String row = entries[i].repeat(100); // Random Number

            if ( row.charAt(startPosition) == '#' ) {
                countOfTrees++;
            }

            startPosition += right;
        }

        return countOfTrees;

    }

    private static int part2(String[] entries) {
        int[][] part2 = new int[][]{{1,1},{3,1},{5,1},{7,1},{1,2}};
        List<Integer> part2Values = new ArrayList<>();
        int part2Result = 1;

        for(int[] row : part2) {
            part2Values.add(part1(entries, row[0], row[1]));
        }

        for(int val : part2Values)
            part2Result *= val;

        return part2Result;
    }


    public static void main(String[] args) throws IOException {
        String[] values = loadFromFile("input.txt");

        System.out.println(part1(values, 3, 1));

        System.out.println(part2(values));
    }

}
