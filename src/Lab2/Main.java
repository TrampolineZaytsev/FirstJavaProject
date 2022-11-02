package Lab2;


import Collections.MyArrayList;
import Collections.MyList;
import java.util.Random;
import static Lab2.TimSort.timSort;

public class Main {
    public static void randomArr(MyList<Integer> arr, int size){
        final Random random = new Random();
        for (int i = 0; i<size; i++){
            arr.add(random.nextInt(size));
        }
    }
    public static void main(String[] args) {
        MyList<Integer> timSortArr = new MyArrayList<>();
        MyList<Integer> insertSortArr = new MyArrayList<>();
        MyList<Integer> mergeSortArr = new MyArrayList<>();
        randomArr(timSortArr, 1000000);
        randomArr(insertSortArr, 1000000);
        randomArr(mergeSortArr, 1000000);

        long start = System.currentTimeMillis();
        timSort(timSortArr);
        long end = System.currentTimeMillis();
        long timSortTime = end - start;
        System.out.println(timSortTime);


    }
}