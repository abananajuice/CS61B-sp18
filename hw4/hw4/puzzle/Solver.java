package hw4.puzzle;


import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    int totmoves = 0;
    List<WorldState> solu;

    // Before we describe this algorithm, we’ll first define a search node of the puzzle to be... ...
    // 定义SearchNode节点
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState n;
        private int moves;
        private SearchNode previous;

        public SearchNode(WorldState w,int m,SearchNode pre){
            n = w;
            moves = m;
            previous = pre;
        }

        public SearchNode getPre(){
            return previous;
        }

        public int getMoves() {
            return moves;
        }

        // 因为优先队列比较需要用到比较器，因此需要实现比较方法。
        @Override
        public int compareTo(SearchNode o) {
            return this.n.estimatedDistanceToGoal() + this.moves - o.n.estimatedDistanceToGoal() - o.moves;
        }
    }
    public Solver(WorldState initial){
        MinPQ<SearchNode> PQNode =new MinPQ<>();
        SearchNode init = new SearchNode(initial,0,null);
        PQNode.insert(init);
        Boolean flag = true;

        while (flag) {
            SearchNode Node = PQNode.delMin();
            if (Node.n.isGoal()) {
                totmoves = Node.moves;
                solu = getSoluSequ(Node);
                flag = false;
                return;
            }
            else {
                for(WorldState i : Node.n.neighbors()){
                    // 避免将已经搜索过的节点加入到优先队列中去
                    if(!i.equals(Node.previous.n) || Node.previous==null) {
                        SearchNode newNode = new SearchNode(i, Node.moves + 1, Node);
                        PQNode.insert(newNode);
                    }
                }
            }

        }

    }

    private List<WorldState> getSoluSequ(SearchNode Sn){
        List<WorldState> sequence = new ArrayList<WorldState>();
        SearchNode p = Sn;
        while (p.previous == null){
            sequence.add(p.n);
            p = p.previous;
        }

        return sequence;
    }


    public int moves(){
        return this.totmoves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> soluR = new ArrayList<>();

        for(int i=moves();i>=0;i--){
            soluR.add(solu.get(i));
        }

        return soluR;
    }
}
