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

import logic.BehaviourPatern.Function;

public abstract class AbstractCreature
{
	private BehaviourPatern bp;
	private ArrayList<Function> functionList;
	
	// CONSTRUCTOR
	public AbstractCreature()
	{
		this(new BehaviourPatern());
	}
	public AbstractCreature(BehaviourPatern bp)
	{
		this.bp = bp;
		functionList = new ArrayList<Function>();
	}

	//===================================================
	// OPERATIONS
	//===================================================
	public void addCondition()
	{
		
	}
	public void addFunction(Function function)
	{
		functionList.add(function);
	}

	//===================================================
	// GETTERS AND SETTERS
	//===================================================
	public BehaviourPatern getBehaviourPatern()
	{
		return bp;
	}
	public void setBehaviourPatern(BehaviourPatern bp)
	{
		this.bp = bp;
	}
}