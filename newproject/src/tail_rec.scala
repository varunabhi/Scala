import scala.annotation.tailrec

object tail_rec {
  var count:Int=1
  var accum:Int=0

  @tailrec
  def sum_series(a:Int,b:Int):Int={
    if(a==0)
      return b+(accum*(count-1))
    else{
      if(count==1)
        accum=b
      count+=1
      return sum_series(a-1,a+b)
    }
  }

  def main(args: Array[String]): Unit = {
    println(sum_series(5,6))
  }
}
