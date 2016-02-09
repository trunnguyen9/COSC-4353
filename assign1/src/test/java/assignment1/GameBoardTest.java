package assignment1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {     
    final boolean ALIVE = true;
    final boolean DEAD = false;    
       
    @Test
    public void testLiveCellDiesWithZeroLiveNeighbors() {
        int numberOfLiveNeighbors = 0;
        assertEquals(DEAD, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));        
    }
    
    @Test
    public void testLiveCellDiesWithOneLiveNeighbors() {         
        int numberOfLiveNeighbors = 1;
        assertEquals(DEAD, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));        
    }
    
    @Test
    public void testLiveCellsStillAliveWith2LiveNeighbors()
    {
        int numberOfLiveNeighbors = 2;
        assertEquals(ALIVE, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));   
    }
    
    @Test
    public void testLiveCellsStillAliveWith3LiveNeighbors()
    {
        int numberOfLiveNeighbors = 3;
        assertEquals(ALIVE, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testLiveCellsDiesWith4LiveNeighbors()
    {
        int numberOfLiveNeighbors = 4;
        assertEquals(DEAD, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testLiveCellsDiesWith8LiveNeighbors()
    {
        int numberOfLiveNeighbors = 8;
        assertEquals(DEAD, GameBoard.isAlive(ALIVE, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsStillDeadWithZeroLiveNeighbors()
    {
        int numberOfLiveNeighbors = 0;
        assertEquals(DEAD, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsStillDeadWith1LiveNeighbors()
    {
        int numberOfLiveNeighbors = 1;
        assertEquals(DEAD, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsStillDeadWith2LiveNeighbors()
    {
        int numberOfLiveNeighbors = 2;
        assertEquals(DEAD, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsBecomeAliveWith3LiveNeighbors()
    {
        int numberOfLiveNeighbors = 3;
        assertEquals(ALIVE, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsStillDeadWith4LiveNeighbors()
    {
        int numberOfLiveNeighbors = 4;
        assertEquals(DEAD, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }
    
    @Test
    public void testDeadCellsStillDeadWith8LiveNeighbors()
    {
        int numberOfLiveNeighbors = 8;
        assertEquals(DEAD, GameBoard.isAlive(DEAD, numberOfLiveNeighbors));  
    }    
    
    @Test
    public void testNumberOfLiveNeighborsIsZero() {
        boolean[][] cells = {
          {DEAD,DEAD, DEAD,DEAD,DEAD}, 
          {DEAD,DEAD,DEAD,DEAD,DEAD}, 
          {DEAD,DEAD,DEAD,DEAD,DEAD}, 
          {DEAD,DEAD,DEAD,DEAD,DEAD}, 
          {DEAD,DEAD,DEAD,DEAD,DEAD}}; 
          
        int x = 0;
        int y = 0;
        assertEquals(0, GameBoard.numberOfLiveNeighbors(cells, x, y)); 
    }
        
    @Test
    public void testNumberOfLiveNeighborsIsOne() {
        boolean[][] cells = {
          {DEAD, ALIVE, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
          
        int x = 0;
        int y = 0;
        assertEquals(1, GameBoard.numberOfLiveNeighbors(cells, x, y));           
    }       
    
    @Test
    public void testNumberOfLiveNeighborsIsTwo() {  
        boolean[][] cells = {
          {DEAD, ALIVE, DEAD,DEAD,DEAD}, 
          {ALIVE, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}, 
          {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
          
        int x = 0;
        int y = 0;
        assertEquals(2, GameBoard.numberOfLiveNeighbors(cells, x, y));       
    }
    
    @Test 
    public void testTheTestedCellAtTheTopLeftCornerAliveWithNoAliveNeighbors() {  
        boolean[][] cells = {
            {ALIVE, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        int x = 0;
        int y = 0;
        assertEquals(0, GameBoard.numberOfLiveNeighbors(cells, x, y));       
    }
    
    @Test 
    public void testTheTestedCellAtTheBottomLeftCornerAliveWithOneAliveNeighbors() {  
        boolean[][] cells = {
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {ALIVE, ALIVE, DEAD, DEAD, DEAD}}; 
        
        int x = 4;
        int y = 0;
        assertEquals(1, GameBoard.numberOfLiveNeighbors(cells, x, y));       
    }
  
    @Test 
    public void testTheTestedCellAtTheTopRightCornerAliveWithOneAliveNeighbors() {  
        boolean[][] cells = {
            {DEAD, DEAD, DEAD, DEAD, ALIVE}, 
            {DEAD, DEAD, DEAD, ALIVE, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, ALIVE}}; 
        
        int x = 0;
        int y = 4;
        assertEquals(1, GameBoard.numberOfLiveNeighbors(cells, x, y));       
    }
    
    @Test 
    public void testTheTestedCellAtTheBottomRightCornerAliveWithTwoAliveNeighbors() {  
        boolean[][] cells = {
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, ALIVE}, 
            {DEAD, DEAD, DEAD, ALIVE, ALIVE}};  
        
        int x = 4;
        int y = 4;
        assertEquals(2, GameBoard.numberOfLiveNeighbors(cells, x, y));       
    }
    
    @Test
    public void testGridWithOneLiveCellDiesInNextGeneration()
    {
        boolean[][] cells = {
            {DEAD, ALIVE, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        boolean[][] expectedCells = {
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        assertArrayEquals(expectedCells, GameBoard.nextBoard(cells));
    }
    
    @Test
    public void testGridWithBlockPattern()
    {
        boolean[][] cells = {
            {DEAD, ALIVE, ALIVE, DEAD, DEAD}, 
            {DEAD, ALIVE, ALIVE, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        assertArrayEquals(cells, GameBoard.nextBoard(cells));
    }
    
    boolean[][] horizontalBlinker = { 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, ALIVE, ALIVE, ALIVE, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
    boolean[][] veritcalBlinker = {
            {DEAD, DEAD, ALIVE, DEAD, DEAD}, 
            {DEAD, DEAD, ALIVE, DEAD, DEAD}, 
            {DEAD, DEAD, ALIVE, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}, 
            {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
    
    @Test
    public void testHorizontalBlinkerBecomesVerticalBlinker() 
    {
        assertArrayEquals(veritcalBlinker, GameBoard.nextBoard(horizontalBlinker));
    }  
    
    @Test
    public void testVerticalBlinkerBecomesHorizontalBlinker() 
    {        
        assertArrayEquals(horizontalBlinker, GameBoard.nextBoard(veritcalBlinker));
    }  
}
