package ADTs;

/**
 * An interface for a List Abstract Data Type (ADT).
 * Extends the Collection ADT with operations specific to lists,
 * including indexed access and modifications at both ends.
 *
 * @param <T> the type of elements in the list
 */
public interface ListADT<T> extends CollectionADT<T> {
  /**
   * Inserts an element at the specified position.
   * 
   * @param index position to insert the element
   * @param item  element to insert
   * @throws IndexOutOfBoundsException if index is invalid
   * @throws IllegalArgumentException if item is null
   */
  public void add(int index, T item);

  /**
   * Adds an element to the beginning of the list.
   * 
   * @param item element to add
   * @throws IllegalArgumentException if item is null
   */
  public void addFirst(T item);

  /**
   * Adds an element to the end of the list.
   * 
   * @param item element to add
   * @throws IllegalArgumentException if item is null
   */
  public void addLast(T item);

  /**
   * Adds the specified element after the first occurrence of the existing item.
   * 
   * @param existing the element to search for
   * @param item     the element to add
   * @return true if the element was added, false if existing element was not
   *         found
   * @throws IllegalArgumentException if either existing or item is null
   */
  public boolean addAfter(T existing, T item);

  /**
   * Removes and returns the first element.
   * 
   * @return the first element
   * @throws java.util.NoSuchElementException if list is empty
   */
  public T removeFirst();

  /**
   * Removes and returns the last element.
   * 
   * @return the last element
   * @throws java.util.NoSuchElementException if list is empty
   */
  public T removeLast();

  /**
   * Removes and returns the element at the specified position.
   * 
   * @param index position of element to remove
   * @return the removed element
   * @throws IndexOutOfBoundsException if index is invalid
   */
  public T remove(int index);

  /**
   * Removes the first occurrence of the specified element from the list.
   * 
   * @param item element to be removed
   * @return true if element was found and removed, false otherwise
   */
  public boolean remove(T item);

  /**
   * Returns the first element without removing it.
   * 
   * @return the first element
   * @throws java.util.NoSuchElementException if list is empty
   */
  public T first();

  /**
   * Returns the last element without removing it.
   * 
   * @return the last element
   * @throws java.util.NoSuchElementException if list is empty
   */
  public T last();

  /**
   * Returns the element at the specified position.
   * 
   * @param index position of element to return
   * @return the element at the specified position
   * @throws IndexOutOfBoundsException if index is invalid
   */
  public T get(int index);

  /**
   * Replaces the element at the specified position.
   * 
   * @param index position of element to replace
   * @param item  element to store
   * @return the element previously at the position
   * @throws IndexOutOfBoundsException if index is invalid
   * @throws IllegalArgumentException if item is null
   */
  public T set(int index, T item);

  /**
   * Returns the position of the first occurrence of the specified element.
   * 
   * @param item element to search for
   * @return the index of the first occurrence, or -1 if not found
   */
  public int indexOf(T item);
}
