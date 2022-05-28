package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;

import uk.ac.aber.cs21120.interfaces.ISolver;

import java.util.ArrayList;

import java.util.Random;

/**
 * Solver Class for solving sudoku puzzles
 *
 * @author Benjamin
 * @version 1 (18th November 2020)
 */
public class Solver implements ISolver {

    private IGrid grid;

    private ArrayList<Integer> attemptedValues = new ArrayList<>();

    /**
     * Constructor for Solver, takes a grid as a parameter (the given sudoku puzzle)
     *
     * @param g grid to be solved
     */
    public Solver(IGrid g) {
        this.grid = g;
    }

    /**
     * Solve() attempts to solve the puzzle using backtracking
     *
     * @return boolean value depending if puzzle is solved or not.
     * More specifically returns false if we have ran out of values to try for
     * given cell. At that point we backtrack to previous.
     */
    @Override
    public boolean solve() {

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid.get(y, x) == 0) {
                    for (int z = 1; z < 10; z++) {
                        grid.set(y, x, z);
                        if (grid.isValid()) {
                            if (solve()) {
                                return true;
                            }
                        }
                    }
                    grid.set(y, x, 0);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Outputs our grid in a readable format
     *
     * @return the grid as a String
     */
    public String toString() {

        StringBuilder b = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                b.append(grid.get(x, y));
            }
            b.append('\n');
        }
        return b.toString();
    }

    /**
     * generateRandom() will fill our instance variable ArrayList attemptedValues with random values 1-9. These values
     * will not repeat.
     */
    public void generateRandom() {
        while (attemptedValues.size() < 10) {
            Random rand = new Random();
            int randomNum = rand.nextInt((9 - 1) + 1) + 1;
            if (!attemptedValues.contains(randomNum)) {
                attemptedValues.add(randomNum);
            }
            if (attemptedValues.size() == 9) {
                break;
            }
        }
    }

    /**
     * SolveM2() attempts to solve the puzzle using backtracking and uses random values to enter into cells from 1-9
     * instead of starting at 1 and ending at 9.
     *
     * @return boolean value depending if puzzle is solved or not.
     * More specifically returns false if we have ran out of values to try for
     * given cell. At that point we backtrack to previous.
     */
    public boolean solveM2() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid.get(y, x) == 0) {
                    generateRandom();
                    for (int i = 0; i < 9; i++) {
                        int randomValue = attemptedValues.get(i);
                        grid.set(y, x, randomValue);
                        if (grid.isValid()) {
                            if (solve()) {
                                return true;
                            }
                        }
                    }
                    grid.set(y, x, 0);
                    attemptedValues.clear();
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * removeValuesAtRandom() will create gaps in a puzzle at random depending on the parameter
     *
     * @param gaps how many gaps you wish to have in your puzzle
     */
    public void removeValuesAtRandom(int gaps) {
        int count = 0;
        while (count < gaps) {
            Random rand = new Random();
            int randomNum1 = rand.nextInt((8 - 1) + 1) + 1;
            int randomNum2 = rand.nextInt((8 - 1) + 1) + 1;
            if (grid.get(randomNum1, randomNum2) != 0) {
                grid.set(randomNum1, randomNum2, 0);
                count++;
            }
        }
    }
}





