/*
 * Author: Barry Huey
 * class KNNModel
 * Given two groups of KNNData values, classify which set that a KNNData test point(s)
 * belongs to determined by the k Nearest Neighbors learning model.
 */

package kNearestNeighbors;

import java.util.ArrayList;

public class KNNModel {
	private ArrayList<KNNData> groupOne;
	private ArrayList<KNNData> groupTwo;
	
	public KNNModel(ArrayList<KNNData> groupOne, ArrayList <KNNData> groupTwo) {
		this.groupOne = groupOne;
		this.groupTwo = groupTwo;
	}
	
	/* public ArrayList<KNNData> classify (ArrayList<KNNData> testData)
	 * 		testData - an ArrayList containing KNNData objects that represent testing
	 *				   points to be classified.
	 *
	 * Given a set of data points to be classified, this will return the same set of
	 * testing points with their classNum set to the group number that they most closely
	 * associate with (according to the rules delineated by kNN)
	 */
	public ArrayList<KNNData> classify (ArrayList<KNNData> testData) {
		/* For each test point, calculate the distance between it and all group data points.
		 * Store the (distance,group_data_point) pairs in an arraylist (there are only three test points,
		 * so only three lists will be made).
		 * Use the three nearest points (i.e. the three smallest distance values) and their classNum
		 * value to determine which group the point belongs to.
		 */
		ArrayList<ArrayList<Pair<Double,KNNData>>> allTestPoints = new ArrayList();
		// For each test point...
		for (KNNData testPoint : testData) {
			ArrayList<Pair<Double,KNNData>> distanceAndDataPoint = new ArrayList();
			// ...calculate the distance between it and all group points.
			for (KNNData groupOnePoint : groupOne) {
				double distance = testPoint.distance(groupOnePoint);
				Pair<Double,KNNData> temp = new Pair<Double,KNNData>(distance,groupOnePoint);
				distanceAndDataPoint.add(temp);
			}
			for (KNNData groupTwoPoint : groupTwo) {
				double distance = testPoint.distance(groupTwoPoint);
				Pair<Double,KNNData> temp = new Pair<Double,KNNData>(distance,groupTwoPoint);
				distanceAndDataPoint.add(temp);
			}
			allTestPoints.add(distanceAndDataPoint);
		}
		
		// Now sort each individual list in allTestPoints by distance and in ascending order
		for (int i = 0; i < allTestPoints.size(); i++) {
			int p1 = testData.get(i).getFirst();
			int p2 = testData.get(i).getSecond();
			int cn = testData.get(i).getClassNum();

			System.out.format("Test Point: (%d, %d, %d) \n",p1,p2,cn);
			ArrayList<Pair<Double,KNNData>> tempList = sortByDistance(allTestPoints.get(i));
			allTestPoints.set(i, tempList);
			printPairList(allTestPoints.get(i));
		}

		// Finally, create list containing all testing points with their classNum set to the appropriate group
		ArrayList<KNNData> sortedTests = new ArrayList<KNNData>();
		sortedTests = extractResults(allTestPoints, testData);
		return sortedTests;
	}
	
	/* public ArrayList<KNNData> extractResults(sortedList, testData)
	 * 		sortedList - an ArrayList of an ArrayList of Pair objects. Each Pair has their first 
	 					 value set to the distance between a test point and the second value, which
	 					 is a KNNData object representing a training point. 

	 * Returns a list of the test points with their group number (third value) set accordingly.
	 */
	public ArrayList<KNNData> extractResults(ArrayList<ArrayList<Pair<Double,KNNData>>> sortedList, ArrayList<KNNData> testData) {
		ArrayList<KNNData> sortedTests = new ArrayList<KNNData>();
		for (int i = 0; i < sortedList.size(); i++) {
			KNNData testTemp = testData.get(i);
			testTemp.setClassNum(majorityGroup(sortedList.get(i)));
			sortedTests.add(testTemp);
		}
		printKNNList(sortedTests);
		return sortedTests;
	}
	
