package Cursach;
import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyList;


import java.io.*;

public class utils {
    private static int numberVertexes = 0;

    //read file and creat Adfancity Matrix
    private static MyList<MyList> readFile (String fileName) throws IOException {

        int numberVertex = 0;
        MyList<Character> vertex = new MyArrayList<>();


        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();


        for (int i = 0; i < line.length(); i++) {
            String curChar = String.valueOf(line.charAt(i));
            if (curChar.equals(" ") == false) {
                vertex.add(curChar.charAt(0));
            }
        }
        numberVertex = vertex.getSize();
        numberVertexes = numberVertex;
        MyList<MyList> matrix = creat2DArr(numberVertex, numberVertex);

        //MyList<Integer> weights = new MyArrayList<>();
        for (int i = 0; i < numberVertex; i++) {
            line = reader.readLine();
            String[] weight = line.split(" ");
            for (int j = 0; j < numberVertex; j++) {
                matrix.get(i).set(j, Integer.valueOf(weight[j]));
            }
        }
        return matrix;
    }


    //creat list of edges from Matrix
    public static MyList<Edge> getEdges (String fileName) throws IOException {
        MyList<Edge> edges = new MyLinkedList<>();
        MyList<MyList> matrix = readFile("C:\\Users\\Admin\\IdeaProjects\\FirstJavaProject\\src\\Cursach\\fileInput.txt");
        int numberVertex = matrix.get(0).getSize();

        for (int i = 0; i < numberVertex; i++){
            for (int j = 0; j < numberVertex; j++){
                int weight = (int) matrix.get(i).get(j);
                if (i < j && weight > 0){
                    edges.add(new Edge(weight, i, j));
                }
            }
        }
        return edges;
    }

    // util function to get number of vertexes
    public static int getNumberVertexes() {return numberVertexes;}

    public static MyList<MyList> creat2DArr(int size1, int size2){
        MyList<MyList> arr = new MyArrayList<>(size1);
        for (int i = 0; i < size1; i++) arr.set(i, new MyArrayList<>(size2));
        return arr;
    }
    public static MyList<MyList> creat2DArr(int size1){
        MyList<MyList> arr = new MyArrayList<>(size1);
        for (int i = 0; i < size1; i++) arr.set(i, new MyArrayList<>());
        return arr;
    }


}
