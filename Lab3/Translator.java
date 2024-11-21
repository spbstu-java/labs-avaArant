package Lab3_translator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Translator {
    private final Map<String, String> translateWithDictionary = new HashMap<>();
    private int translationLength;
    private final String dictPath;
    private final String inputPath;


    public Translator(String dictPath, String inputPath) {
        this.dictPath = dictPath;
        this.inputPath = inputPath;
    }


    private int calculateMaxTranslationLength() {
        int maxLength = 0;
        for (String key : translateWithDictionary.keySet()) {             //перебираем все значения ключей
            maxLength = Math.max(maxLength, key.split(" ").length); //находим максимальную длину ключа (количество слов)
        }
        return maxLength;
    }

    //Загружаем словарь в буфер
    public void loadDictionary() throws FileReadException, InvalidFileFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictPath))) {
            String line;
            while ((line = reader.readLine()) != null) {                              //построчно считываем данные пока не будет null
                String[] parts = line.split("\\|");                             //делим на части по символу |
                if (parts.length != 2) {                                               // проверка на корректность ввода( если мы получили не 2 части)
                    throw new InvalidFileFormatException("Invalid format in the dictionary file in line" + line);
                }
                this.translateWithDictionary.put(parts[0].trim().toLowerCase(), parts[1].trim().toLowerCase());//добавляем в хэшмап пары ключ/значение игнорируя регистр и удаляя все пробелы вначале и вконце ключа
            }
        } catch (IOException e) {                                                      //проверка на корректность нахождения файла
            throw new FileReadException("Error reading the dictionary file.");
        }
        this.translationLength = calculateMaxTranslationLength();                       //получаем максимальную длину ключа (количество слов)
    }

    //Загрузка исходного текста для перевода
    public String translate() throws FileReadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) { //попытка поместить текст в буфер
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {                              //построчно считываем данные пока не будет null
                text.append(line).append(" ");                                        // собираем считанный line в text добавляя после каждого line пробел
            }
            String[] sentences = text.toString().replace(", ", " ").toLowerCase().split("\\."); //заменим ",", игнорируем регистр и разделяем текст на предложения через "."
            StringBuilder translatedText = new StringBuilder();
            for (String sentence : sentences) {
                String translatedSentence = translateSentence(sentence.trim()); // по предложениям передаём текст для перевода, предварительно убрав все пробелы до и после предложения
                if (!translatedSentence.isEmpty()) {            //если строка не пустая
                    translatedText.append(translatedSentence).append(". ");   //добавляем предложение к конечному тексту
                }
            }
            return translatedText.toString();
        } catch (IOException e) {
            throw new FileReadException(inputPath);                            //проверка на ошибки чтения
        }

    }
        //Перевод загруженных предложений
        public String translateSentence(String sentence) {
            ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(sentence.split(" "))); //получим массив слов из предложения с разделителем пробел
            for (int i = 0; i < wordsList.size(); i++) {                                             // проходим циклом по индексам
                for (int length = Math.min(this.translationLength, wordsList.size() - i); length > 0; length--) {  // проходим циклом по от минимального из 2-х ( длина массива или количество слов в ключе) до 1
                    try {
                        String currentString = String.join(" ", wordsList.subList(i, i + length)); //соединяем в строку слова из массива по индексам с разделителем " "
                        if (this.translateWithDictionary.containsKey(currentString)) {         //если нашли такой ключ в словаре
                            wordsList.set(i, this.translateWithDictionary.get(currentString)); // ставим перевод на i позицию в массиве
                            for (int k = 1; k < length; k++) {
                                wordsList.remove(i + 1);                          //удаляем из массива слова с нужным индексом ( при удаленни все последующие индексы сдвигаются влево)
                            }
                            break;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
            }
            return String.join(" ", wordsList); // возврат текста из массива через пробел
        }
    }