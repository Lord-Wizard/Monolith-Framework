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

public class mFraction {
	
	public int whole;
	
	public int denomenator;	// Teller
	public int numerator;	// Noemer
	
	// CONSTRUCTOR
	// TODO Ook CFunction als input
	public mFraction(double denomenator, double numerator) {
		
		this(0, denomenator, numerator);
	}
	public mFraction(double whole, double denomenator, double numerator) {

		// TODO Controleren of de getallen int of double zijn
		this.whole = (int)whole;
		this.denomenator = (int)denomenator;
		this.numerator = (int)numerator;
	}
	
	public static void main(String[] args) {
		
		mFraction fr1 = new mFraction(4, 2);
		mFraction fr2 = new mFraction(8, 3);
		
		System.out.println("Fractions");
		
		System.out.println(fr1.toString());
		System.out.println(fr2.toString());
		System.out.println(fr1.condence().toString());
		System.out.println(fr2.condence().toString());
		System.out.println(fr2.decompose().toString());
		
		System.out.println("==============================");
		
		System.out.println("fr1 add fr2		"		+ fr1.add(fr2).toString());
		System.out.println("fr1 sub fr2		"		+ fr1.sub(fr2).toString());
		System.out.println("fr1 multiply fr2	"	+ fr1.multiply(fr2).toString());
		System.out.println("fr1 divide fr2		"	+ fr1.divide(fr2).toString());
	}
	
	public String toString() {
		
		String fractionString = null;
		
		if(whole == 0 && denomenator == 0) {
			
			fractionString = "0";
			
		} else {
			
			fractionString = whole + "(" + denomenator + " / " + numerator + ")";
			
			if(whole == 0) {
				
				fractionString = "(" + denomenator + " / " + numerator + ")";
			}
			if(denomenator == 0) {

				fractionString = whole + "";
			}
			if(numerator == 0) {
				
				fractionString = "infinite";
			}
		}
		
		return fractionString;
	}
	
	//===================================================
	// Heavy math shit
	//===================================================
	public mFraction add(mFraction frac) {
		
		mFraction tmpFrac1 = decompose();
		mFraction tmpFrac2 = frac.decompose();
		
		int tmpDenomenator = tmpFrac1.denomenator * tmpFrac2.numerator + tmpFrac2.denomenator * tmpFrac1.numerator;
		int tmpNumerator = tmpFrac1.numerator * tmpFrac2.numerator;
		
		return new mFraction(tmpDenomenator, tmpNumerator);
	}
	public mFraction sub(mFraction frac) {

		mFraction tmpFrac1 = decompose();
		mFraction tmpFrac2 = frac.decompose();
		
		int tmpDenomenator = tmpFrac1.denomenator * tmpFrac2.numerator - tmpFrac2.denomenator * tmpFrac1.numerator;
		int tmpNumerator = tmpFrac1.numerator * tmpFrac2.numerator;
		
		return new mFraction(tmpDenomenator, tmpNumerator);
	}
	public mFraction multiply(mFraction frac) {

		mFraction tmpFrac1 = decompose();
		mFraction tmpFrac2 = frac.decompose();
		
		int tmpDenomenator = tmpFrac1.denomenator * tmpFrac2.denomenator;
		int tmpNumerator = tmpFrac1.numerator * tmpFrac2.numerator;
		
		return new mFraction(tmpDenomenator, tmpNumerator);
	}
	public mFraction divide(mFraction frac) {
		
		mFraction tmpFrac1 = decompose();
		mFraction tmpFrac2 = frac.decompose();
		
		int tmpDenomenator = tmpFrac1.denomenator * tmpFrac2.numerator;
		int tmpNumerator = tmpFrac1.numerator * tmpFrac2.denomenator;
		
		return new mFraction(tmpDenomenator, tmpNumerator);
	}
	
	// Put everything in a fracture
	public mFraction decompose() {		// 2(2/3) --> 8/3
		
		if(whole == 0) return this;
		
		int tmpDenomenator = whole * numerator;
		
		return new mFraction(tmpDenomenator, numerator);
	}
	public mFraction condence() {		// 8/3 --> 2(2/3)
		
		if(denomenator < numerator) return this;
		
		int tmpWhole = denomenator / numerator;
		int tmpDenomenator = denomenator % numerator;
		
		return new mFraction(tmpWhole, tmpDenomenator, numerator);
	}
	
	public void compress() {			// Turn (6 / 3) into (3 / 2)
		
		
	}
}