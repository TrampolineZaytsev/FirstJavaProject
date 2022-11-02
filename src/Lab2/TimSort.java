package Lab2;


import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyList;
import Collections.MyStack;

import java.util.Random;


class TimSort
{

    public static int getMinRun(int n)
    {
        int r = 0;
        while (n >= 64)
        {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    // This function sorts array from left index to
    // to right index which is of size atmost RUN
    public static void binInsert(MyList<Integer> arr, int cur, int beg, int end) {
        while (end >= beg)
        {
            if (arr.get(cur) > arr.get((beg + end) / 2))
            {
                beg = (beg + end) / 2 + 1;
            }
            else
            {
                end = (beg + end) / 2 - 1;
            }
        }
        arr.insertBack(cur, beg);
    }
    public static void insertionSort(MyList<Integer> arr, int left, int right) {
        for (int i = left + 1; i <= right; i++){
            if (arr.get(i) < arr.get(i-1)){
                binInsert(arr, i, left, i-1);
            }
        }
    }

    public static int doGallop(MyList<Integer> listGalop, int point, int constDataPoint)
    {
        int gallop = 1;
        int size = listGalop.getSize();
        while (true) {
            if ((point < size) && (listGalop.get(point) <= constDataPoint)) {
                point += gallop;
            }
            else {
                point -= (gallop / 2);
                point++;
                gallop = 1;
                return point;
            }
            gallop *= 2;
        }
    }

    // Merge function merges the sorted runs
    public static void merge(MyList<Integer> arr, Run left, Run right) {
        MyList<Integer> leftList = new MyArrayList<>();
        MyList<Integer> rightList = new MyArrayList<>();
        //copy left, right subarr
        for (int i = left.index; i < left.index + left.size; i++) leftList.add(arr.get(i));
        for (int i = right.index; i < right.index + right.size; i++) rightList.add(arr.get(i));
        int leftPoint = 0, rightPoint = 0;
        int pastPoint = 0; //need for copy elem. from left or right subarr to arr after galop
        int curInd = left.index;

        while (leftPoint < left.size && rightPoint < right.size) {
            if (leftList.get(leftPoint) <= rightList.get(rightPoint)) {
                pastPoint = leftPoint;
                leftPoint = doGallop(leftList, leftPoint, rightList.get(rightPoint));
                for (int i = pastPoint; i < leftPoint; i++, curInd++) {
                    arr.set(curInd, leftList.get(i));
                }
            }
            else {
                pastPoint = rightPoint;
                rightPoint = doGallop(rightList, rightPoint, leftList.get(leftPoint));
                for (int i = pastPoint; i < rightPoint; i++, curInd++) arr.set(curInd, rightList.get(i));
            }
        }
        for (int i = leftPoint; i < left.size; i++, curInd++){
            arr.set(curInd, leftList.get(i));
        }
        for (int i = rightPoint; i < right.size; i++, curInd++) {
            arr.set(curInd, rightList.get(i));
        }
        right.index = left.index;
        right.size += left.size;

        //mylist.view();
    }


    public static void timSort(MyList<Integer> arr) {
        int size = arr.getSize();
        int minRun = getMinRun(arr.getSize());
        MyStack<Run> steckRun = new MyLinkedList<>();
        Run curRun = null, lastRun = null;

        for (int begRun = 0; begRun < size; begRun += minRun) {
            //sorted subsequentes (сорт. подпоследовательности)
            int endRun = Math.min((begRun + minRun - 1), (size - 1));
            insertionSort(arr, begRun, endRun);
            curRun = new Run(begRun, endRun - begRun + 1);

            //merge run
            while (!steckRun.isEmpty() && ((steckRun.peek().size == curRun.size) || (endRun == size-1))) {
                lastRun = steckRun.pop();
                merge(arr, lastRun, curRun);
            }
            steckRun.push(curRun);
        }
    }
}

// This code has been contributed by 29AjayKumar