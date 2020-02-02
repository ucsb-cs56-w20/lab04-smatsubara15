package edu.ucsb.cs56.ratcalc.model;

/** A class to represent a rational number

    with a numerator and denominator


    @author P. Conrad for CS56 F16


    */


//import java.lang.IllegaArgumentException;


public class Rational {


    private int num;

    private int denom;


    /** 

	greatest common divisor of a and b

	@param a first number

	@param b second number

	@return gcd of a and b

    */

    public static int gcd(int a, int b) {

	if (a==0)

	    return b;

	else if (b==0)

	    return a;

	else

	    return gcd(b%a, a);

    }


    public static int lcm(int a, int b){

    	return Math.abs(a*b)/Math.abs(gcd(a,b));

    } 


    public Rational plus(Rational r) {

    	int d = lcm(this.denom,r.denom);

	int scalar1 = d/this.denom;

	int scalar2 = d/r.denom;

	int num1 = scalar1*this.num;

	int num2 = scalar2*r.num;

	

	int numer = num1 + num2;

	

	return new Rational(numer,d);

	

    }


    public static Rational sum(Rational a, Rational b) {

    	

	int d = lcm(a.denom,b.denom);

        int scalar1 = d/a.denom;

        int scalar2 = d/b.denom;

        int num1 = scalar1*a.num;

        int num2 = scalar2*b.num;


        int numer = num1 + num2;


        return new Rational(numer,d);


    }


    public Rational minus(Rational r) {

    

    	return this.plus(r.toNegative());

    }


    public static Rational difference(Rational a, Rational b){

    	

	return sum(a, b.toNegative());

    

    }


    public Rational reciprocalOf(){

    	

	if(this.num == 0)

	{

		throw new ArithmeticException("numerator may not be zero");

	}

	else{

	    return new Rational(this.denom,this.num);

	}

    }

    

    public Rational dividedBy(Rational r){

    

	    r = r.reciprocalOf();


	    return this.times(r);


    }


    public static Rational quotient(Rational a, Rational b){

    

	    b = b.reciprocalOf();

	    return product(a,b);

    }


    public Rational toNegative(){

    	  

	    return new Rational(this.num * -1,this.denom);

    }



    public Rational() {

	this.num = 1;

	this.denom = 1;

    }


    public Rational(int num, int denom) {

	if (denom== 0) {

	    throw new IllegalArgumentException("denominator may not be zero");

	}

	if(num==0){
		this.num=0;
		this.denom=0;
	}
    
	else {
		this.num=num;
    	this.denom=denom;

	    int gcd = Rational.gcd(num,denom);

	    this.num /= gcd;

	    this.denom /= gcd;

	}

    }


    public String toString() {

	if (denom == 1 || num == 0)
	
	    return "" + num;
	if(denom<0){
		num*=-1;
		denom*=-1;
	}
	return num + "/" + denom;

    }


    public int getNumerator() { return this.num; }

    public int getDenominator() { return this.denom; }


    public Rational times(Rational r) {

	return new Rational(this.num * r.num,

			    this.denom * r.denom);

    }


    public static Rational product(Rational a, Rational b) {

	return new Rational(a.num * b.num,

			    a.denom * b.denom);

    }


    

    /** 

	For testing getters.  

	@param args unused

     */


    public static void main (String [] args) {

	Rational r = new Rational(5,7);

	System.out.println("r.getNumerator()=" + r.getNumerator());

	System.out.println("r.getDenominator()=" + r.getDenominator());

    }


    

}
