package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> q;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        q = new LinkedList();
        q.add(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // DONE: Your code here.
        // Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        q.remove();
        for (int w : maze.adj(v)) {

            if (!marked[w]) {
                marked[w] = true;
                q.add(w);
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                if (targetFound) {
                    return;
                }
                bfs(w);
            }
        }

    }


    @Override
    public void solve() {
        bfs(s);
    }
}

