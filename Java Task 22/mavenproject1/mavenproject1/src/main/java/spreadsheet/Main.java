package spreadsheet;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();

        while (true) {
            System.out.println("Spreadsheet Menu:");
            System.out.println("RF <text file pathname> - Read commands from File");
            System.out.println("C - Create a New Spreadsheet");
            System.out.println("E <cell coordinate> <new cell content> - Edit a cell");
            System.out.println("L <SV2 file pathname> - Load a Spreadsheet from a file");
            System.out.println("S <SV2 file pathname> - Save the Spreadsheet to a file");
            System.out.println("Exit - Exit the program");
            System.out.print("Enter command: ");

            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                switch (command) {
                    case "RF":
                        if (parts.length < 2) {
                            System.out.println("Error: File path is required.");
                            break;
                        }
                        readCommandsFromFile(parts[1], spreadsheet);
                        break;
                    case "C":
                        spreadsheet = new Spreadsheet();
                        System.out.println("Created a new empty spreadsheet.");
                        break;
                    case "E":
                        if (parts.length < 2) {
                            System.out.println("Error: Cell coordinate and content are required.");
                            break;
                        }
                        editCell(parts[1], spreadsheet);
                        break;
                    case "L":
                        if (parts.length < 2) {
                            System.out.println("Error: File path is required.");
                            break;
                        }
                        spreadsheet.loadFromFile(parts[1]);
                        System.out.println("Spreadsheet loaded from " + parts[1]);
                        break;
                    case "S":
                        if (parts.length < 2) {
                            System.out.println("Error: File path is required.");
                            break;
                        }
                        spreadsheet.saveToFile(parts[1]);
                        System.out.println("Spreadsheet saved to " + parts[1]);
                        break;
                    case "Exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("I/O Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            spreadsheet.printSpreadsheet();
        }
    }

   private static void readCommandsFromFile(String filePath, Spreadsheet spreadsheet) {
    System.out.println("Reading commands from file: " + filePath);
    try (Scanner fileScanner = new Scanner(new FileInputStream(filePath))) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println("Processing command: " + line);
            String[] parts = line.split(" ", 2);
            String command = parts[0];
            switch (command) {
                case "C":
                    spreadsheet = new Spreadsheet();
                    System.out.println("Created a new empty spreadsheet.");
                    break;
                case "E":
                    if (parts.length >= 2) {
                        editCell(parts[1], spreadsheet);
                    } else {
                        System.out.println("Error in file: Cell coordinate and content are required.");
                    }
                    break;
               
                case "S":
                    if (parts.length >= 2) {
                        spreadsheet.saveToFile(parts[1]);
                        System.out.println("Spreadsheet saved to " + parts[1]);
                    } else {
                        System.out.println("Error in file: File path is required.");
                    }
                    break;
                     case "L":
                    if (parts.length >= 2) {
                        spreadsheet.loadFromFile(parts[1]);
                        System.out.println("Spreadsheet loaded from " + parts[1]);
                    } else {
                        System.out.println("Error in file: File path is required.");
                    }
                    break;
                default:
                    System.out.println("Invalid command in file: " + line);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error processing command from file: " + e.getMessage());
    }
}


    private static void editCell(String args, Spreadsheet spreadsheet) {
        String[] parts = args.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Error: Cell coordinate and content are required.");
            return;
        }
        String coordinate = parts[0];
        String content = parts[1];
        Content parsedContent = spreadsheet.parseContent(content);
        spreadsheet.setCell(coordinate, parsedContent);
    }
}
