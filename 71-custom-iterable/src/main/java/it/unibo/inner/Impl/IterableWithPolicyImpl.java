package it.unibo.inner.Impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private T[] elems;
    private int index;

    public IterableWithPolicyImpl(T[] elements) {
        this.elems = elements;
        index = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return  new InnerIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    public class InnerIterator implements Iterator<T>{

        @Override
        public boolean hasNext() {
            try {
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
