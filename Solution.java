package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        while (!path.equals("exit")) {
            ReadThread thread = new ReadThread(path);
            path = reader.readLine();
        }

    }

    public static class ReadThread extends Thread {
        String fileName;
        public ReadThread(String fileName) throws FileNotFoundException, IOException {
            //implement constructor body
            this.fileName = fileName;
            start();
        }
        // implement file reading here
        @Override
        public void run() {
            try(BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileName))) {

                Map<Byte, Integer> byteCounts = countFrequencyOfByte(reader);

                List<Byte> list = findMaxNumbers(byteCounts);

                int number = findMin(list);
                resultMap.put(fileName, number);
                System.out.println((char) number);

            } catch ( IOException e) {
                System.out.println("IOException!!!");
            }

        }

        private int findMin(List<Byte> list) {
            byte min = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (min > list.get(i)) {
                    min = list.get(i);
                }
            }
            return min;
        }

        private Map<Byte, Integer> countFrequencyOfByte(BufferedInputStream reader) throws IOException {
            Map<Byte, Integer> byteCounts = new HashMap<>();

            while (reader.available() > 0) {
                byte byt = (byte) reader.read();
                byteCounts.put(byt, byteCounts.getOrDefault(byt, 0) + 1);
            }
            return byteCounts;
        }

        private List<Byte> findMaxNumbers(Map<Byte, Integer> bytes) {
            List<Byte> list = new ArrayList<>();
            int max = 0;

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
}
