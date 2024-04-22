package Tools.HelpClasses;

public class TripleData<T, V, M> {

    public T first;
    public V second;
    public M weight;

    public TripleData(T first, V second, M weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "\n(" + first + ", " + second + ", " + weight + ")";
    }
}
