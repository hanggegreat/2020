package cn.lollipop.designpattern.iterator;

/**
 * 迭代器模式：提供一种方法访问一个容器对象中各个元素，而又不需暴露该对象的内部细节。
 * 优点：
 * <p>
 * 支持多种方式遍历一个容器对象
 * 隐蔽了对象的内部细节
 * 最佳实践：
 * <p>
 * JDK中的Iterator类运用了迭代器模式。
 *
 * @param <T>
 */
class ListIterator<T> implements Iterator<T> {
    private T[] values;
    private int position;

    ListIterator(T[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return position < values.length;
    }

    @Override
    public T next() {
        return values[position++];
    }
}