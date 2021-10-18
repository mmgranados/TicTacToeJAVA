package ticTacToe;
import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {
    private String[][] field;
    private Player playerTurn;
    private String symbol;
    private ArrayList<int[]> winningCells;  //  Saves winning cells - first 3 cell combinations with same non-empty symbols
    public boolean isGameOver = false;

    public GameLogic () {
        for (int yIdx = 0; yIdx < 3; yIdx++) {
            for (int xIdx = 0; yIdx < 3; xIdx++) {
                this.field[yIdx][xIdx] = " ";
            }
        }
        this.winningCells = new ArrayList<>();
        playerTurn = Player.X;
    }

    public GameLogic (String[][] array) {
        this.field = array;
        this.winningCells = new ArrayList<>();
        playerTurn = Player.X;
        this.symbol = playerTurn.toString();
    }


    public void setGameOver() {
        this.isGameOver = true;
    }


    public boolean makeTurn(int row, int column) {

        if (field[row][column] != " " || isGameOver) {
            return false;
        }

        field[row][column] = this.symbol;
        this.changeTurn();
        return true;

    }

    /**
     *
      * @return symbol(O/X):String - returns symbol to be used by Player of upcoming turn
     */
    public void changeTurn() {
        if (this.playerTurn == Player.X) {
            this.playerTurn = Player.O;
            symbol =  playerTurn.toString();
            return;
        }

        // otherwise
        this.playerTurn = Player.X;
        symbol = playerTurn.toString();
    }


    public String getField() {
        String currentField =
                this.field[0][0] + this.field[0][1] + this.field[0][2] + "\n" +
                this.field[1][0] + this.field[1][1] + this.field[1][2] + "\n" +
                this.field[2][0] + this.field[2][1] + this.field[2][2] + "\n" ;

        return currentField;
    }

    public String[][] getFieldArray() {

        return this.field;
    }

    public String getPlayerSymbol() {
        return this.symbol;
    }

    public boolean checkGameState() {
        return this.isGameOver;
    }

    public void setGameState() {
        this.isGameOver = (this.DWin() || this.VWin() || this.HWin());
    }

    // Win conditions
    // Horizontal
    // vertical
    // Diagonal
    public boolean HWin() {
        // Horizontal Win
        for (int row = 0; row < 3; row++) {
            // skip if any of the cells are empty
            if (field[row][0].equals(" ") || field[row][1].equals(" ") || field[row][2].equals(" ")) {
                continue;
            }
            if (field[row][0].equals(field[row][1])
                && field[row][1].equals(field[row][2])) {
                return true;
            }
        }

        return false;
    }

    public boolean VWin() {
        // Vertical Win

        for (int column = 0; column < 3; column++) {

            // skip if one of the cells are empty
            if (field[0][column].equals(" ") || field[1][column].equals(" ") || field[2][column].equals(" ")) {
                continue;
            }

            if (field[0][column].equals(field[1][column])
                    && field[1][column].equals(field[2][column])) {
                return true;
            }
        }

        return false;
    }

    public boolean DWin() {
        // Diagonal Win
        // bottom left to top right (/) diagonal
        if (field[0][0].equals(field[1][1])
                && field[1][1].equals(field[2][2]) ) {
            // skip if one of the cells is an empty string
            if (!field[0][0].equals(" ") && !field[1][1].equals(" ") && !field[2][2].equals(" ")) {
                int[] winningCell1 = {0,0};
                int[] winningCell2 = {1,1};
                int[] winningCell3 = {2,2};

                this.winningCells.add(winningCell1);
                this.winningCells.add(winningCell2);
                this.winningCells.add(winningCell3);

                return true;

            }

        }

        // top left to bottom right (\) diagonal
        if (field[2][0].equals(field[1][1])
                && field[1][1].equals(field[0][2]) ) {

            // skip if one of the cells is an empty string
            if (!field[2][0].equals(" ") && !field[1][1].equals(" ") && !field[0][2].equals(" ")) {

                int[] winningCell1 = {2,0};
                int[] winningCell2 = {1,1};
                int[] winningCell3 = {0,2};

                this.winningCells.add(winningCell1);
                this.winningCells.add(winningCell2);
                this.winningCells.add(winningCell3);

                return true;
            }

        }

        return false;
    }

    public boolean fieldIsFilled() {
        return (
                !field[0][0].equals(" ") && !field[0][1].equals(" ") && !field[0][2].equals(" ") &&
                        !field[1][0].equals(" ") && !field[1][1].equals(" ") && !field[1][2].equals(" ") &&
                        !field[2][0].equals(" ") && !field[2][1].equals(" ") && !field[2][2].equals(" ")
        );
    }
}
