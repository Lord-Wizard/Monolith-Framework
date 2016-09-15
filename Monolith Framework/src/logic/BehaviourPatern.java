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

package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BehaviourPatern
{
	public String genome;
	public ArrayList<Condition> conditionList;
	
	// CONSTRUCTOR
	public BehaviourPatern(String genome) throws RuntimeException
	{
		this.genome = genome;
		conditionList = stringToConditionList(genome);
	}
	
	public static void main(String[] args)
	{
		BehaviourPatern bp = new BehaviourPatern("a<b{b<c{f1(a,b),f2(c)}f1(b,c)}");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("a: ");
		final int a = sc.nextInt();
		System.out.print("b: ");
		final int b = sc.nextInt();
		System.out.print("c: ");
		final int c = sc.nextInt();
		sc.close();
		
		System.out.println(bp.toFancyString());
		
		for(Condition condition : bp.conditionList)
		{
			condition.whenConditionIsMet();
		}
	}
	
	//===================================================
	// OPERATIONS
	//===================================================
	public ArrayList<Condition> stringToConditionList(String genome)
	{
		ArrayList<Condition> conditionList = new ArrayList<Condition>();
		char[] charArray = genome.toCharArray();
		
		String temp = "";
		
		for(Character c : charArray)
		{
			if(c.equals("{"))
			{
				conditionList.add(new Condition())
			}
			else if(c.equals("("))
			{
				
			}
		}
		
		return new ArrayList<Condition>();
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		
		for(Condition v : conditionList)
		{
			returnString += v.toString();
		}
		
		return returnString;
	}
	public String toFancyString()
	{
		String returnString = "";
		
		for(Condition v : conditionList)
		{
			returnString += v.toFancyString();
		}
		
		return returnString;
	}
	
	//===================================================
	// NESTED CLASSES
	//===================================================
	public static class Variable<T>
	{
		private String id;
		public T value;
		
		// CONSTRUCTOR
		public Variable(String variableIdentifier)
		{
			this.id = variableIdentifier;
		}
		
		@Override
		public String toString()
		{
			return id;
		}
		
		public T getValue()
		{
			return value;
		}
		public void setValue(T value)
		{
			this.value = value;
		}
	}
	
	public static class Condition
	{
		private boolean id;
		protected ArrayList<Condition> conditionList;
		protected ArrayList<Function> functionList;
		
		// CONSTRUCTOR
		public Condition(boolean conditionIdentifier, Condition[] conditions)
		{
			this.id = conditionIdentifier;
			
			conditionList = new ArrayList<Condition>();
			functionList = new ArrayList<Function>();
			
			for(Condition c : conditions)
			{
				conditionList.add(c);
			}
		}
		public Condition(boolean conditionIdentifier, Function[] functions)
		{
			this.id = conditionIdentifier;
			
			conditionList = new ArrayList<Condition>();
			functionList = new ArrayList<Function>();

			for(Function f : functions)
			{
				functionList.add(f);
			}
		}
		public Condition(boolean conditionIdentifier, Condition[] conditions, Function[] functions)
		{
			this.id = conditionIdentifier;
			
			conditionList = new ArrayList<Condition>();
			functionList = new ArrayList<Function>();
			
			for(Condition c : conditions)
			{
				conditionList.add(c);
			}
			for(Function f : functions)
			{
				functionList.add(f);
			}
		}
		
		@Override
		public String toString()
		{
			String returnString = id + ":";
			
			for(Condition c : conditionList)
			{
				returnString += c.toString();
				
				if(c != conditionList.get(conditionList.size() - 1))
				{
					returnString += ", ";
				}
			}
			for(Function f : functionList)
			{
				returnString += f.toString();
				
				if(f != functionList.get(functionList.size() - 1))
				{
					returnString += ", ";
				}
			}
			
			return returnString += ";";
		}
		public String toFancyString()
		{
			return toFancyString(0);
		}
		private String toFancyString(int level)
		{
			String returnString = "if(" + id + ")\n";
			
			for(int i = 0; i < level; i++)
			{
				returnString += "│  ";
			}
			
			returnString += "├\n";
			
			for(Condition c : conditionList)
			{
				for(int i = 0; i < level + 1; i++)
				{
					returnString += "│  ";
				}
				
				returnString += c.toFancyString(level + 1);
				
				if(c != conditionList.get(conditionList.size() - 1))
				{
					returnString += ", ";
				}
			}
			for(Function f : functionList)
			{
				for(int i = 0; i < level + 1; i++)
				{
					returnString += "│  ";
				}
				
				returnString += f.toString();
				
				if(f != functionList.get(functionList.size() - 1))
				{
					returnString += ",\n";
				}
			}
			
			returnString += "\n";
			
			for(int i = 0; i < level; i++)
			{
				returnString += "│  ";
			}
			
			return returnString += "└\n";
		}
		
		public void whenConditionIsMet()
		{
			if(id)
			{
				for(Condition c : conditionList)
				{
					c.whenConditionIsMet();
				}
				for(Function f : functionList)
				{
					f.perform();
				}
			}
		}
	}
	
	public static abstract class Function
	{
		private String id;
		private HashMap<String, ?> variableMap;
		
		// CONSTRUCTOR
		public Function(String id)
		{
			this.id = id;
		}
		public Function(String id, Variable<?>... variables)
		{
			this.id = id;
			for(Variable<?> t : variables)
			{
				variableMap.put(t.id, t.value);
			}
		}
		
		@Override
		public String toString()
		{
			String returnString = id + "(";
			
			for(int i = 0; i < variables.length; i++)
			{
				returnString += variables[i].toString();
				
				if(i != variables.length - 1)
				{
					returnString += ", ";
				}
			}
			
			returnString += ")";
			
			return returnString;
		}
		
		public abstract boolean perform();
	}
}