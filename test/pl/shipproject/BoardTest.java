package pl.shipproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test (expected = IllegalMoveException.class)
    public void shouldAddSubmarineOnField() throws Exception {
        //arrange
        board.addShip(0, 0, new Submarine());
        board.addShip(3, 3, new Submarine());
        board.addShip(5, 5, new Submarine());
        board.addShip(7, 7, new Submarine());
        //act
       board.addShip(9, 9, new Submarine());

    }

    @Test
    public void shouldAddDestroyerOnFields() throws Exception {
        //arrange
        //act
        board.addShip(0, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        //assert
        Field field = board.getField(1, 0);
        assertEquals(State.SHIP, field.getState());
    }

    @Test (expected = IllegalMoveException.class)
    public void shouldNotBeAbleToGetOutside() throws Exception {
        //arrange
        //act
        board.addShip(9, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
    }

    @Test (expected = IllegalMoveException.class)
    public void shouldNotBeAbleToBeClose() throws Exception {
        //arrange
        board.addShip(0, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        //act
        board.addShip(2, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
    }

    @Test (expected = IllegalMoveException.class)
    public void shouldNotBeAbleToAddTwoBattleships() throws Exception {
        //arrange
        board.addShip(0, 0, new BattleShip(WarShip.Orientation.HORIZONTAL));
        //act
        board.addShip(6, 0, new BattleShip(WarShip.Orientation.HORIZONTAL));
    }

    @Test
    public void shouldNotBeAbleToAddFiveSubmarines() throws Exception {
        //arrange
        //act
        board.addShip(0, 0, new Submarine());
        //assert
    }

    @Test
    public void shouldAddSubmarine() throws Exception {
        //arrange
        //act
        board.addShip(0, 0, new Submarine());
        //assert
        assertEquals(1, board.getShipsCount());
    }

    @Test (expected = IllegalMoveException.class)
    public void shouldFailToAddOutsideX() throws  Exception {
        //arrange
        //act
        board.addShip(-1, 0, new Submarine());
    }

    @Test  (expected = IllegalMoveException.class)
    public void shouldFailToAddOutsideY() throws  Exception {
        //arrange
        //act
        board.addShip(0, 11, new Submarine());
    }
}