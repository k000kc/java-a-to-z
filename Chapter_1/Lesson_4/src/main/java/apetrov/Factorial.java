package apetrov;

**
* ����� ��� ���������� ����������
* @author Andrey
*/
public class Factorial{

	public int number;
	public int result = 1;

	/**
	* ����� ����������� ���������
	* @param number
	* @return result
	*/
	public int getFactorial(int number){
		for (int i = number; i > 0; i--){
			result = result*i;
		}
		return result;
	}
}