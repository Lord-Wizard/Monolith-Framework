package gameplay;

public class FPSCounter {

	private long startTime;
	private long endTime;
	private long deltaTime;
	
	public void startCounter()
	{
		startTime = System.nanoTime();
	}
	
	public void endCounter()
	{
		endTime = System.nanoTime();
		deltaTime = endTime - startTime;
	}
	
	public long getDeltaTime()
	{
		return deltaTime;
	}
	public void setDeltaTime(long deltaTime)
	{
		this.deltaTime = deltaTime;
	}
}