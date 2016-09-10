package apetrov;

/**
 * Класс определяет существует ли треугольник, определяет его площадь и
 * максимальную его сторону
 * @author Andrey
 * @since 08.09.2016
 */
public class Triangle {

	/** Точки */
	public Point a;
	public Point b;
	public Point c;
    
	/** Переменная определяющая существует ли треугольник */
	public boolean existTriangle = false;
    
	/** ������� */
	public double areaTriagle;

	public Triangle(Point a, Point b, Point c){
		this.a = a;
		this.b = b;
		this.c = c;
    	}
    
	/**
	* Существует ли треугольник?
	* @return true - треугольник существует, false - треугольник не существует
	*/
	public boolean exist(){
		double ab = a.distanceTo(b);
		double bc = b.distanceTo(c);
		double ca = c.distanceTo(a);
	        if ((ab + bc) > ca && (ab + ca) > bc && (bc + ca) > ab){
	        	this.existTriangle = true;
        	}else{
            		this.existTriangle = false;
        	}
        	return this.existTriangle;
    	}
    
	/**
	* Находим площадь треугольника
	* @return Площадь треугольника
	*/
	public double area(){
		double ab = a.distanceTo(b);
		double bc = b.distanceTo(c);
		double ca = c.distanceTo(a);
		if (exist()){
			double p = (ab + bc + ca) / 2;
		        this.areaTriagle = Math.round(Math.sqrt(p * (p - ab) * (p - bc) * (p - ca)));
		}else{
		        System.out.println("Triangle does not exist");
		        this.areaTriagle = 0;
		}
		return this.areaTriagle;
	}
}
