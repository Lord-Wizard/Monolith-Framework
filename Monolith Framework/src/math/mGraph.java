package math;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mGraph<T, S>
{
	private ArrayList<mGraphListener<T, S>> listeners;
	
	public ArrayList<T> nodeList;
	public mTable<T, S> connectionMatrix;
	
	// CONSTRUCTOR
	public mGraph()
	{
		nodeList = new ArrayList<T>();
		connectionMatrix = new mTable<T, S>();
		
		listeners = new ArrayList<mGraphListener<T, S>>();
	}
	public mGraph(mTable<T, S> connectionMatrix) throws RuntimeException
	{
		if(connectionMatrix.getRowCount() != connectionMatrix.getColumnCount())
		{
			throw new RuntimeException("Matrix isn't square");
		}
		
		nodeList = new ArrayList<T>();
		this.connectionMatrix = connectionMatrix;
	}
	
	public static void main(String[] args)
	{
		new Thread()
		{
			public void run()
			{
				// Window stuff ------------------------------------- start --
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.setSize(800, 650);
				frame.setResizable(true);
				frame.setLocationRelativeTo(null);
				
				JPanel panel = new JPanel()
				{
					private static final long serialVersionUID = -7306285579182097537L;
					
					mGraph<Integer, Integer> g1;
					
					public JPanel init()
					{
						try
						{
							g1 = new mGraph<Integer, Integer>(
								new mTable<Integer, Integer>(
									new Integer[][]
									{
										{0, 1, 0, 0, 1},
										{1, 0, 1, 0, 0},
										{0, 1, 0, 1, 0},
										{0, 0, 1, 0, 1},
										{1, 0, 0, 1, 0}
									}
								)
							);
						}
						catch(Exception e)
						{
							e.printStackTrace();
							System.exit(1);
						}
						
						addMouseListener(
							new MouseListener()
							{
								public void mouseClicked(MouseEvent e)
								{
									
								}
								public void mouseEntered(MouseEvent e)
								{
									
								}
								public void mouseExited(MouseEvent e)
								{
									
								}
								public void mousePressed(MouseEvent e)
								{
									
								}
								public void mouseReleased(MouseEvent e)
								{
									
								}
							}
						);
						addKeyListener(
							new KeyListener()
							{
								public void keyPressed(KeyEvent e)
								{
									
								}
								public void keyReleased(KeyEvent e)
								{
									
								}
								public void keyTyped(KeyEvent e)
								{
									
								}
							}
						);
						
						return this;
					}
					
					public void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						setBackground(Color.BLACK);
						
						g.setColor(Color.WHITE);
//						g1.draw(g, 10, 10, 200, 200);
					}
				}.init();
				
				frame.add(panel);
				
				frame.setVisible(true);
				// Window stuff ------------------------------------- end ----
				
				boolean isRunning = true;
				
				// THE LOOP
				while(isRunning)
				{
					panel.repaint();
				}
				
				System.exit(0);
			}
		}.start();
	}

	//===================================================
	// OPERATIONS
	//===================================================
	public void addGraphListener(mGraphListener<T, S> listener)
	{
		listeners.add(listener);
	}
	
	//===================================================
	// GETTERS AND SETTERS
	//===================================================
	public void addKey(T t)
	{
		nodeList.add(t);
		connectionMatrix.addColumn(t);
		connectionMatrix.addRow(t);
		
		for(mGraphListener<T, S> l : listeners)
		{
			l.keyAdded(t);
		}
	}
	public void removeKey(T t)
	{
		nodeList.remove(t);
		
		for(mGraphListener<T, S> l : listeners)
		{
			l.keyRemoved(t);
		}
	}
	public void addEntry(S s, T t1, T t2)
	{
		if(connectionMatrix.get(t1, t2) == null)
		{
			connectionMatrix.set(t1, t2, s);
		}
		
		for(mGraphListener<T, S> l : listeners)
		{
			l.entryAdded(s, t1, t2);
		}
	}
	public void removeEntry(T t1, T t2)
	{
		S removedEntry = connectionMatrix.get(t1, t2);
		
		connectionMatrix.set(t1, t2, null);
		
		for(mGraphListener<T, S> l : listeners)
		{
			l.entryRemoved(removedEntry, t1, t2);
		}
	}
	
	//===================================================
	// NESTED CLASSES
	//===================================================
	public static interface mGraphListener<T, S>
	{
		public void keyAdded(T t);
		public void keyRemoved(T t);
		public void entryAdded(S s, T t1, T t2);
		public void entryRemoved(S s, T t1, T t2);
	}
}