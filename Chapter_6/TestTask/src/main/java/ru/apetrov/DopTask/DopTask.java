package ru.apetrov.DopTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by Andrey on 18.10.2017.
 */
public class DopTask {

    /**
     * Переводим строки в массивы.
     * сортируем массивы по алфавиту.
     * сравниваем отсортированные массивы.
     * @param first первое слово.
     * @param second второе слово.
     * @return результат.
     */
    public boolean checkWord(String first, String second) {
        boolean result = true;
        if (first.length() != second.length()) {
            result = false;
        } else {
            char[] firstArray = first.toCharArray();
            char[] secondArray = second.toCharArray();
            Arrays.sort(firstArray);
            Arrays.sort(secondArray);
            if (!Arrays.equals(firstArray, secondArray)) {
                result = false;
            }
        }
        return  result;
    }

    /**
     * Переводим строки в массивы.
     * Добавляем первую строку в HashMap<Character, Integer>, если Character повторяется увеличиваем Integer + 1.
     * Проходим второй строкой по HashMap, если Character повторяется, уменьшаем Integer - 1, и если Integer = 0 удаляем соответствующий Character.
     * Если в конце HashMap isEmpty = true значит все буквы повторились.
     * @param first первое слово.
     * @param second второе слово.
     * @return результат.
     */
    public boolean checkWord1(String first, String second) {
        boolean result = false;
        Map<Character, Integer> hashMap = new HashMap<>();
        if (first.length() != second.length()) {
            result = false;
        } else {
            char[] firstArray = first.toCharArray();
            char[] secondArray = second.toCharArray();
            for (Character character : firstArray) {
                int i = 0;
                hashMap.putIfAbsent(character, i);
                hashMap.compute(character, new BiFunction<Character, Integer, Integer>() {
                    @Override
                    public Integer apply(Character character, Integer integer) {
                        integer++;
                        return integer;
                    }
                });
            }

            for (Character character : secondArray) {
                int i = 1;
                hashMap.computeIfPresent(character, new BiFunction<Character, Integer, Integer>() {
                    @Override
                    public Integer apply(Character character, Integer integer) {
                        integer--;
                        return integer;
                    }
                });
                hashMap.putIfAbsent(character, i++);

                if (hashMap.get(character) == 0) {
                    hashMap.remove(character);
                }
            }

            if (hashMap.isEmpty()) {
                result = true;
            }
        }
        return result;
    }

}
