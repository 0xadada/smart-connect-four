/*
 * HumanPlayer.java
 *
 * Created on December 5, 2003, 11:58 PM
 */

/**
 *
 * @author  Chen
 */
import java.io.*;

public class HumanPlayer implements Player{
    private int m = -1;
    
    public void setMove(int col) {
    	m = col;
    }
    
    /** Creates a new instance of HumanPlayer */
    public HumanPlayer() {
    }
    
    public int getType() {
    	return 0;
    }
    
    public void go(SimpleBoard b) {
           int n = 0;
           InputStreamReader isr = new InputStreamReader( System.in );
           BufferedReader br = new BufferedReader( isr );
           String s = null;
           if (m==-2) {
           try {
              s = br.readLine();
           }
           catch ( IOException ioe ) {
            // won't happen too often from the keyboard
           }
           
             n = Integer.parseInt(s);
           }
         
           if ((m != -1) && (m != -2)) {
           	//System.out.println(m);
           	b.Move(m);
           } else {
           	b.Move(n);
           	//System.out.println(n);
           }
    }
    
}
