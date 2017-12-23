package ru.apetrov;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 11.12.2017.
 */
public class Main {

    /**
     * Стартуем программу при помощи исполнителя (запускается на выполнение каждые 10 секунд по умолчанию)
     */
    private void start() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new JsoupParser(), 0, 10, TimeUnit.SECONDS);
    }

    /**
     * main
     * @param args args.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
