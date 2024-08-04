package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.Cell;
import java.util.*;
import java.io.*;

public class Spreadsheet {
    private Map<Coordinate, Cell> cells = new HashMap<>();
    private Tokenizer tokenizer = new Tokenizer();
    private Parser parser = new Parser();
    private ShuntingYard shuntingYard = new ShuntingYard();
    private PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
    private CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker();

    public void setCell(String coordinateStr, Content content) {
        Coordinate coordinate = parseCoordinate(coordinateStr);
        cells.put(coordinate, new Cell(coordinate, content));
        try {
            circularDependencyChecker.checkForCircularDependencies(coordinate, this);
            processFormulas(); // Update dependent formulas
        } catch (CircularDependencyException e) {
            System.err.println("Circular dependency error: " + e.getMessage());
            cells.remove(coordinate); // Revert the change to avoid circular dependency
        } catch (Exception e) {
            System.err.println("Error processing formulas: " + e.getMessage());
        }
    }

    public String getCellContent(String coordinateStr) {
        Coordinate coordinate = parseCoordinate(coordinateStr);
        Cell cell = cells.get(coordinate);
        return cell == null ? "" : cell.getContent().toString();
    }

    public double getCellValue(String coordinateStr) {
        try {
            Coordinate coordinate = parseCoordinate(coordinateStr);
            Cell cell = cells.get(coordinate);
            if (cell == null) {
                return 0;
            }

            Content content = (Content) cell.getContent();
            if (content instanceof Formula) {
                return evaluateFormula((Formula) content);
            }

            return (Double) content.getValue();
        } catch (Exception e) {
            // Log the error and return a default value or an error indicator
            System.err.println("Error evaluating cell value at " + coordinateStr + ": " + e.getMessage());
            return Double.NaN; // Use Double.NaN to indicate an error
        }
    }

    private double evaluateFormula(Formula formula) throws Exception {
        String formulaStr = formula.getFormula();

        // Replace commas with semicolons before tokenizing
        String sanitizedFormulaStr = formulaStr.substring(1).replace(",", ";");

        // Tokenize the formula
        List<Token> tokens = tokenizer.tokenize(sanitizedFormulaStr);

        // Parse the tokens to check for syntax errors
        if (!parser.parse(tokens)) {
            throw new Exception("Syntax error in formula: " + formulaStr);
        }

        // Convert the infix tokens to postfix notation using the Shunting Yard algorithm
        List<Token> postfixTokens = ShuntingYard.infixToPostfix(tokens);

        // Evaluate the postfix expression
        double result = postfixEvaluator.evaluate(postfixTokens, this);

        return result;
    }

    public Cell getCell(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 1;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                for (int col = 0; col < values.length; col++) {
                    String contentStr = values[col];
                    if (!contentStr.isEmpty()) {
                        String column = Coordinate.toColumnLabel(col + 1);
                        String coordinateStr = column + row;
                        Content content = parseContent(contentStr.replace(",", ";"));
                        setCell(coordinateStr, content);
                    }
                }
                row++;
            }
        } catch (Exception e) {
            throw new IOException("Error loading file: " + e.getMessage(), e);
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            int maxRow = cells.keySet().stream().mapToInt(Coordinate::getRow).max().orElse(1);
            int maxCol = cells.keySet().stream().mapToInt(c -> Coordinate.columnToIndex(c.getColumn())).max().orElse(1);

            for (int row = 1; row <= maxRow; row++) {
                StringBuilder line = new StringBuilder();
                for (int col = 1; col <= maxCol; col++) {
                    String column = Coordinate.toColumnLabel(col);
                    String coordinateStr = column + row;
                    Cell cell = cells.get(parseCoordinate(coordinateStr));
                    if (cell != null) {
                        line.append(cell.getContent().toString().replace(";", ",")).append(";");
                    } else {
                        line.append(";");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }

    private void processFormulas() throws Exception {
        for (Cell cell : cells.values()) {
            if (cell.getContent() instanceof Formula) {
                cell.setValue(evaluateFormula((Formula) cell.getContent()));
            }
        }
    }

    private Coordinate parseCoordinate(String coordinateStr) {
        return new Coordinate(coordinateStr);
    }

    public Content parseContent(String contentStr) {
        if (contentStr.startsWith("=")) {
            return new Formula(contentStr);
        } else {
            try {
                return new Numerical(Double.parseDouble(contentStr));
            } catch (NumberFormatException e) {
                return new Text(contentStr);
            }
        }
    }
}
