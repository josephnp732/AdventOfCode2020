package com.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day2/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null)
            list.add(line);

        return list.toArray(new String[list.size()]);
    }

    // Part 1
    public static int part1(String[] entries) {

        int countOfValidPasswords = 0;

        for( String entry : entries ) {

            String[] tokens = entry.split(" ");
            int low = Integer.parseInt(tokens[0].split("-")[0]);
            int high = Integer.parseInt(tokens[0].split("-")[1]);
            char letter = tokens[1].charAt(0);
            String password = tokens[2];

            Map<Character, Integer> letterMap = new HashMap<>();
            for( char let : password.toCharArray() ) {
                letterMap.put(let, letterMap.get(let) == null ? 1 : letterMap.get(let) + 1);
            }

            int countOfLetters = letterMap.get(letter) == null ? 0 : letterMap.get(letter);

            if ( countOfLetters >= low && countOfLetters <= high ) {
                countOfValidPasswords++;
            }
        }

        return countOfValidPasswords;
    }

    // Part 2
    public static int part2(String[] entries) {
        int countOfValidPasswords = 0;

        for( String entry : entries ) {

            String[] tokens = entry.split(" ");
            int low = Integer.parseInt(tokens[0].split("-")[0]);
            int high = Integer.parseInt(tokens[0].split("-")[1]);
            char letter = tokens[1].charAt(0);
            String password = tokens[2];

            char charAtLow = '@';
            char charAtHigh = '@';

            try {
                charAtLow = password.charAt(low - 1);
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }

            try {
                charAtHigh = password.charAt(high - 1);
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }

            if ( (charAtLow == letter && charAtHigh != letter) || (charAtHigh == letter && charAtLow != letter) ) {
                countOfValidPasswords++;
            }

        }

        return countOfValidPasswords;
    }

    public static void main(String[] args) throws IOException {

        String[] values = loadFromFile("input.txt");

        System.out.println(part1(values));
        System.out.println(part2(values));
    }
}
