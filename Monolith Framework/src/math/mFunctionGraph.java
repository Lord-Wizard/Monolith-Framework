/*
 * Copyright (c) 2015, Sonny Ruff
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of Sonny Ruff nor the names
 *    of its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

package math;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mFunctionGraph
{
	public mFunction function;
	public double[] xValues;
	public double[] yValues;
	
	// CONSTRUCTOR
	public mFunctionGraph(mFunction function, double[] xValues, double[] yValues)
	{
		this.function = function;
		this.xValues = xValues;
		this.yValues = yValues;
	}
	
	public void draw(
			Graphics g,
			int x, int y,
			int w, int h,
			double minX, double maxX,
			double minY, double maxY,
			double axisStepX, double axisStepY)
	{
		int xAxisHeight = (int)(h * (maxY / (Math.abs(minY) + maxY)));
		int yAxisHeight = (int)(w * (Math.abs(minX) / (Math.abs(minX) + maxX)));
		
		// Draw axes
		if(axisStepX > 0 && axisStepY > 0)
		{
			g.setColor(Color.WHITE);
			
			// Horizontal axis
			g.drawLine(
					x,
					y + xAxisHeight,
					x + w,
					y + xAxisHeight
				);
			// Vertical axis
			g.drawLine(
					x + yAxisHeight,
					y,
					x + yAxisHeight,
					y + h
				);
			
			// TODO Draw horizontal line markings
			int horizontalMarkerCount = (int) ((minX - maxX) / axisStepX);
			
			for(int i = 0; i < horizontalMarkerCount; i++)
			{
				
			}
				
			// TODO Draw vertical line markings
		}
		
		double xRatio = w / (maxX - minX);
		double yRatio = h / (maxY - minY);
		
		// Draw graph
		for(int i = 0; i < xValues.length - 1; i++)
		{
			g.drawLine(
					x + yAxisHeight + (int)(xValues[i] * xRatio),
					y + xAxisHeight - (int)(yValues[i] * yRatio),
					x + yAxisHeight + (int)(xValues[i + 1] * xRatio),
					y + xAxisHeight - (int)(yValues[i + 1] * yRatio)
				);
		}
	}
	
	public static void main(String[] args)
	{
		new Thread()
		{
			public void run()
			{
				final mFunction f1 = new mFunction(
						1,
						new mFunction(),
						2,
						0
					);
				double[] xValues = mMath.getValuesBetween(-10, 10, 20);
				double[] yValues = f1.calc(xValues);
				
				final mFunctionGraph fg1 = new mFunctionGraph(f1, xValues, yValues);
				
				
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
					
					public void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						setBackground(Color.BLACK);
						
						g.setColor(Color.WHITE);
						g.drawString("f(x) = " + f1.toString(), 10, 10);
						
						fg1.draw(g, 10, 30, 500, 400, -5, 5, -10, 10, 1, 1);
					}
				};
				
				panel.addMouseListener(
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
				panel.addKeyListener(
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
}