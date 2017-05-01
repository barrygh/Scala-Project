/*
 * Author: Barry Huey
 * 
 * class KNNData 
 * A data model that stores the data for the kNN learning model.
 * The private ints first and second represent the int data points that the 
 * kNN model is to learn from. The third int is the group number that the 
 * data belongs to.
 */

package kNearestNeighbors;

public class KNNData {
	private int first;
	private int second;
	private int classNum;
	
	public KNNData(int first, int second, int classNum) {
		this.first = first;
		this.second = second;
		this.classNum = classNum;
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
	
	public int getClassNum() {
		return classNum;
	}
	
	public void setClassNum(int n) {
		this.classNum = n;
	}
	
	// Distance between two points
	public double distance(KNNData otherData) {
		//System.out.println("this x: " + this.first + "| other x: " + otherData.getFirst());
		double x = Math.pow((this.first - otherData.getFirst()), 2);
		//System.out.println("x: " + x);
		double y = Math.pow((this.second - otherData.getSecond()), 2);
		//System.out.println("y: " + y);
		return Math.sqrt(x + y);
	}
}
