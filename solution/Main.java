package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;

import uk.ac.aber.cs21120.tests.Examples;

public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        for (int i = 0; i < 400; i++) {
            IGrid g = Examples.getExample(i);
            Solver sudokuSolver = new Solver(g);
            double start = System.nanoTime();
            sudokuSolver.solve();
            double timeTaken = System.nanoTime() - start;
            System.out.println("Puzzle " + i + " has " + Examples.getGapCount(i) + " gaps. It took " + (timeTaken / 1000000) + " ms to solve.");

            /*Below will generate a valid sudoku puzzle to solve. Make sure there is an empty puzzle in 'Examples'
              and set the above for loop to that puzzle.*/
            /*sudokuSolver.solveM2();
            System.out.println(sudokuSolver.toString());
            sudokuSolver.removeValuesAtRandom(20);
            System.out.println(sudokuSolver.toString());*/
        }

        double totalTimeTaken = System.nanoTime() - startTime;
        System.out.println("Total time taken was: " + totalTimeTaken / 1000000000 + "seconds");
    }
}
