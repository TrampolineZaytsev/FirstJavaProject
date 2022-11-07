package Lab_2_1_2.Sorts;


import Lab_2_1_2.Collections.MyArrayList;
import Lab_2_1_2.Collections.MyLinkedList;
import Lab_2_1_2.Collections.MyList;
import Lab_2_1_2.Collections.MyStack;


public class TimSort <T extends Comparable<T>> implements Sort<T>
{
    private final MyList<T> arr;
    public TimSort (MyList<T> arr ){
        this.arr = arr;
    }
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



    public static class Run {
        int index;
        int size;

        public Run(int index, int size){
            this.index = index;
            this.size = size;
        }

    }

    // This function sorts array from left index to
    // to right index which is of size atmost RUN
    public void binInsert(int cur, int beg, int end) {
        while (end >= beg)
        {
            if (arr.get(cur).compareTo(arr.get((beg + end) / 2)) > 0)
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
    public void insertionSort(int left, int right) {
        for (int i = left + 1; i <= right; i++){
            if (arr.get(i).compareTo(arr.get(i-1)) < 0){
                binInsert(i, left, i-1);
            }
        }
    }

    public int doGallop(MyList <T> galopList, int point, T constDataPoint)
    {
        int gallop = 1;
        int size = galopList.getSize();
        while (true) {
            if ((point < size) && (galopList.get(point).compareTo(constDataPoint) <= 0)) {
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
    public void merge(Run left, Run right) {

        //copy left, right subarr
        MyList<T> leftList = new MyArrayList<>();
        MyList<T> rightList = new MyArrayList<>();
        for (int i = left.index; i < left.index + left.size; i++) leftList.add(arr.get(i));
        for (int i = right.index; i < right.index + right.size; i++) rightList.add(arr.get(i));

        int leftPoint = 0, rightPoint = 0;
        int pastPoint = 0; //need for copy elem. from left or right subarr to arr after galop
        int curInd = left.index;

        while (leftPoint < left.size && rightPoint < right.size) {
            if (leftList.get(leftPoint).compareTo(rightList.get(rightPoint)) <= 0) {
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



    public void sort() {
        int size = arr.getSize();
        int minRun = getMinRun(arr.getSize());
        MyStack<Run> steckRun = new MyLinkedList<>();
        Run curRun = null, lastRun = null;

        for (int begRun = 0; begRun < size; begRun += minRun) {

            //sorted subsequentes (сорт. подпоследовательности)
            int endRun = Math.min((begRun + minRun - 1), (size - 1));
            insertionSort(begRun, endRun);

            //merge run
            curRun = new Run(begRun, endRun - begRun + 1);
            while (!steckRun.isEmpty() && ((steckRun.peek().size == curRun.size) || (endRun == size-1))) {
                lastRun = steckRun.pop();
                merge(lastRun, curRun);
            }
            steckRun.push(curRun);
        }
    }
}

// This code has been contributed by 29AjayKumar