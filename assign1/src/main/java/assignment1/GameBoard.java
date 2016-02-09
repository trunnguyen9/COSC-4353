package assignment1;

public class GameBoard {    
    final static boolean ALIVE = true;
    final static boolean DEAD = false;   
    
    public static boolean isAlive(boolean isAlive, int neighborCount)
    {
        return neighborCount == 3 || (isAlive == true && neighborCount == 2);
    }
    
    public static int numberOfLiveNeighbors (boolean[][] cells, int rowIndex, int columnIndex)
    {
        int count = 0;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
            {               
                if (isInBounds(cells.length, rowIndex + i, columnIndex + j) && cells[rowIndex + i][columnIndex + j] == ALIVE)
                {
                    count++;
                }
            }

        if (cells[rowIndex][columnIndex])
            return count - 1;

        return count;
    }
    
    public static boolean isInBounds (int boardSize, int rowIndex, int columnIndex)
    {
        return rowIndex >= 0 && rowIndex < boardSize && columnIndex >= 0 && columnIndex < boardSize;
    }
    
    public static boolean[][] nextBoard(boolean[][] cells)
    {
        boolean[][] newCells = new boolean[cells.length][cells.length];
        
        for(int i = 0; i < cells.length; i++)
        {
            for(int j = 0; j < cells[0].length; j++)
            {
                newCells[i][j] = isAlive(cells[i][j], numberOfLiveNeighbors(cells, i, j));
            }
        }
        
        return newCells;
    }
}

