package apetrov;

/**
* ����� ��� ����������� ����� ������� ������� ������������
* @author Andrey
*/
public class MaxTriangleSide {

    /**
    * ���������� ����� ������� ������� ������������
    * @param args 
    */   
    public double maxSide(double ab, double bc, double ca){
        return Math.max(ab, Math.max(bc, ca));
    } 
}