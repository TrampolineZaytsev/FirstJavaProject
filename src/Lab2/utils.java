package Lab2;

import Collections.MyList;
import Sorts.Sort;

import java.util.Random;

public class utils<T> {
    /*public utils (MyList<T> arr, int size){
        randomArr((MyList<Integer>) arr, size);
    }*/
    public static void randomArr(MyList<Integer> arr, int size){
        final Random random = new Random();
        for (int i = 0; i<size; i++){
            arr.add(random.nextInt(size));
        }
    }

    public static long timeSort(Sort<Integer> sortedList){
        long start = System.currentTimeMillis();
        sortedList.sort();
        long end = System.currentTimeMillis();
        return end - start;
    }


}
