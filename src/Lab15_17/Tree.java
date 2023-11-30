package Lab15_17;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

    public static void main(String[] args) {
        Tree tree = new Tree(8);
    }

    private Tree left;
    private Tree right;
    private Integer value;


    public Tree(Integer value) {
        this.value = value;
    }

    public void add(Tree tree, Integer value) { // добавление элемента в дерево
        if (value < tree.value) {
            if (tree.left == null)
                this.left = new Tree(value);
            else
                add(tree.left, value);
        } else {
            if (tree.right == null)
                this.right = new Tree(value);
            else
                add(tree.right, value);
        }
    }

    public Tree search(Tree tree) {
        if (tree == null)
            return null;
        if (tree.value.equals(value))
            return tree;
        return (value < tree.value) ? search(tree.left) : search(tree.right);
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

    public Tree delete(Tree tree) {
        if (tree == null)
            return null;
        else if (value < tree.value) {
            tree.left = delete(tree.left);
        } else if (value > tree.value) {
            tree.right = delete(tree.right);
        } else {
            if (tree.left == null || tree.right == null) {
                tree = (tree.left == null) ? tree.right : tree.left;
            } else {
                Tree maxLeft = getMax(tree.left);
                tree.value = maxLeft.value;
                tree.right = delete(tree.right);
            }
        }
        return tree;
    }

    public static void printTree(Tree tree){
        if (tree != null){
            printTree(tree.left);
            System.out.println(tree.value);
            printTree(tree.right);
        }
    }

    public static void deleteTree(Tree tree){
        if (tree != null){

        }

    }

    public void copyTree(Tree tree){
        if (tree != null){

        }
    }

}



