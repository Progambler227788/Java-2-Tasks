package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.Cell;
import java.util.HashSet;
import java.util.Set;

public class CircularDependencyChecker {

    private Set<Coordinate> visited = new HashSet<>();
    private Set<Coordinate> stack = new HashSet<>();

    public void checkForCircularDependencies(Coordinate coordinate, Spreadsheet spreadsheet) throws CircularDependencyException {
        visited.clear();
        stack.clear();
        visit(coordinate, spreadsheet);
    }

    private void visit(Coordinate coordinate, Spreadsheet spreadsheet) throws CircularDependencyException {
        if (stack.contains(coordinate)) {
            throw new CircularDependencyException("Circular dependency detected at cell: " + coordinate);
        }

        if (!visited.contains(coordinate)) {
            stack.add(coordinate);
            Cell cell = spreadsheet.getCell(coordinate);
            if (cell != null && cell.getContent() instanceof Formula) {
                Formula formula = (Formula) cell.getContent();
                Set<Coordinate> dependencies = formula.getDependencies();
                for (Coordinate dependency : dependencies) {
                    visit(dependency, spreadsheet);
                }
            }
            stack.remove(coordinate);
            visited.add(coordinate);
        }
    }
}
