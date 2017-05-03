package kNearestNeighbors

import kNearestNeighbors.KNNModel
import java.util.ArrayList

object KNNMain {

	def main(args: Array[String]): Unit = {
		val group1: ArrayList[KNNData] = new ArrayList[KNNData]()
		val group2: ArrayList[KNNData] = new ArrayList[KNNData]()
 
		val g1_1: KNNData = new KNNData(1, 7, 1)
		val g1_2: KNNData = new KNNData(3, 5, 1)
		val g1_3: KNNData = new KNNData(4, 7, 1)
		val g2_1: KNNData = new KNNData(2, 2, 2)
		val g2_2: KNNData = new KNNData(4, 4, 2)
		val g2_3: KNNData = new KNNData(6, 3, 2)

		group1.add(g1_1)
		group1.add(g1_2)
		group1.add(g1_3)
 		group2.add(g2_1)
		group2.add(g2_2)
		group2.add(g2_3)

		val testPoints: ArrayList[KNNData] = new ArrayList[KNNData]()

    	val t1: KNNData = new KNNData(6, 6, 0)
    	val t2: KNNData = new KNNData(1, 4, 0)

    	testPoints.add(t1)
    	testPoints.add(t2)

    	val knnModel: KNNModel = new KNNModel(group1, group2)

    	knnModel.classify(testPoints)
	}
}

