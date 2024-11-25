package it.unibo.inner.Impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private T[] elems;
    private int index;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements) {
        this(elements, (new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }
        }));
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter) {
        this.elems = elements;
        index = 0;
        setIterationPolicy(filter);
    }

    @Override
    public Iterator<T> iterator() {
        return  new InnerIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public class InnerIterator implements Iterator<T>{

        @Override
        public boolean hasNext() {
            try {
                while (!filter.test(elems[index])) {
                    index++;
                }
                if(elems[index] != null) 
                {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public T next() {
            if(this.hasNext()) {
                index++;
                return elems[index-1];
            }
            return null;
        }
    }
}
