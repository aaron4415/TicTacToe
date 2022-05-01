//Cheung King Laam
//55812110

package hw1.s55812110;


import java.text.DecimalFormat;
import java.util.Random;



/**
 *
 * @author kingl
 */

public class Main {
     public static int start = 0;
      
             public static int total_esc = 1000000;
              
 
     
    /**
     * @param args the command line arguments
     */
static boolean escapeResult (MousePos test , Maze previouMove){ //return true if mouse escape successful, false if mouse fail
    boolean check = true;
    boolean isMaze = true;
    
    char goDirection = 0 ;
    char d = 0 ;
    int r= 0;
    previouMove.usedMaze(0, 0);
    boolean previousL,previousR,previousF;
    previousL = true;
    previousR = true;
    previousF = true;
    
           
    while (check ){ //2 condition,1: mouse can not go previous position,2: mouse's position should inside Maze
        
            do {    
                if(previousL==false&&previousF==false&&previousR==false) // unsuccessful: cannot fulfill condition 1
        {
            return false;
        }
          
        
        
        d=0;
                while (d==0) { //loop if no direction 
                    Random three_directions= new Random();
        
        int num = three_directions.nextInt(100);
             
  if(num< 30&& previousL==true){
    d = 'L';//go left
    previousL=false;
}else if (num< 70&&num >29&& previousF==true)  {
    d = 'F';//go forward
     previousF=false;
}else if(num<100&&num>69&&previousR==true){
    d = 'R';
 previousR=false;//go right
}   
                    
                }
 
switch(r%4){ //4 different pattern of direction 
    case 0 ->       { //case 0,base case ,if trun right ,r =r + 1 ,case 1 ,if r =3,it will become pattern 0 .if turn left, r= r-1, if r= 0,it will become pattern 3
        switch(d){ 
            case'F' -> goDirection = 'E';
            case'L' -> goDirection = 'N';
            case'R' -> goDirection = 'S';
        }               }

    case 1 ->       { 
        switch(d){
            case'F' -> goDirection = 'S';
            case'L' -> goDirection = 'E';
            case'R' -> goDirection = 'W';
        }               }

    case 2 ->       {
        switch(d){
            case'F' -> goDirection = 'W';
            case'L' -> goDirection = 'S';
            case'R' -> goDirection = 'N';
        }               }

    case 3 ->       {
        switch(d){
            case'F' -> goDirection = 'N';
            case'L' -> goDirection = 'W';
            case'R' -> goDirection = 'E';
        }               }

        
}
    

      
      switch(goDirection)
      {
          case 'N':
              test.movN();
              if (test.pos1 > 6 || test.pos2 > 6|| test.pos1< 0 || test.pos2 <0){//check if mouse fulfill condition 2
                  test.deMovN();
                  isMaze = false; 
              }else if(previouMove.check(test.pos1, test.pos2)==true){ //Check is it fulfill condition 1
                                   test.deMovN();
                                   isMaze = false;
                               }
              else{
                  isMaze = true;
              }
             break;
             case 'E':
                 test.movE();
                 if (test.pos1 > 6 || test.pos2 > 6|| test.pos1< 0 || test.pos2 <0){//check if mouse fulfill condition 2
                  test.deMovE();
                   isMaze = false;
                  }
                 else if(previouMove.check(test.pos1, test.pos2)==true){ //Check is it fulfill condition 1
                                   test.deMovE();
                                   isMaze = false;
                               }
              else{
                  isMaze = true;
              }
           break;
           case 'S':
                 test.movS();
                 if (test.pos1 > 6 || test.pos2 > 6|| test.pos1< 0 || test.pos2 <0){//check if mouse fulfill condition 2
                  test.deMovS();
                   isMaze = false;
                  }
                 else if(previouMove.check(test.pos1, test.pos2)==true){ //Check is it fulfill condition 1
                                  test.deMovS(); 
                                   isMaze = false;
                               }
              else{
                  isMaze = true;
              }
           break;
           case 'W':
                 test.movW();
                 if (test.pos1 > 6 || test.pos2 > 6|| test.pos1< 0 || test.pos2 <0){//check if mouse fulfill condition 2
                  test.deMovW();
                   isMaze = false;
                  }
                 else if(previouMove.check(test.pos1, test.pos2)==true){ //Check is it fulfill condition 1
                                   test.deMovW();
                                   isMaze = false;
                               }
              else{
                  isMaze = true;
              }
           break;
      }
      if(test.pos1==6 && test.pos2==6){ // successful
          return true;
      }
            }while (!isMaze);
            
            
            previouMove.usedMaze(test.pos1, test.pos2);// mark position
            
            previousL = true;
      previousR = true;
       previousF = true;
     
        if (d =='R'){
            r++;
        }
        if (d =='L'){
            
            
            if(r%4==0){
                 r=3;
            }
            
            else {
                r--;
         
            }
        }
        
        
        }
         return false;
}


    public static void main(String[] args) {
        
        int x,y,numOfsuccessful,numOfFail;
       double success_rate,total_escape;
       total_escape = total_esc;
       x = start;
       y = start;
       boolean isDeadEnd;
       
       numOfsuccessful = start;
       numOfFail = start;
        MousePos begin = new MousePos(x,y);
        Maze maze = new Maze();
        
         
        
        for (int i = 0; i <  total_escape; i++) {
            isDeadEnd = escapeResult(begin,maze);
            
            begin.cleanPos();
           maze.clean();
             
            if (isDeadEnd){
                numOfsuccessful++;
            }else{
                numOfFail++;
            }
        }
         success_rate = numOfsuccessful/total_escape; 
        DecimalFormat df = new DecimalFormat("0.000");
         

        System.out.println("The Monte Carlo simulation result of one millionruns:" );
        System.out.println("No. of successful escape: "+ numOfsuccessful);
        System.out.println("Success Rate P: "+ df.format(success_rate));
    }
    
}

 class MousePos{   // class about the position of mouse 
    public int pos1,pos2;
    
     MousePos(int x,int y){
        pos1 = x;
        pos2 = y;
        
    }
     void cleanPos (){ // reset the position of mouse 
         
          pos1 = 0;
          pos2 = 0;
     }
     
     int movN (){ // go direction N
        return pos2--;
         
     }
     int deMovN (){ // back direction N
        return pos2++;
         
     }
     int movE (){ // go direction E
        return pos1++;
         
     }
     int deMovE (){ // back direction E
        return pos1--;
         
     }int movS (){ // go direction S
        return pos2++;
         
     }
     int deMovS (){ // back direction S
        return pos2--;
         
     }int movW (){ // go direction W
        return pos1--;
         
     }
     int deMovW (){ // back direction W
        return pos1++;
         
     }
     
}
class Maze{  // class about the position of Maze
    private int [][]m;
    public static int maxMov = 7;
    Maze (){  
     m =new int[maxMov][maxMov];
     
    }
    void usedMaze(int x,int y){ //save the data about mouse go to a new position of Maze
        m[x][y] = 1;
    }
    boolean check (int x,int y){ // check if mouse go to this position before 
         if(m[x][y]==1){
             return  true;
         }else{
             return false;
         }
    }
    void clean(){ // reset the position of Maze
        for (int i = 0; i < maxMov; i++) {
            for (int j = 0; j < maxMov; j++) {
                m[i][j] = 0 ;
            }
        }
    }
     }
