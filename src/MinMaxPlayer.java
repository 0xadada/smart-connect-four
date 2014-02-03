/*
 * MinMaxPlayer.java
 *
 * Created on December 9, 2003, 11:21 PM
 */

/**
 *
 * @author  Chen
 */
import java.util.*;
public class MinMaxPlayer implements Player {
    private int[] loc;
    private State current_state;
    private int lookahead = 4;
    
    private int MinimaxDecision(State s){
        //System.out.println("MiniMax started...");
        int v = 0;
        //System.out.println(s.next_player);
        if (s.next_player == 1)
            v= MaxValue(s);
        else v = MinValue(s);
        //System.out.print("MiniMax Search Completed. H:");
        //System.out.println(v);
        
        //System.out.println("MiniMax finished.");
        
        for (int i=0;i<s.suc.size();i++)
        {
            State cell = (State) s.suc.get(i);
            if (cell.v==v) return cell.action;
        }
        //System.out.println("I don't know...");
        return (int) (Math.random()*7);
    }
    
    private int MaxValue(State s){
        //System.out.println("MaxValue get called! State: "+s.movelist);
        if (TerminalTest(s))
        {
          //  System.out.println("MaxValue: Calling Utility");
            int u = Utility(s);
           // System.out.print("MaxValue: The utility of state "+s.movelist+" is ");
           // System.out.println(s.v);
            return u;
        }
        s.v = -500;
        Vector suc=s.suc;
        for (int i =0; i< suc.size(); i++){
            State cell=(State) suc.get(i);
            int temp = MinValue(cell);
            if (temp>s.v)  {s.v=temp;}
        }
        /*
        System.out.print("MaxValue: ");
        for (int i=0;i<suc.size(); i++){
            State cell=(State) suc.get(i);
            
            System.out.print(cell.v);
            System.out.print(" ");
        }
        System.out.println();*/
        return s.v;
    }
    private int MinValue(State s){
        //System.out.println("MinValue get called! State: "+s.movelist);
        if (TerminalTest(s))
        {
          //  System.out.println("MinValue: Calling Utility");
            int u = Utility(s);
            //System.out.print("MinValue: The utility of state "+s.movelist+" is ");
            //System.out.println(s.v);
            return u;
        }
        s.v = 500;
        Vector suc = s.suc;
        for (int i = 0; i< suc.size(); i++){
            State cell=(State) suc.get(i);
            int temp = MaxValue(cell);
      
            if (temp < s.v) s.v = temp;
        }
        /*
         System.out.print("MinValue: ");
        for (int i=0;i<suc.size(); i++){
            State cell=(State) suc.get(i);
            System.out.print(cell.v);
            System.out.print(" ");
        }
        */
        //System.out.println("min of max: "+s.v );
        return s.v;
    }
    
    public boolean TerminalTest(State s){
        //System.out.println("Termininal Testing.  State:"+s.movelist);
        if ((s.depth == lookahead) || (s.over()))
            return true;
        return false;
    }
    
