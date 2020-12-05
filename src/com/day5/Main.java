package com.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day5/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null)
            list.add(line);

        return list.toArray(new String[list.size()]);
    }

    public static void Solution(String[] entries) {

        // Part 1 Starts here
        int highestSeatNo = 0;

        List<Integer> seatList = new ArrayList<>();

        for (String entry : entries) {

            int rowStart = 0;
            int rowEnd = 127;
            int colStart = 0;
            int colEnd = 7;

            String rows = entry.substring(0, 7);
            String cols = entry.substring(7);

            char[] rowTokens = rows.toCharArray();
            char[] colTokens = cols.toCharArray();

            int rowCounter = 0;
            int colCounter = 0;

            // Rows
            while (rowCounter < 7) {

                if (rowTokens[rowCounter] == 'F') {
                    rowEnd = ((rowEnd - rowStart) / 2) + rowStart;
                } else if (rowTokens[rowCounter] == 'B') {
                    rowStart = ((rowEnd - rowStart) / 2) + rowStart + 1;
                }

                rowCounter++;
            }

            // Columns
            while (colCounter < 3) {

                if (colTokens[colCounter] == 'L') {
                    colEnd = ((colEnd - colStart) / 2) + colStart;
                } else if (colTokens[colCounter] == 'R') {
                    colStart = ((colEnd - colStart) / 2) + colStart + 1;
                }

                colCounter++;
            }

            int seatNo = rowEnd * 8 + colEnd;
            seatList.add(seatNo);
            highestSeatNo = highestSeatNo < seatNo ? seatNo : highestSeatNo;
        }

        System.out.println("Part 1: Highest Seat Number: " + highestSeatNo);

        Collections.sort(seatList);

        for ( int i = 0 ; i < seatList.size() - 2; i++ ) {
            if(seatList.get(i+1) != seatList.get(i) + 1) {
                System.out.println("Part 2: My Seat Number: " + (seatList.get(i) + 1) + " ");
                break;
            }
        }
    }

    // Part 2
    public static int part2(String[] entries) {
        int highestSeatNo = 0;

        for ( String entry : entries ) {

        }

        return highestSeatNo;
    }
    public static void main(String[] args) throws IOException {
        String[] values = loadFromFile("input.txt");

        Solution(values);
    }
}
