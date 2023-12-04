package Lab15_17;


import Tasks.Task1;
import Tasks.Task2;

import java.util.*;

import static Tasks.Task2.NUMBERS;

public class Tree {

    public static void main(String[] args) { //8 (3 (1, 6 (4,7)), 10 (, 14(13,)))
        Tree tree = new Tree(null);
        tree.readTree(tree, Task1.scanConsoleString());
        tree.inorderTraversal(tree);
        printTree(tree);
        Scanner scanner = new Scanner(System.in);
        int num = 9;
        while (num != 9) {
            System.out.println("Действия с деревом:");
            System.out.println("1. insert");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. print preorder");
            System.out.println("5. print inorder");
            System.out.println("6. print postorder");
            System.out.println("7. print with linear bracket view");
            System.out.println("8. print stack preorder");
            System.out.println("9. exit");
            num = scanner.nextInt();
            switch (num) {
                case 1:
                    System.out.println("Напишите число: ");
                    tree.add(tree, scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Напишитие число: ");
                    tree.delete()
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }

        }


    }

    private Tree left;
    private Tree right;
    private Integer value;
    private static final List<String> CALCULUS = new ArrayList<>(Arrays.asList("+", "-", "*", "/", ")", "(", ",", " "));


    public Tree(Integer value) {
        this.value = value;
    }

    public void add(Tree tree, Integer value) { // добавление элемента в дерево
        if (tree.value == null) {
            tree.value = value;
            return;
        }
        if (value < tree.value) {
            if (tree.left == null) {
                tree.left = new Tree(value);
            } else {
                tree.add(tree.left, value);
            }
        } else {
            if (tree.right == null) {
                tree.right = new Tree(value);
            } else
                tree.add(tree.right, value);
        }
    }

    public Tree search(Tree tree, Integer value) { // рекурсивный обход дерева
        if (tree == null)
            return null;
        if (tree.value.equals(value))
            return tree;
        return (value < tree.value) ? search(tree.left, value) : search(tree.right, value);
    }

    public void inorderTraversal(Tree tree) {
        if (tree == null)
            return;
        tree.inorderTraversal(tree.left);
        System.out.print(tree.value + " ");
        tree.inorderTraversal(tree.right);
    }

    public void preorderTraversal(Tree tree) {
        if (tree != null) {
            System.out.print(tree.value + " ");
            tree.preorderTraversal(tree.left);
            tree.preorderTraversal(tree.right);
        }
    }

    public void postorderTraversal(Tree tree) {
        if (tree != null) {
            tree.postorderTraversal(tree.left);
            tree.postorderTraversal(tree.right);
            System.out.print(tree.value + " ");
        }
    }

    public void stackPreorderTraversal(Tree tree) { /// нерекурсивный обход дерева
        Stack<Tree> stack = new Stack<>();
        while (tree != null || !stack.isEmpty()) {
            if (!stack.isEmpty())
                tree = stack.pop();
            while (tree != null) {
                System.out.println(tree.value + " ");
                if (tree.right != null)
                    stack.add(tree.right);
                tree = tree.left;
            }
        }
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

    public Tree delete(Tree tree, Integer value) {  // удаление
        if (tree == null)
            return null;
        else if (value < tree.value) {
            tree.left = delete(tree.left, value);
        } else if (value > tree.value) {
            tree.right = delete(tree.right, value);
        } else {
            if (tree.left == null || tree.right == null) {
                tree = (tree.left == null) ? tree.right : tree.left;
            } else {
                Tree maxLeft = getMax(tree.left);
                tree.value = maxLeft.value;
                tree.right = delete(tree.right, maxLeft.value);
            }
        }
        return tree;
    }

    public void readTree(Tree tree, List<String> expression) {
        StringBuilder builder = new StringBuilder();
        for (String item : expression) {
            if (NUMBERS.contains(item)) {
                builder.append(item);
            } else {
                if (!builder.toString().isEmpty())
                    this.add(tree, Integer.parseInt(String.valueOf(builder)));
                builder = new StringBuilder();
            }
        }
    }


    public static void printTree(Tree tree) {
        if (tree != null) {
            System.out.print(tree.value + " ");
            if ((tree.left != null) || (tree.right != null))
                System.out.print(" (");
            printTree(tree.left);
            if ((tree.left != null) || (tree.right != null))
                System.out.print(", ");
            printTree(tree.right);
            if (tree.right != null)
                System.out.print(")");
        }
    }


}



