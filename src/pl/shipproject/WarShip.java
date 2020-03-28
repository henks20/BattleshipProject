package pl.shipproject;

public abstract class WarShip implements Ship {

    enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private Orientation orientation;
    private int hits;
    private Field[] occupied;

    public WarShip() {
        occupied = new Field[getDesksCount()];
    }

    @Override
    public boolean isSunk() {
        return hits == getDesksCount();
    }

    public void setOnField(Field field, int deckNo){
        field.setShip(this);
        field.setState(State.SHIP);
        occupied[deckNo] = field;
    }

    @Override
    public void hit() {
        hits++;
        if(isSunk()){
            for (int i = 0; i < occupied.length; i++) {
                occupied[i].setState(State.SUNK);
            }
        }
    }
}

class Submarine extends WarShip {

    @Override
    public int getDesksCount() {
        return 1;
    }
}

class Destoyer extends WarShip {

    @Override
    public int getDesksCount() {
        return 2;
    }
}

class Cruiser extends  WarShip {

    @Override
    public int getDesksCount() {
        return 3;
    }
}

class BattleShip extends WarShip {

    @Override
    public int getDesksCount() {
        return 4;
    }
}
