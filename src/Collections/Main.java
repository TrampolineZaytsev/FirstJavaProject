package Collections;




public class Main {





    public static void main(String[] args) {

        MyArrayList<Integer> arr = new MyArrayList<>();
        for (int i = 0; i < 12; i++){
            arr.add(i);
        }


        //arr.insert(3, 100);
        arr.clear();

        System.out.print(arr.toString());

    }

}