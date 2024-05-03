package org.example;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Определяем стартовый каталог
        Scanner in = new Scanner(System.in);
        System.out.print("Введите каталог: ");
        String startDirectoryPath = in.nextLine();
        if (startDirectoryPath.equals("")) {
            startDirectoryPath = ".";
        }

        if (args.length > 0) {
            startDirectoryPath = args[0];
        }
        File startDirectory = new File(startDirectoryPath);

        // Проверяем, существует ли стартовый каталог
        if (!startDirectory.exists() || !startDirectory.isDirectory()) {
            System.out.println("Указанный каталог не существует или не является директорией.");
            return;
        }

        // Создаем корневой узел для дерева каталогов
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(startDirectory.getName());
        buildDirectoryTree(root, startDirectory);

        // Выводим дерево каталогов на консоль
        printDirectoryTree(root, 0);
    }

    // Рекурсивный метод для построения дерева каталогов
    private static void buildDirectoryTree(DefaultMutableTreeNode node, File directory) {
        // Получаем список файлов и подкаталогов текущего каталога
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                // Создаем узел для каждого файла или подкаталога
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
                node.add(childNode);
                // Если текущий элемент является каталогом, рекурсивно строим его поддерево
                if (file.isDirectory()) {
                    buildDirectoryTree(childNode, file);
                }
            }
        }
    }

    // Метод для вывода дерева каталогов на консоль с отступами для обозначения уровней
    private static void printDirectoryTree(DefaultMutableTreeNode node, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  "); // Используем два пробела в качестве отступа для каждого уровня
        }
        System.out.println(indent.toString() + node.getUserObject());
        // Рекурсивно выводим дочерние узлы
        for (int i = 0; i < node.getChildCount(); i++) {
            printDirectoryTree((DefaultMutableTreeNode) node.getChildAt(i), depth + 1);
        }
    }
}