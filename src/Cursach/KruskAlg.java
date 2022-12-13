package Cursach;


import Collections.MyArrayList;
import Collections.MyList;
import java.util.Comparator;

public class KruskAlg
{




    class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge edge1, Edge edge2) {
            if (edge1.weight < edge2.weight)
                return -1;
            if (edge1.weight > edge2.weight)
                return 1;
            return 0;
        }
    }

    private MyList<Edge> edges;
    private int numberOfVertices;
    public static final int MAX_VALUE = 999;
    private MyList<Integer> visit = new MyArrayList<>();



}
