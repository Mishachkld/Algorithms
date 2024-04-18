package Tools.HelpClasses;

public class TripleData<T, V, M> {

    public T first;
    public V second;
    public M weight;

    public TripleData(T t, V v, M m) {
        this.first = t;
        this.second = v;
        this.weight = m;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + weight + ")";
    }
}
