package pl.shipproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void shouldAddSubmarine() throws Exception {
        //arrange
        Board board = new Board();
        //act
        boolean result = board.addShip(0, 0, new Submarine());
        //assert
        assertTrue(result);
    }

    @Test
    public void shouldFailToAddOutside() throws  Exception {
        //arrange
        Board board = new Board();
        //act
        boolean result = board.addShip(-1, 0, new Submarine());
        //assert
        assertFalse(result);
    }

    @Test
    public void shouldFailToAddOutsideY() throws  Exception {
        //arrange
        Board board = new Board();
        //act
        boolean result = board.addShip(0, 11, new Submarine());
        //assert
        assertFalse(result);
    }
}