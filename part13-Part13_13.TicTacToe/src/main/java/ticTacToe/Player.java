package ticTacToe;

public enum Player {
    X("X"), O("O");

    private String name;

    private Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