	/* private int majorityGroup(sortedPairList)
			sortedPairList - an ArrayList of Pair objects.

	 * Returns an int that represents the group number to give a test point after
	 * determining which of the k neighbors is the majority of the nearest neighbors.
	 */
	private int majorityGroup (ArrayList<Pair<Double,KNNData>> sortedPairList) {
		int k = 0; int g1 = 0; int g2 = 0;
		for (int i = 0; i < sortedPairList.size(); i++) {
			// Either test until all data is considered OR stop after 3 tests
			if (k >= 3) break;
			
			double dist = sortedPairList.get(i).getFirst();
			int classnum = sortedPairList.get(i).getSecond().getClassNum();
			
			// If there is a value after i...
			if (i+1 < sortedPairList.size()) {
				double distNext = sortedPairList.get(i+1).getFirst();
				int classnumNext = sortedPairList.get(i+1).getSecond().getClassNum();
				// ... and if the groups are different and the distances the same...
				if ( dist == distNext && classnum != classnumNext) {
					// ...skip forward.
					k = k + 2;
					i = i + 1;
				}
				// If it isn't the case that the groups are different and the distances the same...
				else {
					// ...increment the group counters accordingly
					k = k + 1;
					if (classnum == 1) g1++;
					else g2++;
				}
			}
			// If there isn't a value after i and k is still less than or equal to 3...
			else {
				k = k + 1;
				if (classnum == 1) g1++;
				else g2++;
			}
		}
		// Return group number 
		if (g1 > g2) return 1;
		else if (g1 < g2) return 2;
		else return 0;
	}
	
	
	/*
	 * Sorts an arraylist of pairs<double,KNNData> using the first value of the pair.
	 * It's an insertion sort.
	 */
	public ArrayList<Pair<Double,KNNData>> sortByDistance(ArrayList<Pair<Double,KNNData>> distanceDataPoints) {
		//ArrayList<Pair<Double,KNNData>> sortedList = new ArrayList<Pair<Double,KNNData>>();
			for (int i = 1; i < distanceDataPoints.size(); i++) {
				for (int k = i; k >= 1; k--) {
					double kDistance = distanceDataPoints.get(k).getFirst();
					double prevKDistance = distanceDataPoints.get(k-1).getFirst();
					if (kDistance < prevKDistance) {
						swap(k-1,k,distanceDataPoints);
					}
				}
			}
		return distanceDataPoints;
	}
	
	/* public void swap(index1,index2,list)
	 * 		index1,index2 - int value representing an index value in a list
	 *		list - an ArrayList of Pairs
	 * Swaps the values at index1 and index2 in list. Solely used in the insertion sort above.
	 */ 
	public void swap(int index1, int index2, ArrayList<Pair<Double,KNNData>> list) {
		Pair<Double,KNNData> temp = list.get(index2);
		list.set(index2, list.get(index1));
		list.set(index1, temp);
	}
	
	/* public void printKNNList(list)
	 * 		list - an ArrayList of KNNData objects
	 * Prints out the index and the KNNData values for each element in the list
	 */
	public void printKNNList(ArrayList<KNNData> list) {
		for (int i = 0; i < list.size(); i++) {
			int p1 = list.get(i).getFirst();
			int p2 = list.get(i).getSecond();
			int cn = list.get(i).getClassNum();
			System.out.format("i: %d | KNN (%d, %d, %d) \n", i,p1,p2,cn);
		}
		System.out.println();
	}

	/* public void printPairList(list)
	 * 		list - an ArrayList of Pair objects (first value is a double, second KNNData object)
	 * Prints out the index, distance, and the KNNData values for each element in the list
	 */
	public void printPairList(ArrayList<Pair<Double,KNNData>> pairList) {
		System.out.format("list size: %d \n",pairList.size());
		for (int i = 0; i < pairList.size(); i++) {
			double distance = pairList.get(i).getFirst();
			int p1 = pairList.get(i).getSecond().getFirst();
			int p2 = pairList.get(i).getSecond().getSecond();
			int cN = pairList.get(i).getSecond().getClassNum();
			System.out.format("i: %d | distance: %f | KNNData: (%d,%d,%d) \n", i,distance,p1,p2,cN); 
		}
		System.out.println("_______");
	}
	
	
}
