import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        build_maze();
    }



    public static void build_maze() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("maze.dat"));
        String[] size = scanner.nextLine().split(" ");
        char[][] maze = new char[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
        
        for(int i = 0; i<maze.length;i++)
        {
            maze[i] = scanner.nextLine().toCharArray();
        }
        
        int[] location = find_beginning(maze,0,0);

        maze = maze(maze,location[0],location[1]);
        
        System.out.println("Found the solution");
        for(char[] arr:maze)
        {
            System.out.println(String.valueOf(arr));
        }
    
    }



    public static int[] find_beginning(char[][] maze, int row, int col)
    {   
        
        if(maze[row][col]=='+')
        {
            int[] result = {row,col};
            return result;
        }
        else
        {
            if(row+1<maze.length && col == 0)
            {
                return find_beginning(maze, row+1, col);
            }
            else if(col+1<maze[row].length && row == maze.length-1)
            {
                return find_beginning(maze, row, col+1);
            }
            else if(row-1>=0 && col == maze[row-1].length-1)
            {
                return find_beginning(maze, row-1, col);
            }
            else
            {
                return find_beginning(maze,row,col-1);
            }
        }
        
    }


    
    private static char[][] maze(char[][] maze, int row, int col)
    {
        
        if(maze[row][col]=='-')
            {
                return maze;
            }
        maze[row][col]='+';
        if(col+1<maze[row].length && (maze[row][col+1]==' ' || maze[row][col+1]=='-')) //East
        {
            return maze(maze,row,col+1);
        }
        else if(row+1<maze.length && (maze[row+1][col]==' ' || maze[row+1][col]=='-')) //South
        {
            return maze(maze,row+1,col);
        }
        else if(col-1>=0 && (maze[row][col-1]==' '||maze[row][col-1]=='-'))//West
        {
            return maze(maze,row,col-1);
        }
        else if(row-1>=0 && (maze[row-1][col]==' '||maze[row-1][col]=='-')) //North
        {
            return maze(maze,row-1,col);
        }
        else
        {
            
            maze[row][col]='.';
            if(col+1<maze[row].length && maze[row][col+1]=='+') //East
            {
                return maze(maze,row,col+1);
            }
            else if(row+1<maze.length && maze[row+1][col]=='+') //South
            {
                return maze(maze,row+1,col);
            }
            else if(col-1>=0 && maze[row][col-1]=='+') //West
            {
                return maze(maze,row,col-1);
            }
            else //North
            {
                return maze(maze,row-1,col);
            }
        }        
    }
}