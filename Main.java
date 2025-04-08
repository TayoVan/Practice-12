import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "catsFile.txt";

        System.out.println("Студентка іменинниця старалася!");
//ЗА КОТИКІІІВ!!!
        while (true) {
            System.out.println("\n=== МЕНЮ від котиків ===");
            System.out.println("1. Котики додають рядки");
            System.out.println("2. Котики показують весь файл");
            System.out.println("3. Котики читають діапазон рядків");
            System.out.println("4. Котики вставляють рядок у файл");
            System.out.println("0. Котики прощаються");
            System.out.print("Вибір для котиків: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addLinesToFile(fileName, scanner);
                case 2 -> printFileWithLineNumbers(fileName);
                case 3 -> {
                    System.out.print("Котики питають: з якого рядка почати? ");
                    int start = scanner.nextInt();
                    System.out.print("До якого рядка котики читають? ");
                    int end = scanner.nextInt();
                    scanner.nextLine();
                    printLinesInRange(fileName, start, end);
                }
                case 4 -> insertLineAtPosition(fileName, scanner);
                case 0 -> {
                    System.out.println("Котики кажуть: до зустрічі!");
                    return;
                }
                default -> System.out.println("Котики не розуміють цей варіант.");
            }
        }
    }
//котики котики і ще раз котики!!!
    public static void addLinesToFile(String fileName, Scanner scanner) {
        System.out.print("Скільки рядків котики мають додати?(раби коду...) ");
        int linesCount = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (int i = 0; i < linesCount; i++) {
                System.out.print("Котики записують рядок " + (i + 1) + ": ");
                String line = scanner.nextLine();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Котики все успішно додали до файлу!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFileWithLineNumbers(String fileName) {
        System.out.println("Котики відкривають книжку-файл...");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Котик бачить рядок " + lineNumber++ + ": " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLinesInRange(String fileName, int start, int end) {
        System.out.println("Котики уважно читають з рядка(я чітать не вмію!) " + start + " до " + end);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber >= start && lineNumber <= end) {
                    System.out.println("Котики читають рядок (єто что букви?) " + lineNumber + ": " + line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertLineAtPosition(String fileName, Scanner scanner) {
        System.out.print("Котики просять ввести новий рядок: ");
        String newLine = scanner.nextLine();
        System.out.print("На яку позицію котики вставляють рядок (з 1): ");
        int position = scanner.nextInt();
        scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] lines = new String[1000];
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && count < lines.length) {
                lines[count++] = line;
            }

            if (position < 1 || position > count + 1) {
                System.out.println("Котики шоковані(дєвачкі я в шокє!): такого рядка не існує!");
                return;
            }

            for (int i = count; i >= position; i--) {
                lines[i] = lines[i - 1];
            }
            lines[position - 1] = newLine;
            count++;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (int i = 0; i < count; i++) {
                    writer.write(lines[i]);
                    writer.newLine();
                }
            }
// котиків забагато не буває!!!
            System.out.println("Котики потужно вставили рядок! Мяу!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}