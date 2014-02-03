/*
 * ConnectFour.java
 *
 * Created on December 5, 2003, 5:56 PM
 */

/**
 *
 * @author  Chen Zhang
 */
import java.io.*;
public class ConnectFour {
   private static Player  p1;
   private static Player  p2;
    
    /** Creates a new instance of ConnectFour */
    public ConnectFour() {
        p1 = new HumanPlayer();
        p2 = new HumanPlayer();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Connect Four");
        System.out.println("Please choose from the following options:");
        System.out.println("    1. Human vs Human");
        System.out.println("    2. Human vs Computer (Human goes first)");
        System.out.println("    3. Computer vs Human (Computer goes first");
        System.out.println("    4. Computer vs Computer");
        System.out.print("Choice[4]:");
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader br = new BufferedReader( isr );
        String s = null;
        try {
           s = br.readLine();
        }
        catch ( IOException ioe ) {
        }
        int n = Integer.parseInt(s);
        
        if (n == 1) {
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
        }
        
        else if ((n == 2) || (n == 3)) {
            System.out.println("Please choose the level of the computer:");
            System.out.println("    1. RandomPlayer");
            System.out.println("    2. DefensivePlayer");
            System.out.println("    3. AggressivePlayer");
            System.out.println("    4. MinMaxPlayer");
            System.out.print("Choice[1]:");
            isr = new InputStreamReader( System.in );
            br = new BufferedReader( isr );
            s = null;
            try {
                s = br.readLine();
            }
            catch ( IOException ioe ) {
            }
            int nn = Integer.parseInt(s);
            
            Player temp= null;            
            
            if (nn==1) temp= new RandomPlayer();
            if (nn==2) temp= new DefensivePlayer();
            if (nn==3) temp= new AggressivePlayer();
            if (nn==4) temp= new MinMaxPlayer();
            
            if (n==2) {p1 = new HumanPlayer();p2 = temp;}
            if (n==3) {p1 = temp;p2 = new HumanPlayer();}                
        }
        else {
            System.out.println("Please choose the level of the computer 1:");
            System.out.println("    1. RandomPlayer");
            System.out.println("    2. DefensivePlayer");
            System.out.println("    3. AggressivePlayer");
            System.out.println("    4. MinMaxPlayer");
            System.out.print("Choice[1]:");
            isr = new InputStreamReader( System.in );
            br = new BufferedReader( isr );
            s = null;
            try {
                s = br.readLine();
            }
            catch ( IOException ioe ) {
            }
           
            int nn = Integer.parseInt(s);
            
            if (nn==1) p1 = new RandomPlayer();
            if (nn==2) p1 = new DefensivePlayer();
            if (nn==3) p1 = new AggressivePlayer();
            if (nn==4) p1 = new MinMaxPlayer();
            
            System.out.println("Please choose the level of the computer 2:");
            System.out.println("    1. RandomPlayer");
            System.out.println("    2. DefensivePlayer");
            System.out.println("    3. AggressivePlayer");
            System.out.println("    4. MinMaxPlayer");
            System.out.print("Choice[1]:");
            isr = new InputStreamReader( System.in );
            br = new BufferedReader( isr );
            s = null;
            try {
                s = br.readLine();
            }
            catch ( IOException ioe ) {
            }
            nn = Integer.parseInt(s);
            if (nn==1) p2 = new RandomPlayer();
            if (nn==2) p2 = new DefensivePlayer();
            if (nn==3) p2 = new AggressivePlayer();
            if (nn==4) p2 = new MinMaxPlayer();
            
        }
        
        SimpleBoard BoardA = new SimpleBoard();
        
        p1.setMove(-2);
        p2.setMove(-2);
        
        System.out.println();
        while (!BoardA.over() ){
           
           System.out.println(BoardA);
           System.out.print("Player "+BoardA.next());
           System.out.print(" next move:");
           
           if (BoardA.next() == 1) p1.go(BoardA);
           else p2.go(BoardA);
                       
        }
        System.out.println(BoardA);
    }
    
}

