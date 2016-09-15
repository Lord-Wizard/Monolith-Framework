/*
 * Copyright (c) 2014, Sonny Ruff
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

public class mFunction
{
	private mFunction multiplier;
	private mFunction variable;
	private mFunction exponent;
	private mFunction adder;
	
	public double n;
	
	private FunctionType type;
	
	// Types
	public enum FunctionType
	{
		NUMBER,
		VARIABLE,
		FUNCTION,
		TRIGONOMETRIC_SIN,
		TRIGONOMETRIC_COS,
		TRIGONOMETRIC_TAN,
		TRIGONOMETRIC_ASIN,
		TRIGONOMETRIC_ACOS,
		TRIGONOMETRIC_ATAN
	}
	
	// CONSTRUCTORS
	public mFunction(double n)
	{
		this.n = n;
		
		type = FunctionType.NUMBER;
	}
	public mFunction()
	{
		this.multiplier	=	new mFunction(1);
		this.exponent	=	new mFunction(1);
		this.adder		=	new mFunction(0);
		
		type = FunctionType.VARIABLE;
	}
	public mFunction(mFunction multiplier, mFunction variable, mFunction exponent, mFunction adder)
	{
		this(
				multiplier,
				variable,
				exponent,
				adder,
				FunctionType.FUNCTION
			);
	}
	public mFunction(double multiplier, mFunction variable, double exponent, double adder)
	{
		this(
				new mFunction(multiplier),
				variable,
				new mFunction(exponent),
				new mFunction(adder),
				FunctionType.FUNCTION
			);
	}
	public mFunction(double multiplier, mFunction variable, double exponent, double adder, FunctionType type)
	{
		this(
				new mFunction(multiplier),
				variable,
				new mFunction(exponent),
				new mFunction(adder),
				type
			);
	}
	public mFunction(mFunction multiplier, mFunction variable, mFunction exponent, mFunction adder, FunctionType type)
	{
		this.multiplier = multiplier;
		this.variable = variable;
		this.exponent = exponent;
		this.adder = adder;
		
		this.type = type;
	}
	
	public String toString()
	{
		String functionReturn = "";
		
		switch (type)
		{
			case NUMBER: 
				functionReturn = functionReturn + this.n;
				break;
			case VARIABLE: 
				functionReturn = "x";
				break;
			case FUNCTION: 
				functionReturn = "(" + this.variable.toString() + ")";
				if (this.multiplier.n != 1.0D) {
					functionReturn = this.multiplier.toString() + " * " + functionReturn;
				}
				if (this.exponent.n != 1.0D) {
					functionReturn = functionReturn + "^(" + this.exponent.toString() + ")";
				}
				if (this.adder.n != 0.0D) {
					functionReturn = functionReturn + " + " + this.adder.toString();
				}
				break;
			case TRIGONOMETRIC_SIN: 
				functionReturn = "sin(" + this.variable.toString() + ")";
				if (this.multiplier.n != 1.0D) {
					functionReturn = this.multiplier.toString() + " * " + functionReturn;
				}
				if (this.exponent.n != 1.0D) {
					functionReturn = functionReturn + "^(" + this.exponent.toString() + ")";
				}
				if (this.adder.n != 0.0D) {
					functionReturn = functionReturn + " + " + this.adder.toString();
				}
				break;
			case TRIGONOMETRIC_COS: 
				functionReturn = this.variable.toString();
				if (this.multiplier.n != 1.0D) {
					functionReturn = this.multiplier.toString() + " * " + functionReturn;
				}
				if (this.exponent.n != 1.0D) {
					functionReturn = functionReturn + "^(" + this.exponent.toString() + ")";
				}
				if (this.adder.n != 0.0D) {
					functionReturn = functionReturn + " + " + this.adder.toString();
				}
				break;
			case TRIGONOMETRIC_TAN: 
				functionReturn = this.variable.toString();
				if (this.multiplier.n != 1.0D) {
					functionReturn = this.multiplier.toString() + " * " + functionReturn;
				}
				if (this.exponent.n != 1.0D) {
					functionReturn = functionReturn + "^(" + this.exponent.toString() + ")";
				}
				if (this.adder.n != 0.0D) {
					functionReturn = functionReturn + " + " + this.adder.toString();
				}
				break;
			case TRIGONOMETRIC_ACOS:
				break;
			case TRIGONOMETRIC_ASIN:
				break;
			case TRIGONOMETRIC_ATAN:
				break;
			default:
				break;
		}
		return functionReturn;
	}
	
	public double calc(double x)
	{
		switch (type)
		{
			case NUMBER:
				return n;
			case VARIABLE:
				return x;
			case FUNCTION:
				return multiplier.calc(x) * Math.pow(variable.calc(x), exponent.calc(x)) + adder.calc(x);
			case TRIGONOMETRIC_SIN: 
				return multiplier.calc(x) * Math.pow(Math.sin(variable.calc(x)), exponent.calc(x)) + adder.calc(x);
			case TRIGONOMETRIC_COS: 
				return multiplier.calc(x) * Math.pow(Math.cos(variable.calc(x)), exponent.calc(x)) + adder.calc(x);
			case TRIGONOMETRIC_TAN: 
				return multiplier.calc(x) * Math.pow(Math.tan(variable.calc(x)), exponent.calc(x)) + adder.calc(x);
			case TRIGONOMETRIC_ACOS:
			case TRIGONOMETRIC_ASIN:
			case TRIGONOMETRIC_ATAN:
			default:
				return Double.NaN;
		}
	}
	public double[] calc(double[] input)
	{
		double[] output = new double[input.length];
		
		for(int i = 0; i < input.length; i++)
		{
			output[i] = calc(input[i]);
		}
		
		return output;
	}
	
	public mFunction add(mFunction func)
	{
		return func;
	}
	
	public static void sum(int i, int m, int n)
	{
		int sum = 0;
		for (i = m; i <= n; i++)
		{
			int[] x = null;
			sum += x[i];
		}
	}
	
	//===================================================
	// GETTERS AND SETTERS
	//===================================================
	public mFunction getMultiplier()
	{
		return this.multiplier;
	}
	public void setMultiplier(mFunction multiplier)
	{
		this.multiplier = multiplier;
    	if (type == FunctionType.NUMBER)
		{
    		variable = new mFunction();
    		type = FunctionType.VARIABLE;
		}
	}
  
	public mFunction getVariable()
	{
		return this.variable;
	}
	public void setVariable(mFunction variable)
	{
		this.variable = variable;
	}
  
	public mFunction getExponent()
	{
		return this.exponent;
	}
	public void setExponent(mFunction exponent)
	{
		this.exponent = exponent;
		
		if (type == FunctionType.NUMBER)
		{
			variable = new mFunction();
			type = FunctionType.VARIABLE;
		}
	}
  
	public mFunction getAdder()
	{
		return this.adder;
	}
	public void setAdder(mFunction adder)
	{
		this.adder = adder;
		
		if (type == FunctionType.NUMBER)
		{
    		variable = new mFunction();
      		type = FunctionType.VARIABLE;
		}
	}
	
	// MAIN
	public static void main(String[] args)
	{
		mFunction f1 = new mFunction(5);	// Number
		mFunction f2 = new mFunction();		// Variable
		mFunction f3 = new mFunction(		// Function
				5,
				new mFunction(),
				2,
				4
			);
		mFunction f4 = new mFunction();
		mFunction f5 = new mFunction();
		
		System.out.println("f(x) = " + f1.toString());
		System.out.println("f(x) = " + f2.toString());
		System.out.println("f(x) = " + f3.toString());
	}
}