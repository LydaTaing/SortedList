package project3;
import java.util.*;

/**
 * @author tainglyda
 * April 17, 2018
 * CSC 143
 * 
 * This class builds a list of sorted elements and has three constructors
 * where the users can insert the capacity, otherwise it sets to 10.
 * Also, user can set the uniqueness to true for non repeated elements.
 * 
 */
public class SortedIntList {
	private int[] elementData;
	private int size;
	private boolean unique;
	public static final int DEFAULT_CAPACITY = 10;

	/**
	 * Create SortedIntList with default capacity.
	 */
	public SortedIntList() {
		this(DEFAULT_CAPACITY);
	}

	/** 
	 * @param unique, the condition for repeated element if false.
	 */
	public SortedIntList(boolean unique) {
		this.unique = unique;
		elementData = new int[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * @param capacity, capacity of the list
	 * pre: capacity >0 (throw IllegalArgumentExpection if not)
	 * post: Create an integer list with given capacity. 
	 */
	public SortedIntList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		elementData = new int[capacity];
		size = 0;
	}

	/**
	 * @param unique, the condition for repeated element if false.
	 * @param capacity, capacity of the list
	 * pre: capacity >= 0 (throw IllegalArgumentExpection if not)
	 * post: Create an integer list with given capacity and condition of uniqueness.
	 */
	public SortedIntList(boolean unique, int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.unique = unique;
		elementData = new int[capacity];
		size = 0;
	}

	/**
	 * @param value, the value from user.
	 * pre: size < capacity (throws IllegalStateException if not).
	 * post: Add value to the list sorted.
	 */
	public void add(int value) {
		checkCapacity(size + 1);
		if (indexOf(value) == -1 || !unique) {
			if (size == 0) {
				elementData[0] = value;
				size++;
			} else if (size == 1) {
				if (elementData[0] > value) {
					elementData[1] = elementData[0];
					elementData[0] = value;
				} else {
					elementData[1] = value;
				}
				size++;
			} else if (size > 1) {
				int index = getInd(value);
				add(index, value);
				size++;
			}
		}
	}

	/**
	 * @param value, the value from user.
	 * pre: size > 1.
	 * post: return index of the value 
	 */
	private int getInd(int value) {
		int first = 0;
		int last = size - 1;
		int index = -1;

		while (first <= last) {
			int mid = (last - first) / 2 + first;
			if (elementData[mid] == value) {
				return mid;
			} else if (elementData[mid] < value) {
				index = mid + 1;
				first = mid + 1;
			} else {
				index = mid;
				last = mid - 1;
			}
		}
		return index;
	}

	/**
	 * @param index, index of the value.
	 * @param value, the value from user.
	 * pre: index > 0 || index < size (throw IndexOutOfBoundsException either way).
	 * post: add value in the given value.
	 */
	private void add(int index, int value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		checkCapacity(size);
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = value;

	}

	/** 
	 * @param index, index of the value
	 * pre: size > 1, index < size (if not, throw IndexOutOfBoundsException).
	 * post: remove the value of the given index.
	 */
	public void remove(int index) {
		checkIndex(index);
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
	}

	/** 
	 * @param index, index of the value.
	 * pre: index < size (if not, throw IndexOutOfBoundsException).
	 * post: return value of given index in the list.
	 */
	public int get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	/**
	 * @return the size, size of the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * @param size, set the size 
	 */
	public void size(int size) {
		this.size = size;
	}

	/**
	 * @return boolean condition. 
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param value, given value
	 * @return index of the given value.
	 */
	public int indexOf(int value) {
		int in = -1;
		int index = firstIndex(value); //first index that use for count.
		if (index >= 0) {
			in = index;
		}
		return in;
	}

	/**
	 * @return the maximum value in the list.
	 */
	public int max() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return elementData[size - 1];
	}

	/**
	 * @return the minimum value in the list.
	 */
	public int min() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return elementData[0];
	}

	/**
	 * @param value, the value from user.
	 * @return occurrence value of the value in the list. 
	 */
	public int count(int value) {
		if (indexOf(value)==-1) {
			return 0;
		}else {
		int start = firstIndex(value);
		int end = lastIndex(value);
		return end - start + 1;
		}
	}

	/**
	 * @param value, the value from user.
	 * @return start, the first index of value that occurs. 
	 * post: size > 1. 
	 */
	private int firstIndex(int value) {
		int first = 0;
		int last = size - 1;
		int start = -1;

		while (first <= last) {
			int mid = (last - first) / 2 + first;
			if (elementData[mid] == value) {
				start = mid;
				last = mid - 1;
			} else if (elementData[mid] < value) {
				first = mid + 1;
			} else {
				last = mid - 1;
			}
		}
		return start;
	}

	/**
	 * @param value, the value from user.
	 * @return last, the last value that occur in the list.
	 */
	private int lastIndex(int value) {
		int first = 0;
		int last = size - 1;
		int start = -1;

		while (first <= last) {
			int mid = (last - first) / 2 + first;
			if (elementData[mid] == value) {
				start = mid;
				first = mid + 1;
			} else if (elementData[mid] < value) {
				first = mid + 1;
			} else {
				last = mid - 1;
			}
		}
		return start;
	}

	/**
	 * @return the unique, the condition for repeated element if false.
	 */
	public boolean getUnique() {
		return unique;
	}

	/**
	 * @param unique set the unique
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
		if (unique == true) {
			for (int i= size-1; i> 0; i--) {
				if (count(get(i))>1) {
					remove(indexOf(get(i)));
				}
				
			}
		}
		
	}

	/**
	 * @param index, index of the value
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index: " + index);
		}
	}

	/**
	 * @param capacity, capacity of the list
	 */
	private void checkCapacity(int capacity) {
		if (capacity > elementData.length) {
			throw new IllegalStateException("Exceed the capactiy.");
		}
	}

	/**
	 * post: print out the list in format.
	 */
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		String result = "[" + elementData[0];
		for (int i = 1; i < size; i++) {
			result = result + ", " + elementData[i];
		}
		return result + "]";
	}

}
