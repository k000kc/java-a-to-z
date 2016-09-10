package apetrov;

public class MaxTriangleSide {
   
    public double maxSide(double ab, double bc, double ca){
        return Math.max(ab, Math.max(bc, ca));
    } 
}