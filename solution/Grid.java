package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;

import java.util.HashSet;

/**
 * Grid Class for retrieving and updating sudoku values, and validating puzzles
 *
 * @author Benjamin
 * @version 1 (18th November 2020)
 */
public class Grid implements IGrid {

    private int[][] sudokuGrid;

    private static int MAX_GRID_SIZE = 9;
    private static int MAX_COORDINATE_CALL = 8;
    private static int MIN_COORDINATE_CALL = 0;
    private static int MAX_VALUE = 9;
    private static int MIN_VALUE = 0;

    private HashSet<Integer>[] rows = new HashSet[MAX_VALUE];
    private HashSet<Integer>[] columns = new HashSet[MAX_VALUE];
    private HashSet<Integer>[] grid3x3 = new HashSet[MAX_VALUE];

    /**
     * Builds a sudoku grid using constant MAX_GRID_SIZE as dimensions
     */
    public Grid() {
        this.sudokuGrid = new int[MAX_GRID_SIZE][MAX_GRID_SIZE];
    }

    /**
     * Takes parameters as coordinates and returns coordinates value
     * Throws exception if coordinates out of range
     *
     * @param x column number
     * @param y row number
     * @return int value of given coordinates
     * @throws BadCellException
     */
    @Override
    public int get(int x, int y) throws IGrid.BadCellException {

        if (x > MAX_COORDINATE_CALL || x < MIN_COORDINATE_CALL || y > MAX_COORDINATE_CALL || y < MIN_COORDINATE_CALL) {
            throw new IGrid.BadCellException(x, y);
        }
        return sudokuGrid[x][y];
    }

    /**
     * Takes parameters as coordinates and an int value to set a coordinate with given value
     * Throws exceptions if coordinates out of range or value is out of range
     *
     * @param x   column number
     * @param y   row number
     * @param val digit from 1-9, or 0 for an empty cell
     * @throws BadCellException
     * @throws BadDigitException
     */
    @Override
    public void set(int x, int y, int val) throws IGrid.BadCellException, IGrid.BadDigitException {

        if (x > MAX_COORDINATE_CALL || x < MIN_COORDINATE_CALL || y > MAX_COORDINATE_CALL || y < MIN_COORDINATE_CALL) {
            throw new IGrid.BadCellException(x, y);
        }
        if (val > MAX_VALUE || val < MIN_VALUE) {
            throw new IGrid.BadDigitException(val);
        }
        sudokuGrid[x][y] = val;
    }

    /**
     * Scans through sudokuGrid and ensures rules of sudoku are not broken
     *
     * @return boolean value depending if the puzzle is valid or not
     */
    @Override
    public boolean isValid() {

        // create our hashSets for all the rows, columns and 3x3 grids.
        for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
            grid3x3[i] = new HashSet<>();
        }

        for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
            for (int j = MIN_VALUE; j < MAX_VALUE; j++) {
                int num = sudokuGrid[i][j];
                if (num != MIN_VALUE) {

                    /* grid3x3Index will use i and j to find the coordinates appropriate 3x3 grid. The top left grid is 0 and
                     we iterate down the columns, so the one below is 1, then 2, then we return to the top of the next
                     column of grids which is 3 etc.*/
                    int grid3x3Index = (i / 3) * 3 + j / 3;

                    /*.add() returns a boolean value, so if we cannot add a value because a it already exists in the
                     hashSet then we return false*/
                    if (!(rows[i].add(num)) || (!columns[j].add(num)) || (!grid3x3[grid3x3Index].add(num))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
