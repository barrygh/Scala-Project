package kNearestNeighbors

/* class KNNData
 * This class is meant to contain all of the relevant information for the kNN 
 * machine learning algorithm. It was specifically built for the situation 
 * described in the project specs, which is why there are only two pieces of
 * traning data (first and second)
 */
class KNNData(firstNum: Int, secondNum: Int, classNumber: Int) {
    var first: Int = firstNum
    var second: Int = secondNum
    var classNum: Int = classNumber

    /* getFirst
     * Returns the first var of the KNNData object
     */
    def getFirst: Int = {
        return first
    }

    /* getSecond
     * Returns the second var of the KNNData object
     */
    def getSecond: Int = {
        return second
    }

    /* getClassNum
     * Returns the classNum var of the KNNData object
     */
    def getClassNum: Int = {
        return classNum
    }

    /* setClassNum (num: Int)
     * A setter that sets the KNNData object's classNum var to num
     */
    def setClassNum(num: Int) {
        classNum = num
    }

    /* distance (otherKNNData: KNNData)
	 * Calculates the distance between two points, those points being
	 * the first and second values from otherKNNData and the calling
	 * KNNData object. The distance is returned as a double value.
	 */
    def distance(otherKNNData: KNNData): Double = {
        var x = scala.math.pow((first - otherKNNData.getFirst), 2)
        var y = scala.math.pow((second - otherKNNData.getSecond), 2)
        return scala.math.sqrt(x + y)
    }
}
