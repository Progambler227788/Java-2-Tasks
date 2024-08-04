package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {
    private Map<String, String> cells;

    public Spreadsheet() {
        this.cells = new HashMap<>();
    }

    public void setCellContent(String cell, String content) {
        cells.put(cell, content);
    }

    public String getCellContent(String cell) {
        return cells.get(cell);
    }

    public void save(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : cells.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            cells.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    cells.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void evaluateFormulas() {
        Map<String, String> evaluatedCells = new HashMap<>();
        for (Map.Entry<String, String> entry : cells.entrySet()) {
            evaluatedCells.put(entry.getKey(), evaluateFormula(entry.getValue(), evaluatedCells));
        }
        cells.putAll(evaluatedCells);
    }

    private String evaluateFormula(String content, Map<String, String> evaluatedCells) {
        if (content.startsWith("=")) {
            // Simplified formula evaluation logic
            String formula = content.substring(1);
            // Replace cell references with their values
            for (Map.Entry<String, String> entry : evaluatedCells.entrySet()) {
                formula = formula.replace(entry.getKey(), entry.getValue());
            }
            try {
                // Evaluate the formula (assuming simple arithmetic for the example)
                double result = evaluateArithmeticExpression(formula);
                return String.valueOf(result);
            } catch (Exception e) {
                throw new UnsupportedOperationException("Error evaluating formula: " + content);
            }
        }
        return content;
    }

    private double evaluateArithmeticExpression(String expression) {
        // Implement a simple arithmetic expression evaluator
        // This example assumes the expression is simple and correct
        // In a real scenario, consider using a library or writing a full parser
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
