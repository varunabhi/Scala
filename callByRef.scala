 class callByRef {

  var num1:Int=_

}

object swap_ref {

  def swap(a: callByRef, b: callByRef): Unit = {
    var temp1: callByRef = a
    var temp2: callByRef = b
    var temp = temp1
    temp1 = temp2
    temp2 = temp

  }

  def main(args: Array[String]): Unit = {


    var obj1 = new callByRef
    var obj2 = new callByRef
    obj1.num1 = 10
    obj2.num1 = 20
    println("Before swapping")
    println(obj1.num1 + " " + obj2.num1)
    println("After swapping")
    swap(obj1,obj2)
    println(obj1.num1 + " " + obj2.num1)


  }
}
