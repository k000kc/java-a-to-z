package ru.apetrov.Task;

import java.util.Arrays;

/**
 * Created by Andrey on 07.06.2017.
 */
public class ComboArrays {

    /**
     * first array (sorted).
     */
    private int[] a;

    /**
     * second array.
     */
    private int[] b;

    /**
     * Constructor init arrays, and sorted first array.
     * @param a first array.
     * @param b second array.
     */
    public ComboArrays(int[] a, int[] b) {
        this.a = a;
        this.b = b;
        Arrays.sort(a);
    }

    /**
     * Search position for insert in array.
     * @param a array for search.
     * @param first first position.
     * @param last last position.
     * @param value value for insert.
     * @return position.
     */
    private int binarSearchPosition(int[] a, int first, int last, int value) {
        int mid = first + (last - first) / 2;
        if (first > last) {
            return mid;
        }
        if (value < a[mid]) {
            return this.binarSearchPosition(a, first, mid - 1, value);
        } else {
            return this.binarSearchPosition(a, mid + 1, last, value);
        }
    }

    /**
     * combo arrays.
     * @return combo array.
     */
    public int[] combo() {
        int size = a.length;
        a = Arrays.copyOf(a, a.length + b.length);
        int position;
        for (int i = 0; i < b.length; i++) {
            position = this.binarSearchPosition(a, 0, size - 1, b[i]);
            System.arraycopy(a, position, a, position + 1, size - position);
            a[position] = b[i];
            size++;
        }
        return this.a;
    }
}
