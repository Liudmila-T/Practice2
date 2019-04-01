package ua.nure.timoshenko.practice2;

interface ListIterator extends java.util.Iterator<Object> {
    // returns true if this list iterator has more
    // elements when traversing the list
    // in the reverse direction
    boolean hasPrevious();


    // returns the previous element in the list
    // and moves the cursor position backwards
    Object previous();


    // replaces the last element returned by next
    // or previous with the specified element
    void set(Object e);

    // removes from the list the last element
    // that was returned by next or previous
    void remove();
}