/*
Student Name:Cheung King Laam
Student ID:55812110
 */
package b2122.hw3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.io.File;
import java.net.URL;
import java.util.InputMismatchException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



/**
 *
 * @author cheungkinglaam
 */
public class TicTacToe55812110 extends JFrame implements TicTacToe {

private int count; //for counting the turn of the game
private int size;  //store the size of grid
private String[][] grid; //the grid for TicTacToe
private  JPanel w2 = new JPanel();
private  JPanel w5 = new JPanel();
private JButton[] gridButtons;
JLabel outputJLabel = new JLabel("");
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
    public TicTacToe55812110() {
        // Set BorderLayout with horizontal gap 50 and vertical gap 20
        setLayout(new BorderLayout(50, 20));
JPanel w1 = new JPanel();

JPanel w3 = new JPanel();
JPanel w4 = new JPanel();
w1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
w1.add(new JLabel("Enter the size to start:    "));
JTextField sizeInputJTextField = new JTextField(10);
 w1.add(sizeInputJTextField);       

        sizeInputJTextField.addActionListener(new ActionListener() {//size inputed than show the size of the button of grid
            @Override
            public void actionPerformed(ActionEvent e) {
                
                init(Integer.valueOf(sizeInputJTextField.getText()));
                     outputJLabel.setText("A "+size+"x"+size+" game is started. Player X first.");
                    int index = 0;
               w2.setLayout(new GridLayout(size, size, 30, 10));
               gridButtons = new JButton[size*size];
              for (int i =0 ;i<size;i++){
            for (int j=0;j<size;j++){
                gridButtons[index]=new JButton(grid[i][j]);//using button array the store the button 
                
                gridButtons[index].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(getTurn()==Player.X){
                     gridButtons[Integer.valueOf(e.getActionCommand())-1].setText("X");//when we click th button , the button disvisble and show X on button
                     outputJLabel.setText("Player O's turn.");
                    }
                    else{
                        gridButtons[Integer.valueOf(e.getActionCommand())-1].setText("O");//when we click th button , the button disvisble and show O on button
                        outputJLabel.setText("Player X's turn.");
                    }
                mark(Integer.valueOf(e.getActionCommand()));// put the psiton of mark on the grid
                
                 gridButtons[Integer.valueOf(e.getActionCommand())-1].setEnabled(false);
                 
            repaint(); //show the grid
            if(!hasNext()){
            if(hasWinner())
            outputJLabel.setText("The winner is " + getWinner() + " !!!");//if there are winner ,show this 
        else
                outputJLabel.setText("DRAW game!");//if draw , show this 
           
            setEnabled(false);// if finish one game , the button will become disable the click
        }
                
            }
        });
                w2.add(gridButtons[index]);
               index++;
            }
        }
              
               
            add(w2, BorderLayout.CENTER);    
            revalidate();
            repaint();
            
            }
        });
        add(w2, BorderLayout.CENTER);
w5.setLayout(new BorderLayout(10, 30));
w5.setBackground(Color.red);
w5.add(new JLabel("OutPut:"),BorderLayout.NORTH);
w5.setBorder(new LineBorder(Color.black, 1));
w5.add(outputJLabel,BorderLayout.CENTER);

JPanel w7 = new JPanel();
w7.setLayout(new BorderLayout(30, 20)); //the output window setting
w7.add(w5,BorderLayout.CENTER);
JPanel w33 = new JPanel();
JPanel w44 = new JPanel();
JPanel w55 = new JPanel();
w7.add(w33, BorderLayout.EAST);
w7.add(w44, BorderLayout.WEST);
w7.add(w55, BorderLayout.SOUTH);
        // Add buttons to the frame
        add(w3, BorderLayout.EAST);
        add(w7, BorderLayout.SOUTH);
        add(w4, BorderLayout.WEST);
        add(w1, BorderLayout.NORTH);
        
        
    }
       
    public static void main(String[] args) {
         TicTacToe55812110 frame = new TicTacToe55812110();
         frame.setTitle("TicTacToe");
        frame.setSize(350, 400); // Set the frame size
        frame.setLocationRelativeTo(null); // New since JDK 1.4
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Display the frame
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    
    
}
