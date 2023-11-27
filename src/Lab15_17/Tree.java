package Lab15_17;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

    public static void main(String[] args) {
        Tree tree = new Tree(8, 0);
    }

    private int key;
    private Tree left;
    private Tree right;
    private Integer value;


    public Tree(Integer value, int key) {
        this.value = value;
        this.key = key;
    }

    public void add(Tree tree, int key, Integer value) { // добавление элемента в дерево
        if (key < tree.key) {
            if (tree.left == null)
                this.left = new Tree(value, key);
            else
                add(tree.left, key, value);
        } else {
            if (tree.right == null)
                this.right = new Tree(value, key);
            else
                add(tree.right, key, value);
        }
    }

    public Tree search(Tree tree, int key) {
        if (tree == null)
            return null;
        if (tree.key == key)
            return tree;
        return (key < tree.key) ? search(tree.left, key) : search(tree.right, key);
    }

    public Tree getMin(Tree tree) {
        if (tree == null)
            return null;
        if (tree.left == null)
            return tree;
        return getMin(tree.left); // самый нижний элемент находится слева, поэтому мы идем до упора влево
    }

    public Tree getMax(Tree tree) {
        if (tree == null)
            return null;
        if (tree.right == null)
            return tree;
        return getMax(tree.right); // самый нижний элемент находится справа, поэтому мы идем до упора вправо
    }

    public Tree delete(Tree tree, int key){
        if (tree == null)
            return null;
        //дописать
        return tree;
    }

}



