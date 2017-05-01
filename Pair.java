/**
 * File:	Pair.java
 * Purpose: Represents a pair of any two objects.
 * @author	The Rebels of Sutlin
 */

package kNearestNeighbors;

import java.io.Serializable;

/**
 * A class to model a pair of objects. It was written in a generic manner so as
 * to be able to be used with any type, but so far its only usage is to store
 * Territory/Int pairs.
 * 
 * 4/30/17
 * Woooo code reuse baby!
 * This was a class I made for the OOP class's final project.
 * 
 * @author barry
 *
 * @param <T> 
 * @param <I>
 */
public class Pair<V, T> implements Serializable {
	private V first;
	private T second;

	public Pair(V first, T second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns the first element of the pair.
	 * @return
	 */
	public V getFirst() {
		return first;
	}

	/**
	 * Returns the second element of the pair
	 * @return
	 */
	public T getSecond() {
		return second;
	}
}
