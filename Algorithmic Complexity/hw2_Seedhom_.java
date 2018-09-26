package hw2;

public class Complexity {

	public static void method0(int n) {
		int counter = 0;
		for (int i=0; i<n; i++) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	public static void method4(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int g = 0; g < n; g++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}
	public static void method2(int n) {
		int counter = 0;
		for (int i=1; i<n; i*= 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	public static void method3(int n) {
		int counter = 0;
		for (int i=1; i<n; i*= 2) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	public static void method5(int n){
		int counter = 0;
		for (int i = 1; i < n; i *= 2){
			System.out.println("Counter " + counter);
			counter++;
			n = n / ((int) Math.pow(2,i));
		}
	}
	public static int method6(int n) {
		int counter = 0;
		if (n <=0) {
			counter ++;
			System.out.println("Counter " + counter);
			return n;
		}else {
			counter ++;
			return method6(n - 1) + method6(n - 2);
		}
	}
	public static void main(String[] args) {
		method6(4);
	}
}
