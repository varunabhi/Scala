import scala.annotation.tailrec

object tailRec {

  var n1,n3=0
  var n2=1

  @tailrec
  def getFib(num:Int,ans1:Int,ans2:Int):Int={
    if(num<2)
      return ans1+ans2
    else {
      var temp1=ans2
      var temp2=ans1+ans2
      return getFib(num-1,temp1,temp2)
    }
  }

  @tailrec
  def fib(num:Int):Unit={
  if (num>0){
    n3=n1+n2
    n1=n2
    n2=n3
    print(n3+" ")
    fib(num-1)
  }

  }

  def main(args: Array[String]): Unit = {
    println(getFib(5,0,1))
  }
}
