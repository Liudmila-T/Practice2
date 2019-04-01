package ua.nure.timoshenko.practice2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl implements MyList, ListIterable {
    private static final byte DEFAULT_CAPACITY_ARRAY = 0;
    private Object[] arrayObj;
    private int i;

    public MyListImpl() {
        this.arrayObj = new Object[DEFAULT_CAPACITY_ARRAY];
    }


    @Override
    public void add(Object e) {
        if ((i + 1) - arrayObj.length > 0) {
            arrayObj = Arrays.copyOf(arrayObj, i + 1);
        }
        arrayObj[i] = e;
        i++;
    }

    @Override
    public void clear() {
        arrayObj = new Object[DEFAULT_CAPACITY_ARRAY];
        i = 0;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int j = 0; j < arrayObj.length; j++) {
                if (arrayObj[j] == null) {
                    System.arraycopy(arrayObj, j + 1, arrayObj, j, arrayObj.length - 1 - j);
                    arrayObj = Arrays.copyOf(arrayObj, arrayObj.length - 1);
                    return true;
                }
            }
        } else {
            for (int j = 0; j < arrayObj.length; j++) {
                if ((o.equals(arrayObj[j]))) {
                    arrayObj[j] = null;
                    System.arraycopy(arrayObj, j + 1, arrayObj, j, arrayObj.length - 1 - j);
                    arrayObj = Arrays.copyOf(arrayObj, arrayObj.length - 1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        if (i < arrayObj.length) {
            arrayObj = Arrays.copyOf(arrayObj, i);
        }
        return arrayObj.clone();
    }

    @Override
    public int size() {
        return arrayObj.length;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        if (o == null) {
            for (Object obj : arrayObj) {
                if (obj == null) {
                    result = true;
                }
            }
        } else {
            for (Object obj2 : arrayObj) {
                if (o.equals(obj2)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(MyList c) {
        boolean result = false;
        Object[] newArray = c.toArray();
        for (Object newArr : newArray) {
            for (Object myArray : arrayObj) {
                if (newArr.equals(myArray)) {
                    result = true;
                    break;
                } else {
                    result = false;
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(arrayObj.clone());
    }


    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        protected int counter = -1;

        // variable to check in the remove() method
        protected boolean check;

        // returns true if the iteration has more elements
        @Override
        public boolean hasNext() {
            return (counter < (arrayObj.length - 1));
        }

        // returns the next element in the iteration
        @Override
        public Object next() {
            if (hasNext()) {
                check = true;
                return arrayObj[++counter];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (check) {
                arrayObj[counter] = null;
                System.arraycopy(arrayObj, counter + 1, arrayObj, counter, arrayObj.length - 1 - counter);
                arrayObj = Arrays.copyOf(arrayObj, arrayObj.length - 1);
                counter--;
                check = false;
            } else {
                throw new IllegalStateException();
            }

        }
    }


    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        @Override
        public boolean hasPrevious() {
            return (counter >= 0);
        }

        @Override
        public Object previous() {
            if (hasPrevious()) {
                check = true;
                return arrayObj[counter--];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void set(Object e) {
            if (check) {
                arrayObj[counter++] = e;
                check = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}

