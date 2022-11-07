package Lab_2_1_2.Collections;

public interface MyStack<T>{
    T push(T item);

    T pop();

    T peek();

    boolean isEmpty();
}
