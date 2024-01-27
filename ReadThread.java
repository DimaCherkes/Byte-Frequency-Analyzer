package com.javarush.task.task18.task1823;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadThread extends Thread {
    String fileName;

    public ReadThread(String fileName) {
        // Constructor to initialize the thread with the given fileName
        this.fileName = fileName;
        start();  // Start the thread upon object creation
    }

    // The main logic for reading the file and processing byte frequencies
    @Override
    public void run() {
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileName))) {

            // Count the frequency of each byte in the file
            Map<Byte, Integer> byteCounts = countFrequencyOfByte(reader);

            // Find the byte(s) with the maximum frequency
            List<Byte> list = findMaxNumbers(byteCounts);

            // Find and store the minimum value among the bytes with maximum frequency
            int number = findMin(list);
            Solution.resultMap.put(fileName, number);  // Store the result in a shared data structure
            System.out.println((char) number);  // Print the result (cast to char for readability)

        } catch (IOException e) {
            // Handle IOException by printing a message
            System.out.println("IOException!!!");
        }
    }

    // Find the minimum value in a list of bytes
    private int findMin(List<Byte> list) {
        byte min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (min > list.get(i)) {
                min = list.get(i);
            }
        }
        return min;
    }

    // Count the frequency of each byte in the BufferedInputStream
    private Map<Byte, Integer> countFrequencyOfByte(BufferedInputStream reader) throws IOException {
        Map<Byte, Integer> byteCounts = new HashMap<>();

        // Loop through the input stream until there are no more bytes
        while (reader.available() > 0) {
            byte byt = (byte) reader.read();
            byteCounts.put(byt, byteCounts.getOrDefault(byt, 0) + 1);
        }
        return byteCounts;
    }

    // Find the byte(s) with the maximum frequency in the given map
    private List<Byte> findMaxNumbers(Map<Byte, Integer> bytes) {
        List<Byte> list = new ArrayList<>();
        int max = 0;

        // Iterate through the map entries to find the maximum frequency
        for (Map.Entry<Byte, Integer> pair : bytes.entrySet()) {
            if (pair.getValue() > max) {
                list.clear();
                list.add(pair.getKey());
                max = pair.getValue();
            } else if (pair.getValue() == max) {
                list.add(pair.getKey());
            }
        }
        return list;
    }
}

