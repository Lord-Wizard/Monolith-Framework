package math;


public class mLine
{
	public mVector v1;
	public mVector v2;
	
	// CONSTRUCTOR
	public mLine()
	{
		this(
			new mVector(),
			new mVector()
		);
	}
	public mLine(mVector v1, mVector v2)
	{
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public mVector toVector()
	{
		return v2.substract(v1);
	}
	public mFunction toFunction()
	{
		return new mFunction(); // TODO
	}
	
	//===================================================
	// OPERATIONS
	//===================================================
	public double distanceToPoint(mVector p)
	{
		mVector v = v1.substract(p);
		return Math.sin(p.getAngle() - getAngle()) * v.getLength();
	}
	
	public double getSlope()
	{
		return (v2.y - v1.y) / (v2.x - v1.x);
	}
	/**
	 * Returns the angle of the line in radians
	 * @return
	 */
	public double getAngle()
	{
		if ((v2.x - v1.x) < 0.0D)
		{
			return Math.atan(getSlope()) + Math.PI;
		}
		
		return Math.atan(getSlope());
	}

	public double getdX()
	{
		return v2.x - v1.x;
	}
	public double getdY()
	{
		return v2.y - v1.y;
	}
}