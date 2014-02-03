/*
 * SimpleBoard.java
 *
 * Created on December 5, 2003, 6:26 PM
 */

/**
 *
 * @author  Chen
 */
import java.util.*;
public class SimpleBoard{
    public int[][] loc;
    public  int next_player;
    public int[] cols;
    public int m_x=0;
    public int m_y=0;
    public int winner = 0;
    public boolean out = true;
    public String movelist;
    
    /** Creates a new instance of SimpleBoard */
    public SimpleBoard(){
       next_player = 1;
       loc = new int[6][7];
       cols = new int[7];
       winner=0;
       clear();
       movelist="";
       out = true;
    }
    
    public void ParseMove(String move_list){
        for (int i=0;i<move_list.length();i++) {
           int tm = Integer.parseInt((new Character(move_list.charAt(i))).toString());
           Move(tm);
        }
        
    }
    public void Move(int pos) {
        if ( (pos<0) || (pos >6))
            System.out.println("invalid input\n\n");
        else{
            if ((cols[pos]==6) && out)
                System.out.println("Column full");
            else{
            m_y=pos;
            movelist+=(new Integer(pos)).toString();
            m_x= 5-cols[pos];
            cols[pos]++; 
            loc[m_x][m_y] = next_player;
            next_player = 3-next_player;
            }
        }
        
    }
    
    public int[][] view() {
        return loc;
    }
    public int[] viewcol(){
        return cols;
    }
    public void clear() {
        for (int i = 0; i< 6;i++)
            for (int j = 0; j<7; j++)
            {
                loc[i][j]=0;
            }
        for (int j = 0 ; j < 7; j++) cols[j] = 0;        
    }
    
    public int next() {return next_player;}
    public int[] ret_col(){return cols;}
    public boolean over() {
        String line_x="";
        String line_y="";
        String line_ld=(new Integer(loc[m_x][m_y])).toString();
        String line_rd=(new Integer(loc[m_x][m_y])).toString();
        String s = (new Integer(3-next_player)).toString();
        String sub = s+s+s+s;
        String match ="[012]*"+sub+"[012]*";
        for (int i=0; i<7; i++){
            int cell = loc[m_x][i];
            line_x+= (new Integer(cell)).toString();
        }
        for (int i=0; i<6; i++){
            int cell = loc[i][m_y];
            line_y+= (new Integer(cell)).toString();
        }
        
        int tempx=m_x;
        int tempy=m_y;
        while ( (tempx>0) && (tempy>0)){
            tempx--;tempy--;
            line_ld = (new Integer(loc[tempx][tempy])).toString() + line_ld;
        }
        
        tempx=m_x;tempy=m_y;
        while ( (tempx <5)&& (tempy <6)) {
            tempx++;tempy++;
            line_ld = line_ld+(new Integer(loc[tempx][tempy])).toString();
        }
        
        tempx=m_x;tempy=m_y;
        while ( (tempx>0) && (tempy<6)){
            tempx--;tempy++;
            line_rd = (new Integer(loc[tempx][tempy])).toString() + line_rd;
        }
        
        tempx=m_x;tempy=m_y;
        while ( (tempx <5)&& (tempy >0)) {
            tempx++;tempy--;
            line_rd = line_rd+(new Integer(loc[tempx][tempy])).toString();
        }
        
        /*System.out.println(line_x);
        System.out.println(line_y);
        System.out.println(line_ld);
        System.out.println(line_rd);
        System.out.println(sub);*/
        
        if  ( (line_x.matches(match)) ||
            (line_y.matches(match)) ||
            (line_ld.matches(match)) ||
            (line_rd.matches(match)) )
            {
              winner = 3 - next_player;
              /*if (out){
              System.out.print("\nPlayer ");
              System.out.print(new Integer(winner));
              System.out.println(" won!");}*/

              return true;
            } 
        
        
        int z=0;
        for (int i=0; i<6; i++)
            for (int j=0; j<7; j++)
              if (loc[i][j] == 0)  z = 1;
            
        if (z == 0)
        {
            /*if (out)
                System.out.println("Draw!");*/
            return true;
        }
            
        return false;
    }
    
    
    
    public String toString(){
        String ret = "   0 1 2 3 4 5 6\n";
        for (int i = 0; i < 6; i++){
            ret += (new Integer(i)).toString()+ ": ";
            for (int j = 0; j< 7; j++){
                ret+=(new Integer(loc[i][j])).toString();
                ret+=" ";
            }
            ret+="\n";
        }
        return ret;
    }
}
