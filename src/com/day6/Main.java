package com.day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day6/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        List<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(" ");
            if (line.isEmpty()) {
                list.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }

        if ( !stringBuilder.toString().isEmpty() )
            list.add(stringBuilder.toString());

        return list.toArray(new String[list.size()]);
    }

    // Part 1
    public static int part1(String[] entries) {
        int sum = 0;
        for(String entry : entries) {
            entry = entry.replace(" ", "");
            char[] questions = entry.toCharArray();
            Set<Character> qSet= new HashSet<>();

            for (char q : questions) {
                qSet.add(q);
            }
            sum += qSet.size();
        }

        return sum;
    }

    // Part 2
    public static int part2(String[] entries) {
        int sum = 0;
        for(String entry : entries) {
            String[] group = entry.split(" ");
            System.out.println(Arrays.asList(group));
            if (group.length == 1) {
                sum += group[0].length();
                continue;
            }
            else {
                char[] firstWord = group[0].toCharArray();
                Set<Character> set = new HashSet<>();

                for(char c : firstWord) {
                    int counter = 0;
                    for(String word : Arrays.copyOfRange(group, 1, group.length)) {
                        if(word.contains(String.valueOf(c)))
                            counter++;
                    }
                    if (counter == group.length - 1)
                        set.add(c);
                }
                sum += set.size();
                continue;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        String[] values = loadFromFile("input.txt");

        System.out.println(part1(values));
        System.out.println(part2(values));
    }
}
