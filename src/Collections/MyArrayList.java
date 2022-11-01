package Collections;

class MyArrayList<T> {

    private final int defCapacity = 10;
    private int size, capacity = defCapacity;
    private Object[] arr;

    MyArrayList(int size)
    {
        this.size = size;
        arr = new Object[size];
    }

    MyArrayList()
    {
        size = 0;
        arr = new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    private void reCapacity() {
        Object[] newArr = new Object[(int)(capacity*1.5)];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public boolean add(T data) {
        if (size >= capacity) reCapacity();
        arr[size++] = data;
        return true;
    }

    public void insert(int index, T data){
        if (size >= capacity) reCapacity();
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index);
        }
        System.arraycopy(arr, index, arr, index+1, ++size-index);
        arr[index] = data;
    }

    public T get(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);/////////////////////////
        return (T) arr[index];
    }

    public T set(int index, T data){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        arr[index] = data;
        return data;
    }
    public void removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
        System.arraycopy(arr, index+1, arr, index, (--size)-index+1);
    }
    public int indexOf(T data){
        for (int i = 0; i < size; i++){
            if (arr[i] == data) return i;
        }
        return -1;
    }

    public boolean remove(T data){
        int count = 0, index;
        do
        {
            index = indexOf(data);
            if (index == -1)
            {
                if (count == 0) return false;
                return true;
            }

            removeAt(index);
            count += 1;

        } while (true);
    }

    public void clear() {
        arr =  new Object[defCapacity];
        size = 0;
    }

    public String toString() {
        if (size == 0)
            return "empty";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        return sb.append(arr[size - 1]).append("]").toString();
    }


}
