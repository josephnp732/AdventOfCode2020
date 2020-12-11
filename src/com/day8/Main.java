package com.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day8/" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null)
            list.add(line);

        return list.toArray(new String[list.size()]);
    }

    public static int part1(String[] entries) {

        for ( int k = 0; k < entries.length; k++)
            entries[k] = entries[k] + " false";

        int acc = 0;
        int i = 0;

        while (true && i < entries.length - 1) {
            String entry = entries[i];
            char sign = entry.split(" ")[1].charAt(0);
            int val = Integer.parseInt(entry.split(" ")[1].substring(1));
            boolean visited = Boolean.valueOf(entry.split(" ")[2]);
            String opp = entry.split(" ")[0];

            if(visited)
                break;

            entries[i] = opp + " " + sign + val + " " + !visited;

            switch (opp) {
                case "nop": {
                    i++;
                    continue;
                }
                case "acc": {
                    if (sign == '-')
                        acc -= val;
                    else
                        acc += val;
                    i++;
                    continue;
                }
                case "jmp": {
                    if (sign == '-')
                        i -= val;
                    else
                        i += val;
                    continue;
                }
            }
        }

        if ( i == entries.length - 1)
            System.out.println("Part 2: " + acc);

        return acc;
    }

    public static int part2(String[] entries) {
        int acc = 0;

        for (int k = 0; k < entries.length; k++) {
            if (!entries[k].split(" ")[0].equals("acc"))
                entries[k] = entries[k].split(" ")[0].equals("nop") ? entries[k].replace("nop","jmp") : entries[k].replace("jmp","nop");;

            // create deep copy of array
            String[] newArray = new String[entries.length];
            for(int j = 0; j < entries.length - 1; j++)
                newArray[j] = entries[j];

            part1(newArray);

            if (!entries[k].split(" ")[0].equals("acc"))
                entries[k] = entries[k].split(" ")[0].equals("nop") ? entries[k].replace("nop","jmp") : entries[k].replace("jmp","nop");;

        }

        return acc;
    }

    public static void main(String[] args) throws IOException {
        String[] values = loadFromFile("input.txt");

//        System.out.println(part1(values));

        part2(values);
    }
}
