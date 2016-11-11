package ru.apetrov;

/**
* Класс для определения самой длинной стороны треугольника
* @author Andrey
*/
public class MaxTriangleSide {

    /**
    * Определяем самую длинную сторону треугольника
    * @param args 
    */   
    public double maxSide(double ab, double bc, double ca){
        return Math.max(ab, Math.max(bc, ca));
    } 
}
