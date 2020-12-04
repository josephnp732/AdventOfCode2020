package com.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    // load values from file
    public static String[] loadFromFile(String fileName) throws IOException {

        File file = new File("src/com/day4/" + fileName);

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

    public static boolean isValid(String[] tokens, String entry) {
        return (tokens.length == 8) || (tokens.length == 8 - 1 && !entry.contains("cid"));
    }

    public static int part1(String[] entries) {
        int validPassports = 0;

        for ( String entry : entries ) {

            String[] tokens = entry.split(" ");
            if ( isValid(tokens, entry) ) {
                validPassports++;
            }
        }

        return validPassports;
    }

    public static int part2(String[] entries) {
        int validPassports = 0;

        for ( String entry : entries ) {
            // create a new HashMap
            Map<String, String> map = new HashMap<>();
            String[] tokens = entry.split(" ");
            for ( String token : tokens) {
                String key = token.split(":")[0];
                String value = token.split(":")[1];
                map.put(key, value);
            }

                int countOfValues = 0;

                for ( String key : map.keySet() ) {
                    switch (key) {
                        case "byr" : {
                            int byr = Integer.parseInt(map.getOrDefault("byr", "0"));
                            if ( byr >= 1920 && byr <= 2002 )
                                countOfValues++;
                            continue;
                        }
                        case "iyr" : {
                            int iyr = Integer.parseInt(map.getOrDefault("iyr", "0"));
                            if ( iyr >= 2010 && iyr <= 2020 )
                                countOfValues++;
                            continue;
                        }
                        case "eyr" : {
                            int eyr = Integer.parseInt(map.getOrDefault("eyr", "0"));
                            if ( eyr >= 2020 && eyr <=2030 )
                                countOfValues++;
                            continue;
                        }
                        case "hgt" : {
                            String hgt = map.getOrDefault("hgt", "0");
                            try {
                            int heightVal = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                            switch (hgt.substring(hgt.length() - 2)) {
                                case "in" : {
                                    if ( heightVal >= 59 && heightVal <= 76 )
                                        countOfValues++;
                                    continue;
                                }
                                case "cm" : {
                                    if ( heightVal >= 150 && heightVal <= 193 )
                                        countOfValues++;
                                    continue;
                                }
                            }
                            continue;
                            } catch (NumberFormatException e) {
                                continue;
                            }
                        }
                        case "hcl" : {
                            String hcl = map.getOrDefault("hcl", "");
                            if ( hcl.charAt(0) == '#' && hcl.length() == 7 )
                                countOfValues++;
                            continue;
                        }
                        case "ecl" : {
                            String ecl = map.getOrDefault("ecl", "");
                            Set<String> eyeColors = new HashSet<>();
                            eyeColors.add("amb"); eyeColors.add("blu"); eyeColors.add("brn"); eyeColors.add("gry"); eyeColors.add("grn"); eyeColors.add("hzl"); eyeColors.add("oth");
                            if ( eyeColors.contains(ecl) )
                                countOfValues++;
                            continue;
                        }
                        case "pid" : {
                            String pid = map.getOrDefault("pid", "");
                            if ( pid.length() == 9 )
                                countOfValues++;
                            continue;
                        }
                        case "cid" : {
//                            String cid = map.getOrDefault("cid", "");
                            countOfValues++;
                        }
                    }
                }

                System.out.println(Arrays.asList(tokens) + " " + tokens.length + " " + countOfValues);
                if ( countOfValues == tokens.length  && isValid(tokens, entry))
                    validPassports++;
        }

        return validPassports;
    }

    public static void main(String[] args) throws IOException {
        String[] values = loadFromFile("input.txt");

        System.out.println(part1(values));

        System.out.println(part2(values));
    }
}
