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

package logic;

import java.util.ArrayList;

// TODO Verander dat het voor ieder soort variabel gebruikt kan worden.
// of
// TODO Verander in een interface, zodat alleen variabelen met een waarde het kunnen gebruiken (BETER)
/**
 * Intransitive variables, usable in logic operation featuring transitive relations
 * @author Sonny Ruff
 * @version 3.0
 */
public class mInVar2 {
	
	public String id;
	
	private ArrayList<mInVar2> equalToList;
	private ArrayList<mInVar2> greaterThenList;
	private ArrayList<mInVar2> lessThenList;
	
	// Checking
	private static ArrayList<mInVar2> checkList;
	
	
	// CONSTRUCTOR
	public mInVar2(String id) {
		
		this.id = id;
		
		equalToList = new ArrayList<mInVar2>();
		greaterThenList = new ArrayList<mInVar2>();
		lessThenList = new ArrayList<mInVar2>();
	}
	
	public static void main(String[] args) {
		
		mInVar2 A = new mInVar2("A");
		mInVar2 B = new mInVar2("B");
		mInVar2 C = new mInVar2("C");
		mInVar2 X = new mInVar2("X");
		mInVar2 Y = new mInVar2("Y");
		mInVar2 Z = new mInVar2("Z");
		
		A.setGreaterThen(B);
		B.setGreaterThen(C);
		C.setGreaterThen(X);
		
		X.setGreaterThen(Y);
		X.setGreaterThen(Z);
		
		try
		{
			pln("=========== A > B?: " + A.isGreaterThen(B));
			pln("=========== A > X?: " + A.isGreaterThen(X));
			pln("=========== X < A?: " + X.isLessThen(A));
			
			pln("=========== Y = Z?: " + Y.isEqualTo(Z));
		}
		catch (UncertaintyException e)
		{
			e.printStackTrace();
		}
	}
	
	//===================================================
	// OPERATIONS
	//===================================================
	public void setEqualTo(mInVar2 iv) {
		
		if(!equalToList.contains(iv)) {
			
			pln(id + " = " + iv.id);
			
			equalToList.add(iv);
			iv.equalToList.add(this);
			
		} else {
			
			pln(id + " is already equal to " + iv.id);
		}
	}
	public void setGreaterThen(mInVar2 iv) {
		
		if(!greaterThenList.contains(iv)) {
			
			pln(id + " > " + iv.id);
			
			greaterThenList.add(iv);
			iv.lessThenList.add(this);
			
		} else {
			
			pln(id + " is already greater then " + iv.id);
		}
	}
	public void setLessThen(mInVar2 iv) {

		if(!lessThenList.contains(iv)) {
			
			pln(id + " < " + iv.id);
			
			lessThenList.add(iv);
			iv.greaterThenList.add(this);
			
		} else {
			
			pln(id + " is already less then " + iv.id);
		}
	}
	
	public void setNotEqualTo(mInVar2 iv) {
		
		setGreaterThen(iv);
		setLessThen(iv);
	}
	public void setNotGreaterThen(mInVar2 iv) {
		
		setEqualTo(iv);
		setLessThen(iv);
	}
	public void setNotLessThen(mInVar2 iv) {
		
		setEqualTo(iv);
		setGreaterThen(iv);
	}
	//----------------------------------------
	public boolean isEqualTo(mInVar2 ivTarget) throws UncertaintyException {

		pln(this.id + " = " + ivTarget.id + "?");
		
		// First test if both are the same object
		if(this == ivTarget) {
			
			pln("yes : " + this.id + " = " + ivTarget.id);
			return true;
		}
		// Check equalToList for the variable
		if(this.equalToList.contains(ivTarget)) {

			pln("yes : " + this.id + " = " + ivTarget.id);
			return true;
			
		}
		
		// If the checklist isn't used already, initialise it
		if(checkList == null) checkList = new ArrayList<mInVar2>();
		
		
		for(mInVar2 i : this.equalToList) {
			
			// Prevent looping
			if(!checkList.contains(i)) {
				
				pln("checking " + i.id);
				
				// i is being checked
				checkList.add(i);
				
				if(i.isEqualTo(ivTarget)) {

					pln("yes : " + this.id + " = " + ivTarget.id);
					
					checkList = null;
					return true;
				}
			}
		}

		pln("no : " + this.id + " != " + ivTarget.id);
		return false;
	}
	public boolean isGreaterThen(mInVar2 ivTarget) throws UncertaintyException {
		
		if(this.equalToList.contains(ivTarget))		pln("equalTo");
		if(this.greaterThenList.contains(ivTarget)) pln("greaterThen");
		if(this.lessThenList.contains(ivTarget))	pln("lessThen");
		
		pln(this.id + " > " + ivTarget.id + "?");
		
		// First test if both are the same object
		if(this == ivTarget) {
			
			pln("no : " + this.id + " = " + ivTarget.id);
			return false;
		}
		// Check equalToList for the variable
		if(this.greaterThenList.contains(ivTarget)) {

			pln("yes : " + this.id + " > " + ivTarget.id);
			return true;
			
		}
		
		// If the checklist isn't used already, initialise it
		if(checkList == null) checkList = new ArrayList<mInVar2>();
		
		
		for(mInVar2 i : this.greaterThenList) {
			
			// Prevent looping
			if(!checkList.contains(i)) {
				
				pln("checking " + i.id);
				
				// i is being checked
				checkList.add(i);
				
				if(i.isGreaterThen(ivTarget)) {

					pln("yes : " + this.id + " > " + ivTarget.id);
					
					checkList = null;
					return true;
				}
			}
		}

		pln("no : " + this.id + " != " + ivTarget.id);
		return false;
	}
	public boolean isLessThen(mInVar2 ivTarget) throws UncertaintyException {

		pln(this.id + " < " + ivTarget.id + "?");
		
		// First test if both are the same object
		if(this == ivTarget) {
			
			pln("no : " + this.id + " = " + ivTarget.id);
			return false;
		}
		// Check equalToList for the variable
		if(this.lessThenList.contains(ivTarget)) {

			pln("yes : " + this.id + " < " + ivTarget.id);
			return true;
			
		}
		
		// If the checklist isn't used already, initialise it
		if(checkList == null) checkList = new ArrayList<mInVar2>();
		
		
		for(mInVar2 i : this.lessThenList) {
			
			// Prevent looping
			if(!checkList.contains(i)) {
				
				pln("checking " + i.id);
				
				// i is being checked
				checkList.add(i);
				
				if(i.isLessThen(ivTarget)) {

					pln("yes : " + this.id + " < " + ivTarget.id);
					
					checkList = null;
					return true;
				}
			}
		}

		pln("no : " + this.id + " != " + ivTarget.id);
		return false;
	}
	
	@SuppressWarnings("serial")
	private class UncertaintyException extends Throwable {
		
		
	}
	
	// TODO DELETE
	public static void p(String message) {
		System.out.print(message);
	}
	public static void pln(String message) {
		System.out.println(message);
	}
}