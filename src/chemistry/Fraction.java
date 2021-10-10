package chemistry;

import static java.lang.Math.min;

public class Fraction {
	private int num, den;
	public int getNum() {
		return num;
	}
	public int getDen() {
		return den;
	}
	public void setNum(int n) {
		num = n;
	}
	public void setDen(int n) {
		den = n;
	}
	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
		simplify(this);
	}
	public Fraction(int num) {
		this.num = num;
		this.den = 1;
	}
	public void add(Fraction f) {
		this.num = this.num * f.den + f.num * this.den;
		this.den = this.den * f.den;
		simplify(this);
	}
	public void subtract(Fraction f) {
		this.num = this.num * f.den - f.num * this.den;
		this.den = this.den * f.den;
		simplify(this);
	}
	public void multiply(Fraction f) {
		this.num *= f.num;
		this.den *= f.den;
		simplify(this);
	}
	public void divide(Fraction f) {
		this.num *= f.den;
		this.den *= f.num;
		simplify(this);
	}
	public static void simplify(Fraction f) {
		boolean isNegative = f.num < 0 && f.den > 0 || f.num > 0 && f.den < 0;
		if(f.num < 0) f.num *= -1;
		if(f.den < 0) f.den *= -1;
		for(int i = min(f.num, f.den); i >= 2; i--) {
			if(f.num % i == 0 && f.den % i == 0) {
				f.num /= i;
				f.den /= i;
			}
		}
		if(isNegative) f.num *= -1;
		if(f.num == 0) f.den = 1;
	}
	public String toString() {
		return num + "/" + den;
	}
	
}
