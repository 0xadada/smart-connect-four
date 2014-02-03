/*
 * RandomPlayer.java
 *
 * Created on December 6, 2003, 12:18 AM
 */

/**
 *
 * @author  Chen
 */
import java.util.*;
public class RandomPlayer implements Player{
    
    /** Creates a new instance of RandomPlayer */
    public RandomPlayer() {
        System.out.println("RandomPlayer initialized.");
    }
    
	public void setMove(int col) {
		
	}
   
	public int getType() {
			return 1;
		}
    public void go(SimpleBoard b) {
        int m= (int) (Math.random()*7);
        
        while (b.cols[m]==6) m=(int) (Math.random()*7);
        
        //System.out.println(m);        
        b.Move(m);
    }
    
}
