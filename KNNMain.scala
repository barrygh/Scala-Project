import java.util.ArrayList
import kNearestNeighbors.KNNData
import kNearestNeighbors.KNNModel

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

        val testPoints: ArrayList[KNNData] = new ArrayList[KNNData]

        val t1: KNNData = new KNNData(6, 6, 0)
        val t2: KNNData = new KNNData(1, 4, 0)

        testPoints.add(t1)
        testPoints.add(t2)

        val knnModel1: KNNModel = new KNNModel(group1, group2)
        
        val group3: ArrayList[KNNData] = new ArrayList[KNNData]()
        val group4: ArrayList[KNNData] = new ArrayList[KNNData]()

        
        val g3_1: KNNData = new KNNData(4, 3, 1)
        val g3_2: KNNData = new KNNData(1, 6, 1)
        val g3_3: KNNData = new KNNData(2, 8, 1)
        val g4_1: KNNData = new KNNData(5, 1, 2)
        val g4_2: KNNData = new KNNData(3, 3, 2)
        val g4_3: KNNData = new KNNData(2, 4, 2)
        
        group3.add(g3_1)
        group3.add(g3_2)
        group3.add(g3_3)
        group4.add(g4_1)
        group4.add(g4_2)
        group4.add(g4_3)
        
        val knnModel2: KNNModel = new KNNModel(group3, group4)
        
        val t3: KNNData = new KNNData(4, 3, 0)
        val t4: KNNData = new KNNData(5, 1, 0)
        
        val testPoints2: ArrayList[KNNData] = new ArrayList[KNNData]
        
        testPoints2.add(t3)
        testPoints2.add(t4)
        
        knnModel1.classify(testPoints)
        knnModel2.classify(testPoints2)
    }
}

