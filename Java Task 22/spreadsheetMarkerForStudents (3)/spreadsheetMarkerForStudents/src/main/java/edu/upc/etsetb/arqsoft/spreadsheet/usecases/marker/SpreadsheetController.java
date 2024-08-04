package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpreadsheetController implements ISpreadsheetControllerForChecker {

    private final Map<String, String> cellContents;
    private final Map<String, Double> cellValues;
    private final Map<String, Set<String>> dependencyGraph;

    public SpreadsheetController() {
        this.cellContents = new HashMap<>();
        this.cellValues = new HashMap<>();
        this.dependencyGraph = new HashMap<>();
    }

    @Override
    public void setCellContent(String cell, String content) throws CircularDependencyException {
        if (content.startsWith("=")) {
            checkCircularDependency(cell, content, new HashSet<>());
            updateDependencyGraph(cell, content);
        } else {
            dependencyGraph.remove(cell);
        }
        cellContents.put(cell, content);
        updateCellValues();
    }

    @Override
    public String getCellContent(String cell) {
        return cellContents.getOrDefault(cell, "");
    }

    @Override
    public double getCellContentAsDouble(String cell) {
        return cellValues.getOrDefault(cell, Double.NaN);
    }

    @Override
    public String getCellContentAsString(String cell) {
        return cellContents.getOrDefault(cell, "");
    }

    @Override
    public String getCellFormulaExpression(String cell) {
        String content = cellContents.get(cell);
        if (content != null && content.startsWith("=")) {
            return content.substring(1); // Remove the '=' sign
        }
        return "";
    }

    @Override
    public void readSpreadSheetFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String cell = parts[0];
                    String content = parts[1];
                    cellContents.put(cell, content);
                }
            }
            updateCellValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveSpreadSheetToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : cellContents.entrySet()) {
                String cell = entry.getKey();
                String content = entry.getValue();
                writer.write(cell + "\t" + content);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkCircularDependency(String cell, String content, Set<String> visitedCells) throws CircularDependencyException {
        if (visitedCells.contains(cell)) {
            throw new CircularDependencyException("Circular dependency detected at cell: " + cell);
        }

        visitedCells.add(cell);

        String[] referencedCells = extractReferencedCells(content);
        for (String refCell : referencedCells) {
            if (cellContents.containsKey(refCell)) {
                checkCircularDependency(refCell, cellContents.get(refCell), new HashSet<>(visitedCells));
            }
        }
    }

    private String[] extractReferencedCells(String content) {
        // Use regex to extract cell references like A1, B2, etc.
        Set<String> referencedCells = new HashSet<>();
        Matcher matcher = Pattern.compile("[A-Z]+[0-9]+").matcher(content);
        while (matcher.find()) {
            referencedCells.add(matcher.group());
        }
        return referencedCells.toArray(new String[0]);
    }

    private void updateDependencyGraph(String cell, String content) {
        if (content.startsWith("=")) {
            String[] referencedCells = extractReferencedCells(content);
            dependencyGraph.put(cell, new HashSet<>(Arrays.asList(referencedCells)));
        } else {
            dependencyGraph.remove(cell);
        }
    }

    private void updateCellValues() {
        Set<String> evaluatedCells = new HashSet<>();
        for (String cell : cellContents.keySet()) {
            updateCellValue(cell, evaluatedCells);
        }
    }
// this is the formula part 
    private void updateCellValue(String cell, Set<String> evaluatedCells) {
        if (evaluatedCells.contains(cell)) {
            return;
        }

        String content = cellContents.get(cell);
        if (content != null && content.startsWith("=")) {
            String formula = content.substring(1);
            for (String refCell : extractReferencedCells(content)) {
                if (cellContents.containsKey(refCell) && !evaluatedCells.contains(refCell)) {
                    updateCellValue(refCell, evaluatedCells);
                }
                formula = formula.replace(refCell, cellValues.getOrDefault(refCell, Double.NaN).toString());
            }

            try {
                double value = evaluateFormula(formula);
                cellValues.put(cell, value);
            } catch (Exception e) {
                cellValues.put(cell, Double.NaN);
            }
        } else {
            try {
                double value = Double.parseDouble(content);
                cellValues.put(cell, value);
            } catch (NumberFormatException e) {
                cellValues.put(cell, Double.NaN);
            }
        }

        evaluatedCells.add(cell);
    }

    private double evaluateFormula(String formula) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

        String jsFormula = convertToJavaScriptFormula(formula);

        Object result = engine.eval(jsFormula);

        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        } else {
            throw new ScriptException("Formula evaluation did not result in a number.");
        }
    }

    private String convertToJavaScriptFormula(String formula) {
        // Replace SUMA, MIN, MAX, PROMEDIO with JavaScript functions
        formula = formula.replaceAll("SUMA", "sum");
        formula = formula.replaceAll("MIN", "Math.min");
        formula = formula.replaceAll("MAX", "Math.max");
        formula = formula.replaceAll("PROMEDIO", "average");

        // Define the sum and average functions for JavaScript
        String jsFunctions = "function sum() { var args = Array.prototype.slice.call(arguments); return args.reduce(function(a, b) { return a + b; }, 0); } " +
                             "function average() { var args = Array.prototype.slice.call(arguments); return sum.apply(null, args) / args.length; } ";

        return jsFunctions + formula;
    }
}
