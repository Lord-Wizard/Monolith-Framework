package math;

import java.awt.Color;
import java.awt.Graphics;

public class mVectorN
{
	public double[] components;
	
	// CONSTRUCTOR
 	public mVectorN(int dimensionCount)
	{
		components = new double[dimensionCount];
	}
 	public mVectorN(double... components)
 	{
 		this.components = components;
 	}
 	
	public mMatrix toMatrix()
	{
		mMatrix m = new mMatrix(components.length, 1);
		
		for(int i = 0; i < components.length; i++)
		{
			m.values[i][1] = components[i];
		}
		
		return m;
	}
	
	// --debug--
	public void draw(Graphics g, mVectorN p)
	{
		g.setColor(Color.GREEN);
		g.drawLine((int)p.components[0], (int)p.components[1], (int)(p.components[0] + components[0]), (int)(p.components[1] + components[1]));
	}
	
	public static void main(String[] args)
	{
		mVectorN v1 = new mVectorN(10, 20);
		mVectorN v2 = new mVectorN(10, 20);
	}
	
	//===================================================
	// GETTERS AND SETTERS
	//===================================================
	public double getSlope(int x, int y, int z)
	{
		return components[1] / components[0];
	}
	public double getAngle2D()
	{
		if (components[0] < 0.0D)
		{
			return Math.atan(getSlope2D()) + Math.PI;
		}
		
		return Math.atan(getSlope2D());
	}
	
	public double getLength()
	{
		double m = 0;
		
		for(int i = 0; i < components.length; i++)
		{
			m += components[i]*components[i];
		}
		
		return Math.sqrt(m);
	}
	
	//===================================================
	// HEAVY MATH
	//===================================================
	public void relocate(double[] p)
	{
		for(int i = 0; i < p.length; i++)
		{
			components[i] = p[i];
		}
	}
	
	public void transform(double[] p)
	{
		for(int i = 0; i < p.length; i++)
		{
			components[i] += p[i];
		}
	}
	
	public mVectorN add(mVectorN v)
	{
		mVectorN v2 = new mVectorN(components.length);
	    
		for(int i = 0; i < components.length; i++)
		{
			v2.components[i] = components[i] + v.components[i];
		}
	    
		return v2;
	}
	public mVectorN substract(mVectorN v)
	{
		mVectorN v2 = new mVectorN(components.length);
	    
		for(int i = 0; i < components.length; i++)
		{
			v2.components[i] = components[i] - v.components[i];
		}
	    
		return v2;
	}
	
	public mVectorN scale(double p)
	{
		mVectorN v2 = new mVectorN(components.length);
	    
		for(int i = 0; i < components.length; i++)
		{
			v2.components[i] = components[i] * p;
		}
	    
		return v2;
	}
	
	public mVectorN rotate(double degreesX, double degreesY, double degreesZ)
	{
	    double tmpX = getLength() * Math.cos(getAngle() + Math.toRadians(degreesX));
	    double tmpY = getLength() * Math.sin(getAngle() + Math.toRadians(degreesY));
	    
	    return new mVectorN(tmpX, tmpY);
	}
	
	public mVectorN normalize()
	{
		mVectorN v = this;
		
	    if (getLength() != 0.0D)
	    {
	    	v.x /= getLength();
			v.y /= getLength();
			v.z /= getLength();
	    }
	    
	    return v;
	}
	
	public mVectorN projectOn(mVector v1)
	{
		return scale(dotProduct(this, v1) / dotProduct(this, this));
	}
  
	public mVectorN perpendicular()
	{
		return new mVector(-this.y, this.x);
	}
  
	public static double dotProduct(mVector v1, mVector v2)
	{
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}
	public static mVectorN crossProduct(mVector v1, mVector v2)
	{
		return null; // TODO
	}
}