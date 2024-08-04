package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula extends Content {
    private String formula;
    private Set<Coordinate> dependencies;

    public Formula(String formula) {
        this.formula = formula;
        this.dependencies = parseDependencies(formula);
    }

    public String getFormula() {
        return formula;
    }

    public Set<Coordinate> getDependencies() {
        return dependencies;
    }

    @Override
    public Double getValue() {
        throw new UnsupportedOperationException("Formula value should be evaluated through the Spreadsheet.");
    }

    @Override
    public String toString() {
        return formula;
    }

    private Set<Coordinate> parseDependencies(String formula) {
        Set<Coordinate> dependencies = new HashSet<>();
        Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            dependencies.add(new Coordinate(matcher.group()));
        }
        return dependencies;
    }
}
