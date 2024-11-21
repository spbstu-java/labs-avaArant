package Lab3_translator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к словарю: ");
        String dictionary = scanner.nextLine();
        System.out.print("Введите путь к тексту для перевода: ");
        String input = scanner.nextLine();

        Translator translator =   new Translator (dictionary,input);
try {
            translator.loadDictionary();
            String ruText = translator.translate();
            System.out.println(ruText);
        } catch (FileReadException | InvalidFileFormatException e) {
            e.printStackTrace();
        }
    }
}

