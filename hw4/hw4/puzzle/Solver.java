package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.*;

public class Solver {

    public class SearchNode {
        WorldState thisWS;
        //the number of moves made to reach
        // this world state from the initial state.
        int moves;
        //a reference to the previous search node.
        SearchNode preSN;

        public SearchNode(WorldState ws, int moves, SearchNode preSN) {
            this.thisWS = ws;
            this.moves = moves;
            this.preSN = preSN;
        }
    }
    int totalMoves = 0;

    //List<WorldState> listOfWorldState = new ArrayList<>();
    Stack<WorldState> sol = new Stack<>();

    //second optimization
    HashMap<WorldState, Integer> edtGCaches = new HashMap<>();

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        Comparator<SearchNode> cp = new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                //second optimization
                if (!edtGCaches.containsKey(o1.thisWS)) {
                    edtGCaches.put(o1.thisWS, o1.thisWS.estimatedDistanceToGoal());
                }
                if (!edtGCaches.containsKey(o2.thisWS)) {
                    edtGCaches.put(o2.thisWS, o2.thisWS.estimatedDistanceToGoal());
                }

                return o1.moves + edtGCaches.get(o1.thisWS)
                        - (o2.moves + edtGCaches.get(o2.thisWS));
            }
        };
        MinPQ<SearchNode> pq = new MinPQ<>(cp);
        SearchNode sn1 = new SearchNode(initial, 0, null);
        pq.insert(sn1);

        SearchNode currentSN = pq.delMin();
        while (!currentSN.thisWS.isGoal()) {
            for (WorldState ws : currentSN.thisWS.neighbors()) {
                if (currentSN.preSN != null && ws.equals(currentSN.preSN.thisWS)) {
                    continue;
                }
                SearchNode toInsert = new SearchNode(ws, currentSN.moves + 1, currentSN);
                pq.insert(toInsert);
            }
            SearchNode temp = pq.delMin();
            currentSN = temp;
            //outMoves += 1;
            //listOfWorldState.add(temp.thisWS);
        }
        while (currentSN != null) {
            sol.push(currentSN.thisWS);
            currentSN = currentSN.preSN;
            totalMoves += 1;
        }
    }

    /**Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.*/
    public int moves() {
        return totalMoves - 1;
    }
    /**Returns a sequence of WorldStates from the initial WorldState
     to the solution.*/
    public Iterable<WorldState> solution() {
        return sol;
    }
}

