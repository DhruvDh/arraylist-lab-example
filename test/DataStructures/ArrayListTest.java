package DataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class ArrayListTest {
  private ArrayList<String> list;

  @BeforeEach
  void setUp() {
    list = new ArrayList<>();
  }

  // Basic Operations Tests
  @Test
  void testNewListIsEmpty() {
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  // Add Tests
  @Test
  void testAddFirst() {
    list.addFirst("A");
    assertEquals("A", list.first());
    assertEquals(1, list.size());
  }

  @Test
  void testAddLast() {
    list.addLast("A");
    assertEquals("A", list.last());
    assertEquals(1, list.size());
  }

  @Test
  void testAddAtIndex() {
    list.add(0, "A");
    list.add(1, "B");
    list.add(1, "C");
    assertEquals("A", list.get(0));
    assertEquals("C", list.get(1));
    assertEquals("B", list.get(2));
  }

  @Test
  void testAddAfter() {
    list.addLast("A");
    list.addLast("B");
    assertTrue(list.addAfter("A", "C"));
    assertEquals("C", list.get(1));
  }

  @Test
  void testAddAfterEdgeCases() {
    assertFalse(list.addAfter("nonexistent", "A"));
    list.addFirst("A");
    assertTrue(list.addAfter("A", "B"));
    assertEquals("B", list.last());
  }

  // Remove Tests
  @Test
  void testRemoveFirst() {
    list.addLast("A");
    list.addLast("B");
    assertEquals("A", list.removeFirst());
    assertEquals("B", list.first());
  }

  @Test
  void testRemoveLast() {
    list.addLast("A");
    list.addLast("B");
    assertEquals("B", list.removeLast());
    assertEquals("A", list.last());
  }

  @Test
  void testRemoveByItem() {
    list.addLast("A");
    list.addLast("B");
    assertTrue(list.remove("A"));
    assertEquals("B", list.first());
  }

  @Test
  void testRemoveEdgeCases() {
    assertFalse(list.remove("nonexistent"));
    list.addLast("A");
    assertTrue(list.remove("A"));
    assertFalse(list.remove("A")); // Try removing after already removed
  }

  // Access Tests
  @Test
  void testGet() {
    list.addLast("A");
    list.addLast("B");
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
  }

  @Test
  void testSet() {
    list.addLast("A");
    assertEquals("A", list.set(0, "B"));
    assertEquals("B", list.get(0));
  }

  // Growth Tests
  @Test
  void testGrowth() {
    // Add more elements than initial capacity
    for (int i = 0; i < 15; i++) {
      list.addLast("Item" + i);
    }
    assertEquals(15, list.size());
    assertEquals("Item0", list.first());
    assertEquals("Item14", list.last());
  }

  // Exception Tests
  @Test
  void testNullItemException() {
    assertThrows(IllegalArgumentException.class, () -> list.addFirst(null));
    assertThrows(IllegalArgumentException.class, () -> list.addLast(null));
    assertThrows(IllegalArgumentException.class, () -> list.add(0, null));
    assertThrows(IllegalArgumentException.class, () -> list.set(0, null));
  }

  @Test
  void testIndexOutOfBoundsException() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "A"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));
  }

  @Test
  void testBoundaryConditions() {
    list.addLast("A");
    list.addLast("B");

    // Test exact size boundary
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(3, "C"));

    // Test zero and size-1 boundaries
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(list.size() - 1));

    // Test removal at boundaries
    assertEquals("A", list.remove(0));
    assertEquals(1, list.size());
    assertEquals("B", list.remove(0));
    assertEquals(0, list.size());
  }

  @Test
  void testEmptyListExceptions() {
    assertThrows(NoSuchElementException.class, () -> list.first());
    assertThrows(NoSuchElementException.class, () -> list.last());
    assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    assertThrows(NoSuchElementException.class, () -> list.removeLast());
  }

  // Clear and Contains Tests
  @Test
  void testClearAndContains() {
    list.addLast("A");
    list.addLast("B");
    assertTrue(list.contains("A"));
    list.clear();
    assertTrue(list.isEmpty());
    assertFalse(list.contains("A"));
  }

  @Test
  void testContainsEdgeCases() {
    assertFalse(list.contains(null));
    list.addLast("A");
    assertTrue(list.contains("A"));
    assertFalse(list.contains("B"));
    list.clear();
    assertFalse(list.contains("A"));
  }

  // Index Tests
  @Test
  void testIndexOf() {
    list.addLast("A");
    list.addLast("B");
    list.addLast("A");
    assertEquals(0, list.indexOf("A"));
    assertEquals(1, list.indexOf("B"));
    assertEquals(-1, list.indexOf("C"));
  }

  @Test
  void testIndexOfEdgeCases() {
    assertEquals(-1, list.indexOf(null));
    assertEquals(-1, list.indexOf("nonexistent"));
    list.addLast("A");
    list.addLast("B");
    list.addLast("A");
    assertEquals(0, list.indexOf("A")); // First occurrence
    assertEquals(1, list.indexOf("B")); // Middle element
    assertEquals(-1, list.indexOf("C")); // Non-existent element
  }

  @Test
  void testBoundaryConditionsForAdd() {
    // Test exact boundary conditions for add
    list.add(0, "A"); // Add at start of empty list
    list.add(1, "B"); // Add at size
    assertEquals(2, list.size());

    // Test that exceptions are thrown for invalid indices
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "C")); // Below zero
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(3, "C")); // Above size

    // Verify list wasn't modified by failed operations
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
  }

  @Test
  void testAddAfterWithNullItems() {
    // Test null existing item
    assertThrows(IllegalArgumentException.class, () -> list.addAfter(null, "A"));

    // Test null new item
    list.addFirst("A");
    assertThrows(IllegalArgumentException.class, () -> list.addAfter("A", null));

    // Test both null
    assertThrows(IllegalArgumentException.class, () -> list.addAfter(null, null));

    // Verify original item still exists
    assertEquals("A", list.get(0));
  }

  @Test
  void testArrayGrowthBehavior() {
    // Fill to capacity
    for (int i = 0; i < 10; i++) {
      list.addLast("Item" + i);
    }
    assertEquals(10, list.size());

    // Add one more to force growth
    list.addLast("Overflow");
    assertEquals(11, list.size());
    assertEquals("Item0", list.get(0));
    assertEquals("Overflow", list.get(10));

    // Remove items and verify internal state
    for (int i = 0; i < 5; i++) {
      list.removeFirst();
    }
    assertEquals(6, list.size());
    assertEquals("Item5", list.get(0));
  }

  @Test
  void testIndexOfWithNulls() {
    // Test with empty list
    assertEquals(-1, list.indexOf(null));

    // Test with non-null items
    list.addLast("A");
    list.addLast("B");

    assertEquals(0, list.indexOf("A"));
    assertEquals(1, list.indexOf("B"));
    assertEquals(-1, list.indexOf("NotInList"));

    // Verify null handling
    assertThrows(IllegalArgumentException.class, () -> list.addLast(null));
  }

  @Test
  void testRemoveWithShifting() {
    // Setup list with known values
    list.addLast("A");
    list.addLast("B");
    list.addLast("C");
    list.addLast("D");

    // Remove from middle and verify shifting
    assertEquals("B", list.remove(1));
    assertEquals("A", list.get(0));
    assertEquals("C", list.get(1));
    assertEquals("D", list.get(2));

    // Remove from start and verify shifting
    assertEquals("A", list.remove(0));
    assertEquals("C", list.get(0));
    assertEquals("D", list.get(1));

    // Remove from end and verify
    assertEquals("D", list.remove(1));
    assertEquals(1, list.size());
    assertEquals("C", list.get(0));
  }

  @Test
  void testSetWithBoundaryConditions() {
    list.addLast("A");

    // Test boundaries
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "X"));
    assertThrows(IllegalArgumentException.class, () -> list.set(0, null));

    // Test valid replacement
    assertEquals("A", list.set(0, "B"));
    assertEquals("B", list.get(0));
  }

  @Test
  void testRemoveWithExactBoundaryConditions() {
    list.addLast("A");
    list.addLast("B");
    list.addLast("C");

    // Test index == size-1 (last element)
    assertEquals("C", list.remove(2));
    assertEquals(2, list.size());

    // Test index == 0 (first element)
    assertEquals("A", list.remove(0));
    assertEquals(1, list.size());

    // Test index == size-1 again after removals
    assertEquals("B", list.remove(0));
    assertEquals(0, list.size());

    // Verify state after all removals
    assertTrue(list.isEmpty());
  }

  @Test
  void testAddAtExactBoundaries() {
    // Test adding at exact boundaries multiple times
    list.add(0, "A"); // At start when empty
    list.add(1, "B"); // At end
    list.add(1, "C"); // In middle
    list.add(0, "D"); // At start when not empty
    list.add(4, "E"); // At end again

    // Verify final state
    assertEquals("D", list.get(0));
    assertEquals("A", list.get(1));
    assertEquals("C", list.get(2));
    assertEquals("B", list.get(3));
    assertEquals("E", list.get(4));
    assertEquals(5, list.size());
  }

  @Test
  void testGrowthAndRemovalPatterns() {
    // Fill beyond initial capacity
    for (int i = 0; i < 12; i++) {
      list.addLast("Item" + i);
    }

    // Remove from various positions
    list.remove(0); // First
    list.remove(5); // Middle
    list.remove(list.size() - 1); // Last

    // Add more to trigger potential growth
    for (int i = 12; i < 15; i++) {
      list.addLast("Item" + i);
    }

    // Verify size and contents
    assertEquals(12, list.size());
    assertEquals("Item1", list.get(0));
    assertEquals("Item14", list.get(list.size() - 1));
  }

  @Test
  void testSetEdgeCases() {
    list.addLast("A");
    list.addLast("B");

    // Test set at boundaries
    assertEquals("A", list.set(0, "X"));
    assertEquals("B", list.set(1, "Y"));

    // Verify state
    assertEquals("X", list.get(0));
    assertEquals("Y", list.get(1));

    // Test invalid indices
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, "Z"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "Z"));
  }

  @Test
  void testIndexOfWithDuplicatesAndBoundaries() {
    // Test with duplicates
    list.addLast("A");
    list.addLast("B");
    list.addLast("A");
    list.addLast("C");
    list.addLast("A");

    // Test first occurrence
    assertEquals(0, list.indexOf("A"));

    // Test middle
    assertEquals(1, list.indexOf("B"));

    // Test last unique element
    assertEquals(3, list.indexOf("C"));

    // Test non-existent
    assertEquals(-1, list.indexOf("D"));

    // Test after clearing
    list.clear();
    assertEquals(-1, list.indexOf("A"));
  }

  @Test
  void testAddAtAbsoluteBoundaries() {
    // Test adding at size boundary
    list.add(0, "A");
    list.add(1, "B");

    // This should succeed - adding exactly at size
    list.add(2, "C");
    assertEquals("C", list.get(2));

    // These should fail - adding beyond size
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(4, "D"));

    // Verify state wasn't corrupted
    assertEquals(3, list.size());
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals("C", list.get(2));
  }

  @Test
  void testRemoveWithPreciseShifting() {
    // Setup initial state
    list.addLast("A");
    list.addLast("B");
    list.addLast("C");
    list.addLast("D");

    // Remove and verify exact positions after each shift
    String removed = list.remove(1); // Remove "B"
    assertEquals("B", removed);
    assertEquals("A", list.get(0)); // A stays at 0
    assertEquals("C", list.get(1)); // C shifts to 1
    assertEquals("D", list.get(2)); // D shifts to 2

    // Remove from end
    removed = list.remove(2); // Remove "D"
    assertEquals("D", removed);
    assertEquals("A", list.get(0)); // A stays at 0
    assertEquals("C", list.get(1)); // C stays at 1
    assertEquals(2, list.size());

    // Remove from start
    removed = list.remove(0); // Remove "A"
    assertEquals("A", removed);
    assertEquals("C", list.get(0)); // C shifts to 0
    assertEquals(1, list.size());
  }

  @Test
  void testNullChecksInAllPaths() {
    // Test null in add methods
    assertThrows(IllegalArgumentException.class, () -> list.add(0, null));
    assertThrows(IllegalArgumentException.class, () -> list.addFirst(null));
    assertThrows(IllegalArgumentException.class, () -> list.addLast(null));

    // Test null in addAfter
    list.addFirst("A");
    assertThrows(IllegalArgumentException.class, () -> list.addAfter(null, "B"));
    assertThrows(IllegalArgumentException.class, () -> list.addAfter("A", null));
    assertThrows(IllegalArgumentException.class, () -> list.addAfter(null, null));

    // Test null in contains and indexOf
    assertFalse(list.contains(null));
    assertEquals(-1, list.indexOf(null));

    // Verify list state wasn't corrupted
    assertEquals(1, list.size());
    assertEquals("A", list.get(0));
  }

  @Test
  void testExactIndexOperations() {
    // Fill list
    list.addLast("A");
    list.addLast("B");
    list.addLast("C");

    // Test operations at exact boundaries
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));

    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "X"));

    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));

    // Verify state
    assertEquals(3, list.size());
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals("C", list.get(2));
  }

  @Test
  void testIndexOfWithExactComparisons() {
    // Test with exact equality scenarios
    list.addLast("A");
    list.addLast("B");
    list.addLast("A"); // Duplicate

    // Test exact matches
    assertEquals(0, list.indexOf("A")); // First occurrence
    assertEquals(1, list.indexOf("B")); // Single occurrence
    assertEquals(-1, list.indexOf("C")); // No match

    // Test with same value but different object
    String newA = new String("A");
    assertEquals(0, list.indexOf(newA)); // Should find first "A"

    // Test after removing
    list.remove("A"); // Remove first "A"
    assertEquals(1, list.indexOf("A")); // Should find second "A"
  }
}
