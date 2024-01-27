# Byte Frequency Analyzer

This Java project reads files, counts the frequency of bytes, and determines the minimum value among bytes with the maximum frequency. It consists of two classes: `ReadThread` and `Solution`.

## Table of Contents

- [Overview](#overview)
- [Classes](#classes)

## Overview

The project includes two Java classes: `ReadThread` and `Solution`. The `ReadThread` class is a thread that reads a file, counts byte frequencies, and finds the minimum value among bytes with the maximum frequency. The `Solution` class contains the main method and a shared `resultMap` to store the results of file processing.

## Classes

### 1. `ReadThread`
- **Description:** This class extends `Thread` and is responsible for reading a file, counting byte frequencies, and finding the minimum value among bytes with the maximum frequency.
- **Methods:**
   - `ReadThread(String fileName)`: Constructor to initialize the thread with the given file name. It starts the thread upon object creation.
   - `run()`: Overrides the `run` method from `Thread` class to execute the file reading and processing logic.
   - `findMin(List<Byte> list)`: Finds the minimum value in a list of bytes.
   - `countFrequencyOfByte(BufferedInputStream reader)`: Counts the frequency of each byte in the input stream.
   - `findMaxNumbers(Map<Byte, Integer> bytes)`: Finds the byte(s) with the maximum frequency in the given map.

### 2. `Solution`
- **Description:** This class contains the main method and a shared `resultMap` to store the results of file processing.
- **Methods:**
   - `main(String[] args)`: Takes user input for file paths, creates `ReadThread` objects, and continues until the user enters "exit".

