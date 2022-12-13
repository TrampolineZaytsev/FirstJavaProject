package Cursach;

public class Edge implements Comparable<Edge> {
    int weight, start, end;
    public Edge(int weight, int start, int end){
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public int compareTo(Edge edge){
        if (this.weight < edge.weight) return -1;
        else if (this.weight > edge.weight) return 1;
        else return -0;
    }



}
