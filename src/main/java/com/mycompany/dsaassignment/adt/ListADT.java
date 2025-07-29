package com.mycompany.dsaassignment.adt;

public interface ListADT<T> {
    void add(T element);                // Add element to end
    void add(int index, T element);     // Add element at index
    T get(int index);                   // Get element at index
    T set(int index, T element);        // Replace element at index
    T remove(int index);                // Remove element at index
    boolean remove(T element);          // Remove first occurrence
    int size();                         // Number of elements
    boolean isEmpty();                  // Is list empty?
    void clear();                       // Remove all elements
} 