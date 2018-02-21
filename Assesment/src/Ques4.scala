object Ques4 {



  def sum(a:Int,b:Int,c:Int,d:Int,e:Int):Int={
   return a+b+c+d+e
  }



  def main(args: Array[String]): Unit = {

    var num=12345
    var b=num.toString
    var arr=b.toCharArray

    val func1=sum _
    val func2=func1.curried
    val func3=func2(arr(0).toInt-48)
    val func4=func3(arr(1).toInt-48)
    val func5=func4(arr(2).toInt-48)
    val func6=func5(arr(3).toInt-48)
    val func7=func6(arr(4).toInt-48)
    println("Sum is "+func7)
  }


}
