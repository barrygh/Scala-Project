
class KNNData (firstNum: Int, secondNum: Int, classNumber : Int) {
	var first: Int = firstNum
	var second: Int = secondNum
	var classNum: Int = classNumber

	def getFirst : Int = {
		return first
	}

	def getSecond  : Int = {
		return second
	}

	def getClassNum : Int = {
		return classNum
	}

	def setClassNum (num: Int) {
		classNum = num 
	}

	def distance (otherKNNData: KNNData) : Double = {
		var x = scala.math.pow((first - otherKNNData.getFirst),2)
		var y = scala.math.pow((second - otherKNNData.getSecond),2)
		return scala.math.sqrt(x + y)
	}
}
