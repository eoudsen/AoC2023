package days.day22;

import java.util.Arrays;
import java.util.function.Supplier;

public class Builder<E> {
    private E e;
    private E newE;
    private Class<E> c;
    private Supplier<E> s;

    public Builder(Supplier<E> s) {
        this.s = s;
        this.e = s.get();
        this.newE = s.get();
    }

    public E getFromClass(Object... o) {
        try {
            return (E) Arrays.stream(c.getConstructors()).filter(e -> e.getParameterCount() == o.length).findAny().orElseThrow().newInstance(o);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public E get() {
        return e;
    }

    public E getNew() {
        return newE;
    }

    public void refresh() {
        e = newE;
        if (s != null) {
            newE = s.get();
        } else if (c != null) {
            newE = getFromClass();
        }
    }

    public void setNew(E e) {
        newE = e;
    }
}
