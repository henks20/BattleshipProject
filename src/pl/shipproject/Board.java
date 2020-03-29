package pl.shipproject;

public class Board {

    public static final int BOARD_SIZE = 10;
    public static final int SHIP_TYPES_COUNT = 4;

    private Field[][] fields = new Field[BOARD_SIZE][BOARD_SIZE];

    private int shipsCount;
    private int numberOfShipsByDeck[] = new int [SHIP_TYPES_COUNT];


    public Board() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                fields[y][x] = new Field(x, y, State.EMPTY);
            }
        }
    }

    public void fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                fields[i][j].setState(getRandomShip(Math.random()));
            }
        }
    }

    private State getRandomShip(double random) {
        if (random < 0.2){
            return State.HIT;
        } else if(random > 0.8) {
            return State.EMPTY;
        } else {
            return State.MISS;
        }
    }

    private static void printLetters() {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE ; i++) {
            System.out.print((char) ('A' + i));
        }
        System.out.print('\n');
    }

    public void printBoard() {
        printLetters();
        for (int i = 0; i < 10 ; i++) {
            int numberToPrint = i + 1;
            if(numberToPrint < 10) {
                System.out.print(' ');
            }
            System.out.print(numberToPrint);
            for (int j = 0; j < 10; j++) {
                char shipValue = fields[i][j].stateToChar();
                System.out.print(shipValue);
            }
            System.out.print('\n');
        }
    }

    public void addShip(int x, int y, Ship ship) throws IllegalMoveException {
        int count = ship.getDecksCount();
        if(numberOfShipsByDeck[count - 1]
                == getTotalCountOfShips(count)) {
            throw new IllegalMoveException("You have all submarines set!");
        }

        Field[] field = new Field[count];
        int xToSet = x, yToSet = y;
        for (int i = 0; i < count; i++) {
            if(ship.getOrientation() == WarShip.Orientation.HORIZONTAL){
                xToSet = x + i;
            } else {
                yToSet = y + i;
            }
            if(isOutside(xToSet, yToSet)) {
                throw new IllegalMoveException("Ship set outside board!");
            }

            field[i] = fields[yToSet][xToSet];
            if(isFieldOccupied(field[i])){
                throw new IllegalMoveException("Field is occupied");
            }
        }

        for (int i = 0; i < count; i++) {
            ship.setOnField(field[i], i);
        }

        shipsCount++;
        numberOfShipsByDeck[count - 1]++;
    }

    private boolean isFieldOccupied(Field field) {
        for (int y = field.getY() - 1; y <= field.getY() + 1; y++) {
            for (int x = field.getX() - 1; x <= field.getX() + 1; x++) {
                if(isOutside(x, y)){
                    continue;
                }
                if(fields[y][x].getState() != State.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOutside(int x, int y) {
        return x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE;
    }

    private int getTotalCountOfShips(int decksCount) {
        return SHIP_TYPES_COUNT - decksCount + 1;
    }

    public int getShipsCount() {
        return shipsCount;
    }

    public Field getField(int x, int y) {
        return fields[y][x];
    }
}
