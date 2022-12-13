package Cursach;



import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyList;
import Lab_2_1_2.Sorts.MergeSort;
import Lab_2_1_2.Sorts.Sort;


import java.io.*;


public class main {


    public static void main(String[] args) throws IOException {


        MyList<Edge> edges = utils.getEdges("C:\\Users\\Admin\\IdeaProjects\\FirstJavaProject\\src\\Cursach\\fileInput.txt");
        int numberVertex = utils.getNumberVertexes();

        Sort<Edge> sortingList = new MergeSort<>(edges);
        sortingList.sort();

        /*for (int i = 0; i < edges.getSize(); i++){
            System.out.print(edges.get(i).weight);
            System.out.print(" ");
            System.out.print(edges.get(i).start);
            System.out.print(" ");
            System.out.print(edges.get(i).end);
            System.out.println(" ");
        }*/


        MyList<Integer> unitedVertex = new MyLinkedList<>(); // U
        MyList<MyList> isolatedGroup = utils.creat2DArr(numberVertex); //D
        MyList<Edge> edgesOfMST = new MyLinkedList<>(); //T

        for (int i = 0; i < edges.getSize(); i++) {
            int start = edges.get(i).start;
            int end = edges.get(i).end;

            if (!unitedVertex.contains(start) || !unitedVertex.contains(end)) {
                if (!unitedVertex.contains(start) && !unitedVertex.contains(end)) {

                    isolatedGroup.get(start).add(start);
                    isolatedGroup.get(start).add(end);
                    isolatedGroup.get(end).add(start);
                    isolatedGroup.get(end).add(end);
                }
                else {
                    if (isolatedGroup.get(start).isEmpty()) {
                        int size = isolatedGroup.get(end).getSize();
                        for (int k = 0; k < size; k++)
                            isolatedGroup.get((Integer) isolatedGroup.get(end).get(k)).add(start);
                        for (int k = 0; k < isolatedGroup.get(end).getSize(); k++)
                            isolatedGroup.get(start).add(isolatedGroup.get(end).get(k));
                    }
                    else {
                        int size = isolatedGroup.get(start).getSize();
                        for (int k = 0; k < isolatedGroup.get(start).getSize(); k++)
                            isolatedGroup.get((Integer) isolatedGroup.get(start).get(k)).add(end);
                        for (int k = 0; k < isolatedGroup.get(start).getSize(); k++)
                            isolatedGroup.get((Integer) isolatedGroup.get(end).get(k)).add(start);
                    }
                }

                edgesOfMST.add(edges.get(i));
                if (!unitedVertex.contains(start)) unitedVertex.add(start);
                if (!unitedVertex.contains(end)) unitedVertex.add(end);
            }
        }




        for (int i = 0; i < edges.getSize(); i++) {
            int start = edges.get(i).start;
            int end = edges.get(i).end;

            if (isolatedGroup.get(start).contains(start) && !isolatedGroup.get(start).contains(end)) {
                edgesOfMST.add(edges.get(i));
                int sizeStart = isolatedGroup.get(start).getSize();
                MyList temp = new MyArrayList();

                for (int j = 0; j < sizeStart; j++) {
                    int sizeEnd = isolatedGroup.get(end).getSize();
                    Integer valForTemp = (Integer) isolatedGroup.get(start).get(j);
                    temp.add(valForTemp);
                    for (int k = 0; k < sizeEnd; k++)
                        isolatedGroup.get(valForTemp).add(isolatedGroup.get(end).get(k));
                }

                int sizeEnd = isolatedGroup.get(end).getSize();
                for (int j = 0; j < sizeEnd; j++) {
                    for (int k = 0; k < temp.getSize(); k++)
                        isolatedGroup.get((Integer) isolatedGroup.get(end).get(j)).add(temp.get(k));
                }
            }
        }



        for (int i = 0; i < edgesOfMST.getSize(); i++){
            System.out.print(edgesOfMST.get(i).weight);
            System.out.print(" ");
            System.out.print(edgesOfMST.get(i).start);
            System.out.print(" ");
            System.out.print(edgesOfMST.get(i).end);
            System.out.println(" ");
        }





    }
}
