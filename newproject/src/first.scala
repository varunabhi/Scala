import scala.collection.mutable.Stack
class first{
  var ints= Stack[Int]()

  def biggest(a:Int,b:Int,c:Int):Int={
    var largest:Int=0
    var nums= List(a,b,c)
    for(k <- nums if k % 5 ==0 ; if k % 7 != 0)
      ints.push(k)

    for(l <- ints)
    {
      if(l>largest)
        largest=l
    }
    return largest

  }

}

object demo_first{

  def main(args: Array[String]): Unit = {
    var obj=new first()
    var a=obj.biggest(50,100,105)
    println("Required Number is "+a)
  }

}