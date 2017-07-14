/*
 * MazeSolver.java
	Archit Kumar
	Reads Maze from Txt file.
 */


public class MazeSolver {
    
    
    
    public static String findPath(Maze maze) {
        /* First we getb the entry and exit point of the maze and assign it to the mazelocation entry and exit respectively.
		   Make a new mazelocation and give it a location one forward of entry point.
		   Make a new boolean array and give it a size of as number of columns and rows.
		   Initialize two stacks for storing and copying. */
        MazeLocation entry = maze.getEntry();
        MazeLocation exit = maze.getExit();
        MazeLocation curr = new MazeLocation(entry.getRow()+1,entry.getCol());
        boolean[][] searched = new boolean[maze.getNumRows()+1][maze.getNumCols()+1];
        MazeLocation done = curr;
        StackRefBased<MazeLocation> func = new StackRefBased<MazeLocation>();
        StackRefBased<MazeLocation> copy = new StackRefBased<MazeLocation>();
		
        func.push(new MazeLocation(entry.getRow(),entry.getCol()));
        searched[entry.getRow()][entry.getCol()] = true;
        func.push(new MazeLocation(entry.getRow()+1,entry.getCol()));
        searched[entry.getRow()+1][entry.getCol()] = true;
        /*Iterate using curr in the maze.*/
		while(!(curr.equals(exit)))
        {
            
            if((curr.equals(maze.getEntry()))){
                try{
                    func.pop();      // Condition check if the there is no solution.
                }
                catch(StackEmptyException ex)
                {
                    System.out.print("");
                }
                try{
                    func.pop();
                }
                catch(StackEmptyException ex)
                {
                    System.out.print("");
                }
                break;
            }
            else
            {
                curr = MarkWall(searched,curr,maze);  // Check the wall function if the four locations are wall are not.
                
                curr = checksafe(curr,searched,func,maze);  // Advance in the maze when the maze is checked.
                
                try{
                    
                    curr = func.peek();     //Give current the last value stack has entered.
                }
                catch(StackEmptyException ex)
                {
                    System.out.print("");
                }
                
            }
            
        }
        while(!(func.isEmpty()))
        {
			/*Function for copying the stack into another one.*/
            try
            {
                
                done = func.peek();
                copy.push(new MazeLocation(done.getRow(),done.getCol()));
                
            }
            catch(StackEmptyException ex)
            {
                System.out.print("");
            }
            try{
                func.pop();
            }
            catch(StackEmptyException ex)
            {
                System.out.print("");
            }
        }
        
        
        
        return copy.toString();
    }
    
    public static MazeLocation MarkWall(boolean[][] searched,MazeLocation curr,Maze maze)
    {
        
        /*Checking the if the above,down,left and right of the current position.
		  Mark the searched accordingly if true or false.*/
        if((maze.isWall(curr.getRow()-1,curr.getCol())))
        {
            
            if(maze.getNumRows()==curr.getRow())
            {
                searched[curr.getRow()][curr.getCol()] = true;
            }
            else
            {
                searched[curr.getRow()-1][curr.getCol()] = true;
            }
        }
        if((maze.isWall(curr.getRow()+1,curr.getCol())))
        {
            
            
            if(maze.getNumRows()==curr.getRow())
            {
                searched[curr.getRow()][curr.getCol()] = true;
            }
            else
            {
                
                searched[curr.getRow()+1][curr.getCol()] = true;
            }
        }
        if((maze.isWall(curr.getRow(),curr.getCol()+1)))
        {
            
            if(maze.getNumCols()==curr.getCol())
            {
                searched[curr.getRow()][curr.getCol()] = true;
            }
            else
            {
                searched[curr.getRow()][curr.getCol()+1]=true;
            }
        }
        if((maze.isWall(curr.getRow(),curr.getCol()-1)))
        
        {
            
            if(maze.getNumCols()==curr.getCol())
            {
                searched[curr.getRow()][curr.getCol()] = true;
            }
            else
            {
                searched[curr.getRow()][curr.getCol()-1] = true;
            }
        }
        
        return curr;
    }
    
    public static MazeLocation checksafe(MazeLocation curr,boolean[][] searched,StackRefBased<MazeLocation> func,Maze maze)
    {
        /*After checking the wall check if the point is checked or not.
		  If it is checked go to default if if not check where point is not checked and move accordingly.*/
        if(!(searched[curr.getRow()-1][curr.getCol()]))
        {
            
            func.push(new MazeLocation(curr.getRow()-1,curr.getCol()));
            searched[curr.getRow()][curr.getCol()] = true;
            curr = new MazeLocation(curr.getRow()-1,curr.getCol());
        }
        else if(!(searched[curr.getRow()+1][curr.getCol()]))
        {
            
            func.push(new MazeLocation(curr.getRow()+1,curr.getCol()));
            
            searched[curr.getRow()][curr.getCol()] = true;
            curr = new MazeLocation(curr.getRow()+1,curr.getCol());
            
        }
        else if(!(searched[curr.getRow()][curr.getCol()+1]))
        {
            
            func.push(new MazeLocation(curr.getRow(),curr.getCol()+1));
            searched[curr.getRow()][curr.getCol()] = true;
            curr = new MazeLocation(curr.getRow(),curr.getCol()+1);
        }
        else if(!(searched[curr.getRow()][curr.getCol()-1]))
        {
            
            func.push(new MazeLocation(curr.getRow(),curr.getCol()-1));
            searched[curr.getRow()][curr.getCol()] = true;
            curr = new MazeLocation(curr.getRow(),curr.getCol()-1);
        }
        
        else {
            searched[curr.getRow()][curr.getCol()] = true;
            try{
                
                func.pop();
                
            }
            catch(StackEmptyException ex)
            {
                System.out.print("");
            }
            try{
                
                curr = func.peek();
            }
            catch(StackEmptyException ex)
            {
                System.out.print("");
            }
            
        }
        return curr;
    }
}