package com.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    // load values from file
    public static Integer[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day1/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<Integer> list = new ArrayList<>();

        while ((line = br.readLine()) != null)
            list.add(Integer.parseInt(line));

        return list.toArray(new Integer[list.size()]);
    }

    // Part 1
    public static Integer part1(Integer[] numbers, int start, int end) {

        while( start < end ) {
            int sum = numbers[start] + numbers[end];
            if (sum < 2020)
                start++;
            else if (sum > 2020)
                end--;
            else if (sum == 2020) {
                return numbers[start] * numbers[end];
            }

        }

        return 0;
    }

    // Part 2
    public static Integer part2(Integer[] numbers, int start, int end) {

        while ( start < numbers.length - 2 ) {
            int middle = start + 1;

            while ( middle < end ) {
                int sum = numbers[start] + numbers[middle] + numbers[end];
                if (sum < 2020)
                    middle++;
                else if (sum > 2020)
                    end--;
                else if (sum == 2020) {
                    return numbers[start] * numbers[middle] * numbers[end];
                }
            }

            start++;
            end = numbers.length - 1;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {

        Integer[] numbers = loadFromFile("input.txt");

        // Sort Arrays
        Arrays.sort(numbers);

        System.out.println(part1(numbers, 0, numbers.length - 1));

        System.out.println(part2(numbers, 0, numbers.length - 1));

    }
}
