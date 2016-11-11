package ru.apetrov;

/**
 * Класс определяющий расстояние между точками
 * @author Andrey
 * @since 08.09.2016
 */        
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
	this.x = x;
	this.y = y;
    }

    /**
     * Вычислим расстояние между точками
     * @param point
     * @return Расстояние между точками
     */
    public double distanceTo(Point point){
	return Math.sqrt(Math.pow((point.x - this.x), 2) + Math.pow((point.y - this.y), 2));
    }
}
