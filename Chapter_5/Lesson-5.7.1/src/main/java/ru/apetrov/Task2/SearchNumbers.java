package ru.apetrov.Task2;

/**
 * Created by Andrey on 11.06.2017.
 */
public class SearchNumbers {

    /**
     * array.
     */
    private int[] array;

    /**
     * Constuctor.
     * @param array array.
     */
    public SearchNumbers(int[] array) {
        this.array = array;
    }

    /**
     * Missing items search.
     * @return result.
     */
    public int[] search() {
        int[] result = new int[2];
        int j = 0;
        int k = 1;
        for (int i = 0; i < this.array.length; i++) {
            if ((array[i] - i) > k) {
                result[j++] = array[i] - 1;
                k++;
            }
        }
        return result;
    }
}
