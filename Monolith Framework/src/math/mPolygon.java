package math;

public class mPolygon
{
	public mVector[] points;
	
	// CONSTRUCTOR
	public mPolygon()
	{
		points = new mVector[0];
	}
	public mPolygon(mVector[] points)
	{
		this.points = points;
	}
	public mPolygon(double[] xArray, double[] yArray)
	{
		points = new mVector[xArray.length];
		
		for(int i = 0; i < points.length; i++)
		{
			points[i] = new mVector(xArray[i], yArray[i]);
		}
	}
	public mPolygon(double radius, int vertexCount)
	{
		points = new mVector[vertexCount];
		
		for(int i = 0; i < vertexCount; i++)
		{
			double theta = i * 2 * Math.PI / vertexCount;
			
			points[i] = new mVector(
								Math.cos(theta) * radius,
								Math.sin(theta) * radius
							);
		}
	}
	
	//===================================================
	// OPERATIONS
	//===================================================
	/*
	public void addPointBefore(int target, mVector v)
	{
		points.add(target, v);
	}
	
	public void divideEdges(int parts)
	{
		for(int i = points.length - 1; i >= 0; i--)
		{
			mVector p1 = points[i];
			mVector p2 = points[i != 0 ? i - 1 : points.length - 1];
			
			for(int j = 1; j < parts; j++)
			{
				mVector newPoint = new mVector
				(
					p1.x + (p2.x - p1.x) / parts * j,
					p1.y + (p2.y - p1.y) / parts * j
				);
				
				addPointBefore
				(
					i,
					newPoint
				);
			}
		}
		
		// Gave shit
		for(int i = points.size() - 1; i >= 0; i--)
		{
			for(int j = 1; j < parts - 1; j++)
			{
				addPointAfter
				(
					i,
					new mPoint
					(
						(points.get(i != points.size() - 1 ? i + 1 : 0).x - points.get(i).x) / parts * j,
						(points.get(i != points.size() - 1 ? i + 1 : 0).y - points.get(i).y) / parts * j
					)
				);
			}
		}
	}
	*/
}