    public int Utility(State s){
        //System.out.println("Utility of State "+s.movelist);
        int me= current_state.next_player;
        int oppo = 3- me;
        if (s.over()){
            if (s.winner == me)
            {
                s.v=100;
                
                return s.v;
            }
            if (s.winner == oppo)
            {
                s.v=-100;
                return s.v;
            }
            if (s.winner == 0)
            {
                s.v=0;
                return 0;
            }
        }

      

        
        int h=0;
        int row = s.m_x;
        int col = s.m_y;
    
        if ((col>=3)
        && (s.loc[row][col-1] == me)
        && (s.loc[row][col-2] == me)
        && (s.loc[row][col-3] == me))
            h=h+16;
        //right
        if ((col<=3)
        && (s.loc[row][col+1] == me)
        && (s.loc[row][col+2] == me)
        && (s.loc[row][col+3] == me))
            h=h+16;
        //check y direction
        if ((row<=2)
        && (s.loc[row+1][col] == me)
        && (s.loc[row+2][col] == me)
        && (s.loc[row+3][col] == me))
            h=h+16;
        //check left diagonal
        if ((col>=3) && (row<=2)
        && (s.loc[row+1][col-1] == me)
        && (s.loc[row+2][col-2] == me)
        && (s.loc[row+3][col-3] == me))
            h=h+16;
        
        if ((col<=3) && (row<=2)
        && (s.loc[row+1][col+1] == me)
        && (s.loc[row+2][col+2] == me)
        && (s.loc[row+3][col+3] == me))
            h=h+16;
        
        if ((col>=3) && (row>=3)
        && (s.loc[row-1][col-1] == me)
        && (s.loc[row-2][col-2] == me)
        && (s.loc[row-3][col-3] == me))
            h=h+16;
        
        if ((col<=3) && (row>=3)
        && (s.loc[row-1][col+1] == me)
        && (s.loc[row-2][col+2] == me)
        && (s.loc[row-3][col+3] == me))
            h=h+16;
        
        if ((col>=2)
        && (s.loc[row][col-1] == me)
        && (s.loc[row][col-2] == me))
            h=h+4;
        //right
        if ((col<=4)
        && (s.loc[row][col+1] == me)
        && (s.loc[row][col+2] == me))
            h=h+4;
        //check y direction
        if ((row<=3)
        && (s.loc[row+1][col] == me)
        && (s.loc[row+2][col] == me))
            h=h+4;
        //check left diagonal
        if ((col>=2) && (row<=3)
        && (s.loc[row+1][col-1] == me)
        && (s.loc[row+2][col-2] == me))
            h=h+4;
        
        if ((col<=4) && (row<=3)
        && (s.loc[row+1][col+1] == me)
        && (s.loc[row+2][col+2] == me))
            h=h+4;
        
        if ((col>=2) && (row>=2)
        && (s.loc[row-1][col-1] == me)
        && (s.loc[row-2][col-2] == me))
            h=h+4;
        
        if ((col<=4) && (row>=2)
        && (s.loc[row-1][col+1] == me)
        && (s.loc[row-2][col+2] == me))
            h=h+4;
        
        if ((col>=1)
        && (s.loc[row][col-1] == me))
            h=h+2;
        //right
        
        if ((col<=5)
        && (s.loc[row][col+1] == me))
            h=h+2;
        //check y direction
        if ((row<=4)
        && (s.loc[row+1][col] == me))
            h=h+2;
        //check left diagonal
        if ((col>=1) && (row<=4)
        && (s.loc[row+1][col-1] == me))
            h=h+2;
        
        if ((col<=5) && (row<=4)
        && (s.loc[row+1][col+1] == me))
            h=h+2;
        
        if ((col>=1) && (row>=1)
        && (s.loc[row-1][col-1] == me))
            h=h+2;
        
        if ((col<=5) && (row>=1)
        && (s.loc[row-1][col+1] == me))
            h=h+2;
        
        //check x direction.
        //left
        if ((col>=3)
        && (s.loc[row][col-1] == oppo)
        && (s.loc[row][col-2] == oppo)
        && (s.loc[row][col-3] == oppo))
            h=h+8;
        //right
        if ((col<=3)
        && (s.loc[row][col+1] == oppo)
        && (s.loc[row][col+2] == oppo)
        && (s.loc[row][col+3] == oppo))
            h=h+8;
        //check y direction
        if ((row<=2)
        && (s.loc[row+1][col] == oppo)
        && (s.loc[row+2][col] == oppo)
        && (s.loc[row+3][col] == oppo))
            h=h+8;
        //check left diagonal
        if ((col>=3) && (row<=2)
        && (s.loc[row+1][col-1] == oppo)
        && (s.loc[row+2][col-2] == oppo)
        && (s.loc[row+3][col-3] == oppo))
            h=h+8;
        
        if ((col<=3) && (row<=2)
        && (s.loc[row+1][col+1] == oppo)
        && (s.loc[row+2][col+2] == oppo)
        && (s.loc[row+3][col+3] == oppo))
            h=h+8;
        
        if ((col>=3) && (row>=3)
        && (s.loc[row-1][col-1] == oppo)
        && (s.loc[row-2][col-2] == oppo)
        && (s.loc[row-3][col-3] == oppo))
            h=h+8;
        
        if ((col<=3) && (row>=3)
        && (s.loc[row-1][col+1] == oppo)
        && (s.loc[row-2][col+2] == oppo)
        && (s.loc[row-3][col+3] == oppo))
            h=h+8;
        
        if ((col>=2)
        && (s.loc[row][col-1] == oppo)
        && (s.loc[row][col-2] == oppo))
            h=h+4;
        //right
        if ((col<=4)
        && (s.loc[row][col+1] == oppo)
        && (s.loc[row][col+2] == oppo))
            h=h+4;
        //check y direction
        if ((row<=3)
        && (s.loc[row+1][col] == oppo)
        && (s.loc[row+2][col] == oppo))
            h=h+4;
        //check left diagonal
        if ((col>=2) && (row<=3)
        && (s.loc[row+1][col-1] == oppo)
        && (s.loc[row+2][col-2] == oppo))
            h=h+4;
        
        if ((col<=4) && (row<=3)
        && (s.loc[row+1][col+1] == oppo)
        && (s.loc[row+2][col+2] == oppo))
            h=h+4;
        
        if ((col>=2) && (row>=2)
        && (s.loc[row-1][col-1] == oppo)
        && (s.loc[row-2][col-2] == oppo))
            h=h+4;
        
        if ((col<=4) && (row>=2)
        && (s.loc[row-1][col+1] == oppo)
        && (s.loc[row-2][col+2] == oppo))
            h=h+4;
        
        if ((col>=1)
        && (s.loc[row][col-1] == oppo))
            h=h+2;
        //right
        
        if ((col<=5)
        && (s.loc[row][col+1] == oppo))
            h=h+2;
        //check y direction
        if ((row<=4)
        && (s.loc[row+1][col] == oppo))
            h=h+2;
        //check left diagonal
        if ((col>=1) && (row<=4)
        && (s.loc[row+1][col-1] == oppo))
            h=h+2;
        
        if ((col<=5) && (row<=4)
        && (s.loc[row+1][col+1] == oppo))
            h=h+2;
        
        if ((col>=1) && (row>=1)
        && (s.loc[row-1][col-1] == oppo))
            h=h+2;
        
        if ((col<=5) && (row>=1)
        && (s.loc[row-1][col+1] == oppo))
            h=h+2;       
        
        s.v = h;
        return h;
    }
    
