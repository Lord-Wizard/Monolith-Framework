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

import java.util.Scanner;

import logic.BehaviourPatern.Condition;
import logic.BehaviourPatern.Function;

public class BehaviourPaternFactory
{
	public static void main(String[] args)
	{
		BehaviourPatern bp = new BehaviourPatern();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("a: ");
		final int a = sc.nextInt();
		System.out.print("b: ");
		final int b = sc.nextInt();
		System.out.print("c: ");
		final int c = sc.nextInt();
		sc.close();
		
		bp.conditionList.add(
			bp.new Condition(
				"a < b",
				new Condition[]
				{
					bp.new Condition(
						"b < c",
						new Function[]
						{
							bp.new Function(
								"f1.1",
								bp.new Variable<Boolean>("b")
							)
							{
								public boolean perform()
								{
									System.out.println("I'm function1.1");
									return true;
								}
							}
						}
					)
					{
						public boolean isConditionMet()
						{
							if(b < c)
							{
								return true;
							}
							return false;
						}
					}
				},
				new Function[]
				{
					bp.new Function(
						"f1",
						bp.new Variable<Integer>("m"),
						bp.new Variable<Integer>("n")
					)
					{
						public boolean perform()
						{
							System.out.println("I'm function1");
							return false;
						}
					},
					bp.new Function(
						"f2",
						bp.new Variable<Integer>("p"),
						bp.new Variable<Integer>("q")
					)
					{
						public boolean perform()
						{
							System.out.println("I'm function2");
							return false;
						}
					}
				}
			)
			{
				public boolean isConditionMet()
				{
					if(a < b)
					{
						return true;
					}
					return false;
				}
			}
		);
		
		System.out.println(bp.toFancyString());
		
		for(Condition condition : bp.conditionList)
		{
			condition.whenConditionIsMet();
		}
	}
}