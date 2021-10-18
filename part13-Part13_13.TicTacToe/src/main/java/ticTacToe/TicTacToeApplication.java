package ticTacToe;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[][] array = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };

        boolean isGameOver = false;

        // Make the general layout that houses entire UI of the game
        BorderPane layout = new BorderPane();

        // Turn indicator
        Label turnLabel = new Label("Turn: X");
        turnLabel.setFont(Font.font("Monospaced", 40));

        // Make the 3x3 field for the tic-tac-toe
        GridPane field = new GridPane();

        // Initialize Game logic
        GameLogic TTT = new GameLogic(array);

        // Initialize the buttons for the 3x3 playing field
        // Top
        Button button00 = new Button("");
        button00.setFont(Font.font("Monospaced", 40));
        button00.setMinSize(100,100); //  <- Set size of button
        Button button01 = new Button("");
        button01.setFont(Font.font("Monospaced", 40));
        button01.setMinSize(100,100);
        Button button02 = new Button("");
        button02.setFont(Font.font("Monospaced", 40));
        button02.setMinSize(100,100);

        // Mid
        Button button10 = new Button("");
        button10.setFont(Font.font("Monospaced", 40));
        button10.setMinSize(100,100);
        Button button11 = new Button("");
        button11.setFont(Font.font("Monospaced", 40));
        button11.setMinSize(100,100);
        Button button12 = new Button("");
        button12.setFont(Font.font("Monospaced", 40));
        button12.setMinSize(100,100);

        // Bottom
        Button button20 = new Button("");
        button20.setFont(Font.font("Monospaced", 40));
        button20.setMinSize(100,100);
        Button button21 = new Button("");
        button21.setFont(Font.font("Monospaced", 40));
        button21.setMinSize(100,100);
        Button button22 = new Button("");
        button22.setFont(Font.font("Monospaced", 40));
        button22.setMinSize(100,100);

        // Add the buttons to the playing field
        //Top
        field.add(button00, 0, 0);
        field.add(button01, 0, 1);
        field.add(button02, 0, 2);
        //Mid
        field.add(button10, 1, 0);
        field.add(button11, 1, 1);
        field.add(button12, 1, 2);
        //Bottom
        field.add(button20, 2, 0);
        field.add(button21, 2, 1);
        field.add(button22, 2, 2);

        layout.setTop(turnLabel);
        layout.setCenter(field);

        ArrayList<Integer> buttonClicked = new ArrayList<>();

        boolean isButtonClicked = false;

        ArrayList <Button> buttonList = new ArrayList<>();
        // Top buttons
        buttonList.add(button00);
        buttonList.add(button01);
        buttonList.add(button02);
        // Middle buttons
        buttonList.add(button10);
        buttonList.add(button11);
        buttonList.add(button12);
        // Bot buttons
        buttonList.add(button20);
        buttonList.add(button21);
        buttonList.add(button22);

        // Add behaviors for the buttons
        // Top
        button00.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(0);
            buttonClicked.add(0);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button01.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(0);
            buttonClicked.add(1);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button02.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(0);
            buttonClicked.add(2);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });

        // Mid
        button10.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(1);
            buttonClicked.add(0);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button11.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(1);
            buttonClicked.add(1);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button12.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(1);
            buttonClicked.add(2);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });

        // Bottom
        button20.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(2);
            buttonClicked.add(0);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button21.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(2);
            buttonClicked.add(1);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });
        button22.setOnMouseClicked((event) -> {
            buttonClicked.clear();
            buttonClicked.add(2);
            buttonClicked.add(2);

            boolean gameState = gameplayLoop(TTT, buttonClicked, turnLabel);
            updateField(TTT.getFieldArray(), buttonList);


        });




//        // Repeating gameplay loop
//        while (true) {
//            // Primitive way of playing tic-tac-toe
//            // terminal input determines turn  action
//
//            // How to transition to GUI?
//
//            // terminal input -> change to button press
////            System.out.println("ROW:");
////            int row = Integer.parseInt(scanner.nextLine());
////            System.out.println("COLUMN:");
////            int column = Integer.parseInt(scanner.nextLine());
//
//
//
//
//        }

        Scene view = new Scene(layout);

        stage.setScene(view);
        stage.setTitle("Tic-tac-toe");
        stage.show();
    }

    // method to make turn
    // return true if game over
    public boolean gameplayLoop(GameLogic TTT, ArrayList<Integer> buttonClicked, Label turnIndicator) {

        // By default, Game over state is set to false
        boolean isGameOver = false;

        // check button press if action is valid
        boolean isValidTurn = TTT.makeTurn(buttonClicked.get(0), buttonClicked.get(1));

        // Check player turn
        turnIndicator.setText("Turn: " + TTT.getPlayerSymbol());

        // remove error message -done
        // continue asking for button press - done
        if(!isValidTurn) {
//                System.out.println(TTT.getField());
            return false;
        }

        // Check all win conditions and set game state.
        TTT.setGameState();
        // Check game state -- Game Over or continue.
        isGameOver = TTT.checkGameState();

        if (isGameOver) {
            System.out.println("Diagonal win is" + TTT.DWin());
            System.out.println("Vertical win is" + TTT.VWin());
            System.out.println("Horizontal win is" + TTT.HWin());
            System.out.println("GAME OVER!");
            turnIndicator.setText("The end!");
            return true;
        }

        if (TTT.fieldIsFilled()) {
            System.out.println("Field already filled!");
            return true;
        }

        return false;
    }




    public static void updateField(String[][] field, ArrayList<Button> buttonList) {
        int x = 0; // button number (1 = top left button, 2 = bottom right button)
        // Iterate thru field, update corresponding button
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {

                buttonList.get(x).setText(field[row][column]);
                x++;
            }
        }

    }

    public void gameOver(Label sign) {

    }


    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

}
