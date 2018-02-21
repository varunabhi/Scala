/* Write a function lteqgt(values: Array[Int], v: Int) that returns a tuple
 containing the counts of values less than v, equal to v, and greater than v.
  */

object tuple_demo  extends App{

  def func(arr:Array[Int],v:Int)={
    var b=arr.filter(_<v)
    var c=arr.filter(_==v)
    var d=arr.filter(_>v)
    (b,c,d)

  }

  var arr_new=Array(22,33,44,55,66,77,88,99)
  var (a,b,c)=func(arr_new,55)
  println("Count of Elements less than 55")
  println(a.length)

  println("Count of elements equal to 55")
  println(b.length)

  println("Count of Elements More than 55")
  println(c.length)
}
