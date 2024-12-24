package ADTs;

/**
 * An interface for a Collection Abstract Data Type (ADT).
 * Provides basic operations that any collection should support.
 *
 * @param <T> the type of elements in the collection
 */
public interface CollectionADT<T> {
    /**
     * Removes all elements from the collection.
     */
    public void clear();

    /**
     * Checks if a specific element is in the collection.
     * 
     * @param item the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T item);

    /**
     * Checks if the collection has no elements.
     * 
     * @return true if the collection is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the collection.
     * 
     * @return the number of elements
     */
    public int size();
}
