package Lab2;


import Collections.MyArrayList;
import Collections.MyList;
import Sorts.Sort;
import Sorts.TimSort;

import java.util.Random;

public class Main {
    /*public static void randomArr(MyList<Integer> arr, int size){
        final Random random = new Random();
        for (int i = 0; i<size; i++){
            arr.add(random.nextInt(size));
        }
    }*/
    public static void main(String[] args) {
        int l = 1000;
        MyList<Integer> timSortArr = new MyArrayList<>();
        MyList<Integer> insertSortArr = new MyArrayList<>();
        MyList<Integer> mergeSortArr = new MyArrayList<>();


        utils.randomArr(timSortArr, l);

        Sort<Integer> sortedList = new TimSort<>(timSortArr);
        long timSortTime = utils.timeSort(sortedList);
        System.out.println(timSortTime);
        System.out.println(timSortArr.toString());




    }
}