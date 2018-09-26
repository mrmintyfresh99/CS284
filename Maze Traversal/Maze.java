package Maze;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that traverses a maze.
 * @author Mathew Seedhom
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    		if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || x < 0 || y < 0 || maze.getColor(x, y) == BACKGROUND || maze.getColor(x, y) == TEMPORARY){
    			return false;
    		} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
    			maze.recolor(x, y, PATH);
    			return true;
    		} else if (maze.getColor(x, y) == NON_BACKGROUND){
    			maze.recolor(x, y, TEMPORARY);
    			if (this.findMazePath(x-1,y) || this.findMazePath(x+1,y) || this.findMazePath(x,y-1) || this.findMazePath(x,y+1)) {
    				maze.recolor(x, y, PATH);
    				return true;
    			}
    		}
    		return this.findMazePath(x-1,y) || this.findMazePath(x+1,y) || this.findMazePath(x,y-1) || this.findMazePath(x,y+1);
	}
    
        
        

    // ADD METHOD FOR PROBLEM 2 HERE
    /**
     * Helper function of findAllMazePaths.
     * @param x
     * @param y
     * @param res
     * @param trace
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> res, Stack<PairInt> trace) {
    		if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || x < 0 || y < 0 || maze.getColor(x, y) == BACKGROUND || maze.getColor(x, y) == TEMPORARY){
    			return;
    		} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
    			PairInt Dummy = new PairInt(x,y);
    			trace.push(Dummy);
    			ArrayList<PairInt> temp = new ArrayList<PairInt>();
    			temp.addAll(trace);
    			res.add(temp);
    			trace.pop();
    			maze.recolor(x,y, NON_BACKGROUND);
    			return;
    		} else {
    			PairInt Dummy = new PairInt(x,y);
    			trace.push(Dummy);
    			maze.recolor(x, y, TEMPORARY);
    			this.findMazePathStackBased(x + 1, y, res, trace);
    			this.findMazePathStackBased(x - 1, y, res, trace);
    			this.findMazePathStackBased(x, y + 1, res, trace);
    			this.findMazePathStackBased(x, y - 1, res, trace);
    			maze.recolor(x, y, NON_BACKGROUND);
    			trace.pop();
    			return;
    		}
	}
    /**
     * Creates an ArrayList of all the possible paths to reach the end in the maze.
     * @param x
     * @param y
     * @return
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
	    	ArrayList <ArrayList <PairInt >> result = new ArrayList <>();
	    	Stack<PairInt> trace = new Stack<>(); 
	    	findMazePathStackBased(0,0,result, trace);
	    	return result;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    /**
     * Returns the shortest path that can be taken to reach the end of the maze.
     * @param x
     * @param y
     * @return
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    		ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
    		result = findAllMazePaths(x, y);
    		int intArray[] = new int[result.size()];
    		for (int i = 0; i < result.size(); i++) {
    			intArray[i] = result.get(i).size();
    		}
    		int max = intArray[0];
    		int index = 0;
    		for (int i = 1; i < intArray.length; i++) {
    		    if (intArray[i] > max) {
    		      max = intArray[i];
    		      index = i;
    		    }
    		}
    		return result.get(index);
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
