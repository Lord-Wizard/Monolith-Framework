package math;

public class mMatrix
{
	public double[][] values;
	public int m;
	public int n;
	
	// CONSTRUCTOR
	public mMatrix()
	{
		this(0, 0);
	}
	/**
	 * Create a m-by-n matrix
	 * @param m - Number of rows
	 * @param n - Number of columns
	 */
	public mMatrix(int m, int n)
	{
		values = new double[m][n];
		this.m = m;
		this.n = n;
	}
	public mMatrix(double[][] matrixArray)
	{
		this.values = matrixArray;
		m = matrixArray.length;
		n = matrixArray[0].length;
	}
	
 	public String toString()
	{
		String returnString = "";
		
		for(int i = 0; i < values.length; i++)
		{
			returnString += "{";
			
			for(int j = 0; j < values[i].length; j++)
			{
				returnString += values[i][j];
				
				if(j != values[i].length - 1)
				{
					returnString += ",";
				}
			}
			
			returnString += "}\n";
		}
		
		return returnString;
	}
	
	public static void main(String[] args)
	{
		mMatrix m1 = new mMatrix(new double[][]
			{
				{},
				{},
				{}
			});
		
		System.out.println(m1.toString());
	}
	
	//===================================================
	// OPERATIONS
	//===================================================
	public mMatrix add(mMatrix matrix) throws RuntimeException
	{
		// Check if the matrices have the same dimensions
		if(m != matrix.m || n != matrix.n)
		{
			throw new RuntimeException("Not a square matrix");
		}
		
		mMatrix returnMatrix = new mMatrix();
		
		return returnMatrix;
	}
	
	public mMatrix multiplyWith(mMatrix matrix) throws RuntimeException
	{
		// Check if the first matrix's rows are equal to the second matrix's columns
		if(n != matrix.m)
		{
			throw new RuntimeException("Matrices don't match");
		}
		
		mMatrix returnMatrix = new mMatrix();
		
		return returnMatrix;
	}
	
	public mMatrix sweep()
	{
		return null;
	}
}