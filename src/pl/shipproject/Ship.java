package pl.shipproject;

public interface Ship {

    int getDesksCount();

    void hit();

    boolean isSunk();

    void setOnField(Field field, int deckNo);
}
