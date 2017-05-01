/*
 * Author: Barry Huey
 * class KNNMain
 * The main method from which KNNModel is invoked and tested.
 */
package kNearestNeighbors;

import java.util.ArrayList;

public class KNNMain {

	public static void main(String[] args) {
		ArrayList<KNNData> group1 = new ArrayList<KNNData>();
		ArrayList<KNNData> group2 = new ArrayList<KNNData>();

		KNNData g1_1 = new KNNData(1,7,1);
		KNNData g1_2 = new KNNData(3,5,1);
		KNNData g1_3 = new KNNData(4,7,1);
		KNNData g2_1 = new KNNData(2,2,2);
		KNNData g2_2 = new KNNData(4,4,2);
		KNNData g2_3 = new KNNData(6,3,2);
		
		group1.add(g1_1); group1.add(g1_2); group1.add(g1_3);
		group2.add(g2_1); group2.add(g2_2); group2.add(g2_3);
		
		ArrayList<KNNData> testPoints = new ArrayList<KNNData>();
		KNNData t1 = new KNNData(6,6,0);
		KNNData t2 = new KNNData(1,4,0);
		//KNNData t3 = new KNNData(0,1,0);
		testPoints.add(t1); testPoints.add(t2); //testPoints.add(t3);
		
		KNNModel knnModel = new KNNModel(group1,group2);
		knnModel.classify(testPoints);
		
	}
	
	

}
