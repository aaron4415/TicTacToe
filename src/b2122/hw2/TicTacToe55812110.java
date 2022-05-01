/*
Student Name:Cheung King Laam
Student ID:55812110
 */
package b2122.hw2;



/**
 *
 * @author cheungkinglaam
 */
public class TicTacToe55812110 implements TicTacToe{

private int count; //for counting the turn of the game
private int size;  //store the size of grid
private String[][] grid; //the grid for TicTacToe

    @Override
    public void init(int size) throws IllegalArgumentException {
        
        if (size<3){
            throw new IllegalArgumentException("size is less than 3");//throws IllegalArgumentException ,if the input size is less than 3
        }
        this.size=size;//store size 
       grid=new String[size][size];//determine the size of the grid
       int pos=1;
        for (int i =0 ;i<size;i++){
            for (int j=0;j<size;j++){
                grid[i][j]=String.valueOf(pos);
                pos++;//determine the position of the number of the grid
            }
        }
    }

    @Override
    public boolean hasNext() {
        if(hasWinner())
            return false;//if it has winner ,no next turn
        if(count==size*size){
            return false;//if the grid is full,no next turn
        }else{
            return true;
        }
             
        
         
          
           
    }

    @Override
    public Player getTurn() {
        if(count%2==0){
            return Player.X;//X player go first
        }else{
            return Player.O;
        }
    }

    @Override
    public void mark(int pos) throws IllegalArgumentException {
        if(pos>size*size||pos<1)
        throw new IllegalArgumentException("input position cannot be marked");//IllegalArgumentException, if the input position cannot be marked
        
        for (int i =0 ;i<size;i++){
            for (int j=0;j<size;j++){
                if(grid[i][j].equals(String.valueOf(pos))){
                    if(getTurn()==Player.X){
                        grid[i][j]="X";//marked X
                        count++;//the turn of game +1
                        break;
                    }
                    else{
                        grid[i][j]="O";//marked O
                        count++;//the turn of game +1
                        break;
                    }
                }
                    
                }
            
            }
        
        }
    

    @Override
    public void print() {
       for (int i =0 ;i<size;i++){
           int counter = size-1;
            for (int j=0;j<size;j++){
                System.out.printf(grid[i][j]);//print the grid
                if(counter>0){//for print |
                    System.out.printf("|");
                }
                counter--;
                }
            System.out.println("");
            }
        }
    

    @Override
    public boolean hasWinner() {
       if(getWinner()==Player.O||getWinner()==Player.X){
           return true;//has winner 
       }else{
           return false;//no winner 
       }
    }

    @Override
    public Player getWinner() throws IllegalStateException {
       for (int i =0 ;i<size;i++){
           int countX=0;
           int countO=0;
            for (int j=0;j<size;j++){
                if(grid[i][j]=="O")
                    countO++;
                if(grid[i][j]=="X")
                    countX++;
            }
           if (countO==size) 
               return Player.O;//O player win for horizontal
           if(countX==size) 
               return Player.X;//X player win for horizontal
        }
      for (int i =0 ;i<size;i++){
          int countX=0;
           int countO=0;
           for (int j=0;j<size;j++){
                if(grid[j][i]=="O")
                    countO++;
                if(grid[j][i]=="X")
                    countX++;
            }
           if (countO==size) 
               return Player.O;//O player win for vertical
           if(countX==size) 
               return Player.X;//X player win for vertical
        }
      int diagonal1X=0;
           int diagonal1O=0;
      for (int i =0 ;i<size;i++){
          
           
                if(grid[i][i]=="O")
                    diagonal1O++;
                if(grid[i][i]=="X")
                    diagonal1X++;
                
            
      }
      if (diagonal1O==size) 
               return Player.O;//O player win for diagonal row
           if(diagonal1X==size) 
               return Player.X;//X player win for diagonal row
           int diagonal2X=0;
           int diagonal2O=0;
            for (int i =0 ;i<size;i++){
          
           
                if(grid[i][size-i-1]=="O")
                    diagonal2O++;
                if(grid[i][size-i-1]=="X")
                    diagonal2X++;
                
            
      }
      if (diagonal2O==size) 
               return Player.O;//O player win for diagonal row
           if(diagonal2X==size) 
               return Player.X;//X player win for diagonal row
          
     return null;
      } 
       
    

    /**
     * @param args the command line arguments
     */
    
    
}
