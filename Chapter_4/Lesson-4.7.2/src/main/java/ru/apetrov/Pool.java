package ru.apetrov;

/**
 * Created by Andrey on 21.02.2017.
 */
public class Pool {

    private final int[][] values;

    public Pool(int[][] values) {
        this.values = values;
    }

    public int maxUnion() {
        int result = 0;
        int max = 0;
        for (int i = 1; i < this.values.length; i++) {
            for (int j = 1; j < this.values.length; j++) {
                max = count(i, j);
                if (max > result) {
                    result = max;
                }
            }
        }
        return result;
    }

    public int count(int i, int j) {
        int result = 0;
        if (i >= 0 && j >= 0 && i <= this.values.length && j <= this.values.length) {
            if (this.values[i][j] == 1) {
                result++;
                this.values[i][j] = 2;
                result += count(i, j++);
                result += count(i++, j);
                result += count(i, j--);
                result += count(i--, j);
            }
        }
        return result;
    }
}
