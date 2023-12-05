package Lab15_17;


import Tasks.Task1;

import java.util.*;

import static Tasks.Task2.NUMBERS;

public class Tree {

    public static void main(String[] args) { //d
        Tree tree = new Tree(null);
        tree.readTree(tree, Task1.scanConsoleString());
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (num != 9) {
            System.out.println("\nДействия с деревом:");
            System.out.println("1. Добавление элемента");
            System.out.println("2. Удаление");
            System.out.println("3. Поиск");
            System.out.println("4. print preorder");
            System.out.println("5. print inorder");
            System.out.println("6. print postorder");
            System.out.println("7. Вывод в ленейно скобочной записи");
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
                    tree.delete(tree, scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Напишитие число: ");
                    Tree searchTree = tree.search(tree, scanner.nextInt());
                    if (searchTree != null)
                        System.out.println(searchTree.value);
                    else System.out.println("not found");
                    break;
                case 4:
                    tree.preorderTraversal(tree);
                    System.out.println();
                    break;
                case 5:
                    tree.inorderTraversal(tree);
                    System.out.println();
                    break;
                case 6:
                    tree.postorderTraversal(tree);
                    break;
                case 7:
                    tree.printTree(tree);
                    System.out.println();
                    break;
                case 8:
                    tree.stackPreorderTraversal(tree);
                    System.out.println();
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
        return (value < tree.value) ? tree.search(tree.left, value) : tree.search(tree.right, value);
    }

    public void inorderTraversal(Tree tree) { // центрированный обход дерева
        if (tree == null)
            return;
        tree.inorderTraversal(tree.left);
        System.out.print(tree.value + " ");
        tree.inorderTraversal(tree.right);
    }

    public void preorderTraversal(Tree tree) {  // прямой обход дерева
        if (tree != null) {
            System.out.print(tree.value + " ");
            tree.preorderTraversal(tree.left);
            tree.preorderTraversal(tree.right);
        }
    }

    public void postorderTraversal(Tree tree) { // обратный обход дерева
        if (tree != null) {
            tree.postorderTraversal(tree.left);
            tree.postorderTraversal(tree.right);
            System.out.print(tree.value + " ");
        }
    }

    public void stackPreorderTraversal(Tree tree) { /// Не рекурсивный прямой обход
        Stack<Tree> stack = new Stack<>();
        while (tree != null || !stack.isEmpty()) {
            if (!stack.isEmpty())
                tree = stack.pop();
            while (tree != null) {
                System.out.print(tree.value + " ");
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
            tree.left = tree.delete(tree.left, value);
        } else if (value > tree.value) {
            tree.right = tree.delete(tree.right, value);
        } else {
            if ((tree.left == null) || (tree.right == null)) {
                if (tree.left == null)
                    tree = tree.right;
                else
                    tree = tree.left;
            } else {
                Tree minTree = tree.getMin(tree.right);
                tree.value = minTree.value;
                tree.right = tree.delete(tree.right, minTree.value);
            }
        }
        return tree;
    }

    public void readTree(Tree tree, List<String> expression) {  // считываем дерево
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


    public void printTree(Tree tree) {  // выводим дерево в скобочной записи
        if (tree == null) {
            return;
        }
        System.out.print(tree.value);
        if ((tree.left != null) || (tree.right != null))
            System.out.print(" (");
        tree.printTree(tree.left);
        if ((tree.left != null) || (tree.right != null))
            System.out.print(", ");
        tree.printTree(tree.right);
        if ((tree.left != null) || tree.right != null)
            System.out.print(")");
    }
}





