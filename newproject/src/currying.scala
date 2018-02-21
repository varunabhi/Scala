object currying {

  def sum(a:Int,b:Int,c:Int,d:Int,e:Int):Int={
    return (a+b+c+d+e)
  }

  def main(args: Array[String]): Unit = {
    val cur_func=sum _
    val func1=cur_func.curried
    val func2=func1(10)
    val func3=func2(20)
    val func4=func3(30)
    val func5=func4(40)
    val func6=func5(50)
    println("Sum is "+func6)
    println("Other way sum is "+func1(12)(34)(55)(1)(5))
  }
}
