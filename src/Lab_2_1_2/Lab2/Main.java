package Lab_2_1_2.Lab2;


import Lab_2_1_2.Collections.MyArrayList;
import Lab_2_1_2.Collections.MyList;
import Lab_2_1_2.Sorts.InsertionSort;
import Lab_2_1_2.Sorts.MergeSort;
import Lab_2_1_2.Sorts.Sort;
import Lab_2_1_2.Sorts.TimSort;

public class Main {
    public static void main(String[] args) {

        MyList<Integer> willSortList = null;
        Sort<Integer> sortingList = null;
        //long[][] dataForGraph = new long[3][4];
        MyList<MyList<Long>> dataForGraph = utils.creat2DLongArr(3, 4);
        long sortTime;

        for (int l = 1000, choiceLen = 0; l <= 1000000; l *= 10, choiceLen++) {
            for (int i = 0; i < 3; i++) {
                //mix list
                willSortList = new MyArrayList<>();
                utils.randomArr(willSortList, l);

                //choice type sort (Tim/Merge/Insert);
                if (i == 0) sortingList = new TimSort<>(willSortList);
                else if (i == 1) sortingList = new MergeSort<>(willSortList);
                else sortingList = new InsertionSort<>(willSortList);

                //add time sorts
                if (i == 2 && l == 1000000)
                    dataForGraph.get(i).set(choiceLen, (long) l);

                else
                    //dataForGraph[i][choiceLen]  = utils.timeSort(sortingList);
                    //dataForGraph.get(i).set(choiceLen, utils.timeSort(sortingList));
                    dataForGraph.get(i).set(choiceLen, utils.timeSort(sortingList));
            }

        }



        for (int i = 0; i < 3; i++){
            System.out.println(dataForGraph.get(i).toString());
        }

        //System.out.println(timSortTime);
        //System.out.println(timSortArr.toString());




    }
}