    /** Creates a new instance of MinMaxPlayer */
    public MinMaxPlayer() {
        System.out.println("MinMaxPlayer Initialized.");
        current_state = new State();
    }
    
    public int getType() {
        return 4;
    }
    
    public void go(SimpleBoard b) {
        State current_state = new State();

        current_state.ParseMove(b.movelist);
        //System.out.println("MinMaxPlayer thinking...");
        create_suc(current_state);
        int m = MinimaxDecision(current_state);
        //System.out.println(m);
        b.Move(m);
        
    }
    
    public void setMove(int col) {
    }
    
    public void display_tree(){
        
    }
    private void create_suc(State s){
        //System.out.print("Generate Successors down to level ");
        //System.out.println(lookahead);
        Vector chd = new Vector();
        for (int i=0; i<7; i++) {
            State temp = new State();
            temp.ParseMove(s.movelist);
            if (temp.cols[i]!=6){
                temp.Move(i);
                temp.depth = s.depth+1;
                temp.action = i;
                temp.out=false;
                chd.add(temp);
                if (temp.depth<lookahead) create_suc(temp);            
            }
        }
        s.suc=chd;
    }
    
}

class State extends SimpleBoard{
    public int v = -1;
    public int depth = 0;
    public int action =0;
    public boolean out = false;
    public Vector suc=new Vector();
    State(){
        action = -1;
        v=0;depth=0;clear();
        suc = new Vector();
        out = false;
    }
    
}
