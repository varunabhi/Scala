object minMax extends App {

  def minmax(arr:Array[Int])={
    (arr.min,arr.max)
  }
  var b=Array(5,23,456,78,12,59,98)
  val x=minmax(b)
  println("Maximum and Minimum value is "+x)

}
