package cn.lollipop.designpattern.iterator;


class List<T> implements AbstractList<T> {
    private T[] values;

    public List(T[] values) {
        this.values = values;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(values);
    }
}