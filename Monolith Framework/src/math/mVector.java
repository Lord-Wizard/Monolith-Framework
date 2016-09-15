package math;

import java.awt.Color;
import java.awt.Graphics;

public class mVector
{
	public double x, y, z;
	
	// CONSTRUCTOR
	public mVector()
	{
 		this(0, 0, 0);
	}
 	public mVector(double x, double y)
	{
 		this(x, y, 0);
	}
 	public mVector(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
 	
	public mMatrix toMatrix()
	{
		return new mMatrix(new double[][]{{x}, {y}, {z}});
	}
	
	// --debug--
	public void draw(Graphics g, mVector p)
	{
		g.setColor(Color.GREEN);
		g.drawLine(
				(int)p.x,
				(int)p.y,
				(int)(p.x + x),
				(int)(p.y + y)
			);
	}
	
	public static void main(String[] args)
	{
		mVector v1 = new mVector(10, 20);
	}
	
	//===================================================
	// GETTERS & SETTERS
	//===================================================
	public double getSlope()
	{
		return y / x;
	}
	public double getAngle()
	{
		if (x < 0.0D)
		{
			return Math.atan(getSlope()) + Math.PI;
		}
		
		return Math.atan(getSlope());
	}
	
	public double getLength()
	{
		return Math.sqrt(x*x + y*y);
	}
	
	public double getDistanceTo(mVector v)
	{
		return v.substract(this).getLength();
	}
	
	//===================================================
	// HEAVY MATH
	//===================================================
	public void relocate(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void transform(mVector v)
	{
		transform(v.x, v.y, 0);
	}
	public void transform(double x, double y)
	{
		transform(x, y, 0);
	}
	public void transform(double x, double y, double z)
	{
	    this.x += x;
	    this.y += y;
	    this.z += z;
	}
	
	public mVector add(mVector v)
	{
	    mVector v2 = new mVector(x + v.x, y + v.y, z + v.z);
	    
	    return v2;
	}
	public mVector add(double x, double y)
	{
	    mVector v2 = new mVector(this.x + x, this.y + y);
	    
	    return v2;
	}
	public mVector substract(mVector v)
	{
	    mVector v2 = new mVector(x - v.x, y - v.y, z - v.z);
	    
	    return v2;
	}
	public mVector substract(double x, double y)
	{
	    mVector v2 = new mVector(this.x - x, this.y - y);
	    
	    return v2;
	}
	
	public mVector scale(double scaleFactor)
	{
		return new mVector(x * scaleFactor, y * scaleFactor, z * scaleFactor);
	}
	
	public mVector rotate(double degrees)
	{
		return rotate(degrees, degrees, 0.0D);
	}
	public mVector rotate(double degreesX, double degreesY, double degreesZ)
	{
	    double tmpX = getLength() * Math.cos(getAngle() + Math.toRadians(degreesX));
	    double tmpY = getLength() * Math.sin(getAngle() + Math.toRadians(degreesY));
	    
	    return new mVector(tmpX, tmpY);
	}
	
	public mVector normalize()
	{
		mVector v = this;
		
	    if (getLength() != 0.0D)
	    {
	    	v.x /= getLength();
			v.y /= getLength();
			v.z /= getLength();
	    }
	    
	    return v;
	}
	
	public mVector projectOn(mVector v1)
	{
		return scale(dotProduct(this, v1) / dotProduct(this, this));
	}
  
	public mVector perpendicular()
	{
		return new mVector(-this.y, this.x);
	}
  
	public static double dotProduct(mVector v1, mVector v2)
	{
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}
	public static mVector crossProduct(mVector v1, mVector v2)
	{
		return null; // TODO
	}
}