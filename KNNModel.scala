package kNearestNeighbors

import java.util.ArrayList

import kNearestNeighbors.KNNData


class KNNModel(private var groupOne: ArrayList[KNNData], private var groupTwo: ArrayList[KNNData]) {

	// For each test point, calculate the distance between it and all group data points.
	// Store the (distance,group_data_point) pairs in an arraylist (there are only three test points,
	// so only three lists will be made).
	// Use the three nearest points (i.e. the three smallest distance values) and their classNum
	// value to determine which group the point belongs to.
	// For each test point, calculate the distance between it and all group data points.
	// Store the (distance,group_data_point) pairs in an arraylist (there are only three test points,
	// so only three lists will be made).
	// Use the three nearest points (i.e. the three smallest distance values) and their classNum
	// value to determine which group the point belongs to.
	def classify(testData: ArrayList[KNNData]): ArrayList[KNNData] = {
		val allTestPoints: ArrayList[ArrayList[Pair[Double, KNNData]]] = new ArrayList[ArrayList[Pair[Double, KNNData]]]()

		// For each test point...
		for (testPoint <- testData) {

			val distanceAndDataPoint: ArrayList[Pair[Double, KNNData]] = new ArrayList[Pair[Double, KNNData]]()

			// ...calculate the distance between it and all group points.
			for (groupOnePoint <- groupOne) {
				val distance: Double = testPoint.distance(groupOnePoint)
				val temp: Pair[Double, KNNData] =
				new Pair[Double, KNNData](distance, groupOnePoint)
				distanceAndDataPoint.add(temp)
			}

			for (groupTwoPoint <- groupTwo) {
				val distance: Double = testPoint.distance(groupTwoPoint)
				val temp: Pair[Double, KNNData] =
				new Pair[Double, KNNData](distance, groupTwoPoint)
				distanceAndDataPoint.add(temp)
			}

			allTestPoints.add(distanceAndDataPoint)
		}

		for (i <- 0 until allTestPoints.size) {
			val p1: Int = testData.get(i).getFirst
			val p2: Int = testData.get(i).getSecond
			val cn: Int = testData.get(i).getClassNum
			System.out.format("Test Point: (%d, %d, %d) \n", p1, p2, cn)
			val tempList: ArrayList[Pair[Double, KNNData]] = sortByDistance(
			allTestPoints.get(i))
			allTestPoints.set(i, tempList)
			printPairList(allTestPoints.get(i))
		}

		// Finally, create list containing all testing points with their classNum set to the appropriate group
		var sortedTests: ArrayList[KNNData] = new ArrayList[KNNData]()
		sortedTests = extractResults(allTestPoints, testData)
		sortedTests
	}

	/*
	 * Returns a list of the test points with their group number (third value) set accordingly
	 */

	def extractResults(sortedList: ArrayList[ArrayList[Pair[Double, KNNData]]], testData: ArrayList[KNNData]): ArrayList[KNNData] = {
		val sortedTests: ArrayList[KNNData] = new ArrayList[KNNData]()
		for (i <- 0 until sortedList.size) {
			val testTemp: KNNData = testData.get(i)
			testTemp.setClassNum(majorityGroup(sortedList.get(i)))
			sortedTests.add(testTemp)
		}
		printKNNList(sortedTests)
		sortedTests
	}

	/*
	 * Returns an int that represents the group number to give a test point after
	 * considering the data.
	 */
	def majorityGroup(sortedPairList: ArrayList[Pair[Double, KNNData]]): Int = {
		var k: Int = 0
		var g1: Int = 0
		var g2: Int = 0
		for (i <- 0 until sortedPairList.size) {
		// Either test until all data is considered OR stop after 3 tests
			if (k >= 3){ //break
				if (g1 > g2) 1 else if (g1 < g2) 2 else 0
			}
			// If there is a value after i...
			if (i + 1 < sortedPairList.size) {
				val distNext: Double = sortedPairList.get(i + 1).getFirst
				val classnumNext: Int =
				sortedPairList.get(i + 1).getSecond.getClassNum	
				// ... and if the groups are different and the distances the same...
          		if (sortedPairList.get(i).getFirst == sortedPairList.get(i).getSecond.getClassNum &&classnum != classnumNext) {
					// ...skip forward.
					k = k + 2
					i = i + 1
				}// If it isn't the case that the groups are different and the distances the same...
 				else {
					// ...increment the group counters accordingly
					k = k + 1
					if (classnum == 1) { g1 += 1; g1 - 1 } else { g2 += 1; g2 - 1 }
            	}
			}  // If there isn't a value after i and k is still less than or equal to 3...
			else{
            	k = k + 1
            	if (classnum == 1) { g1 += 1; g1 - 1 } else { g2 += 1; g2 - 1 }
			}
		}
		// Return group number
		if (g1 > g2) 1 else if (g1 < g2) 2 else 0
	}

	/*
	 * sorts an arraylist of pairs<double,KNNData> using the first value of the pair
	 * insertion sort
	 */
	def sortByDistance(distanceDataPoints: ArrayList[Pair[Double, KNNData]]) : ArrayList[Pair[Double, KNNData]] = {
		for (i <- 1 until distanceDataPoints.size) {
			var k: Int = i
			while (k >= 1) {
				val kDistance: Double = distanceDataPoints.get(k).getFirst
				val prevKDistance: Double = distanceDataPoints.get(k - 1).getFirst
				if (kDistance < prevKDistance) {
					swap(k - 1, k, distanceDataPoints)
				}
				{ k -= 1; k + 1 }
			}
		}
		distanceDataPoints
	}

	def swap(index1: Int, index2: Int, list: ArrayList[Pair[Double, KNNData]]): Unit = {
		val temp: Pair[Double, KNNData] = list.get(index2)
		list.set(index2, list.get(index1))
		list.set(index1, temp)
	}

	def printKNNList(list: ArrayList[KNNData]): Unit = {
		for (i <- 0 until list.size) {
			val p1: Int = list.get(i).getFirst
			val p2: Int = list.get(i).getSecond
 			val cn: Int = list.get(i).getClassNum
			System.out.format("i: %d | KNN (%d, %d, %d) \n", i, p1, p2, cn)
		}
    	println()
	}

	def printPairList(pairList: ArrayList[Pair[Double, KNNData]]): Unit = {
		System.out.format("list size: %d \n", pairList.size)
		for (i <- 0 until pairList.size) {
			val distance: Double = pairList.get(i).getFirst
			val p1: Int = pairList.get(i).getSecond.getFirst
			val p2: Int = pairList.get(i).getSecond.getSecond
			val cN: Int = pairList.get(i).getSecond.getClassNum
			System.out.format("i: %d | distance: %f | KNNData: (%d,%d,%d) \n", i, distance, p1, p2, cN)
		}
		println("_______")
	}
}
