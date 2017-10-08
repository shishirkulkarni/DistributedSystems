package edu.sjsu.cs249.four;

public class Main {
	public static void main(String[] args) {
		Algorithm a = new Algorithm();
		a.execute();
		System.out.println("Maximal consistent cut: ");
		System.out.println("P0: " + a.getP0().cut(new VectorClock(new int[] {2, 6})));
		System.out.println("P1: " + a.getP1().cut(new VectorClock(new int[] {2, 6})));
	}
}
