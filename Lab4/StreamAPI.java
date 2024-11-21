package Lab4;

import java.util.*;
import java.util.stream.Collectors;


public class StreamAPI {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(12, 10, 2, 10, 4, 2, 5, 12);
        List<String> strings = Arrays.asList("this", "function", "return", "then", "correct", "value");
        // List<String> strings = Arrays.asList();
        int[] intArray = {2, 3, 6, 1, 5, 8};
        int[] intArray0 = {1, 7, 11, 23};
        int[] intArray1 = {0};


        System.out.println("Среднее значение списка целых чисел: " + getAverageOfNumbers(numbers));
        System.out.println("Конвертированная строка: " + getConvertingToUpperCase(strings));
        System.out.println("Исходный список: " + numbers);
        System.out.println("Квадрат чисел, повторяющихся один раз: " + getUniqueElementsSquared(numbers));
        System.out.println("Последний элемент: " + getLastElement(strings));

        System.out.println("Сумма чётных числел: случай 1 = " + getSumOfNumbers(intArray) + " , " +
                "случай 2 = " + getSumOfNumbers(intArray0) + " ," + "случай 3 = " + getSumOfNumbers(intArray1));
        System.out.println("Преобразованные строки: " + getMapByFirstCharacter(strings));
    }


    //Метод, возвращающий среднее значение списка целых чисел
    public static double getAverageOfNumbers(List<Integer> numbers) throws IllegalArgumentException {
        return numbers.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                //  .orElse(0.0);
                .orElseThrow(() -> new IllegalArgumentException("Ошибка, список пуст!"));
    }

    //Метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»;
    public static List<String> getConvertingToUpperCase(List<String> strings) {
        return strings.stream()
                .map(str -> "_new_" + str.toUpperCase())
                .collect(Collectors.toList());
    }

    //Метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
    public static List<Integer> getUniqueElementsSquared(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> Collections.frequency(numbers, i) == 1)
                .map(i -> i * i)
                .collect(Collectors.toList());
    }

    //Метод, принимающий на вход коллекцию и возвращающий ее последний элемент
    // или кидающий исключение, если коллекция пуста

    public static <T> T getLastElement(Collection<T> collection) throws NoSuchElementException {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Ошибка, коллекция пустая!"));
    }

    //Метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел
    // или 0, если чётных чисел нет
    public static int getSumOfNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    //Метод, преобразовывающий все строки в списке в Map, где первый
    //символ – ключ, оставшиеся – значение;
    public static Map<Character, String> getMapByFirstCharacter(List<String> strings) {
        return strings.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (existing, replacement) -> existing));
    }
}

