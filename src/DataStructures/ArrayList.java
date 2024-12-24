package DataStructures;

import java.util.NoSuchElementException;
import ADTs.ListADT;

public class ArrayList<T> implements ListADT<T> {
  private T[] array;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  @SuppressWarnings("unchecked")
  public ArrayList() {
    array = (T[]) new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  private void growArrayIfNeeded() {
    if (size == array.length) {
      @SuppressWarnings("unchecked")
      T[] newArray = (T[]) new Object[array.length * 2];
      System.arraycopy(array, 0, newArray, 0, size);
      array = newArray;
    }
  }

  @Override
  public void clear() {
    @SuppressWarnings("unchecked")
    T[] newArray = (T[]) new Object[DEFAULT_CAPACITY];
    array = newArray;
    size = 0;
  }

  @Override
  public boolean contains(T item) {
    return indexOf(item) != -1;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(int index, T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    growArrayIfNeeded();
    System.arraycopy(array, index, array, index + 1, size - index);
    array[index] = item;
    size++;
  }

  @Override
  public void addFirst(T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    add(0, item);
  }

  @Override
  public void addLast(T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    add(size, item);
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return remove(0);
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return remove(size - 1);
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    T removed = array[index];
    System.arraycopy(array, index + 1, array, index, size - index - 1);
    array[size - 1] = null;
    size--;
    return removed;
  }

  @Override
  public T first() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return array[0];
  }

  @Override
  public T last() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return array[size - 1];
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return array[index];
  }

  @Override
  public T set(int index, T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    T old = array[index];
    array[index] = item;
    return old;
  }

  @Override
  public int indexOf(T item) {
    for (int i = 0; i < size; i++) {
      if (item == null ? array[i] == null : item.equals(array[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean addAfter(T existing, T item) {
    if (existing == null || item == null) {
      throw new IllegalArgumentException("Neither existing nor item can be null");
    }
    int index = indexOf(existing);
    if (index == -1) {
      return false;
    }
    add(index + 1, item);
    return true;
  }

  @Override
  public boolean remove(T item) {
    int index = indexOf(item);
    if (index == -1) {
      return false;
    }
    remove(index);
    return true;
  }
}
