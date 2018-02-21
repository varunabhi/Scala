class Divisible {

  def isDivisible(a:Int,b:Int,c:Int):Unit={


    for(i <- List(a,b,c)){
      if(i % 5 == 0 && i % 7 != 0 )
        println(i)
    }
  }
}

object Divisible{
  def main(args: Array[String]): Unit = {
    var a=new Divisible().isDivisible(15,35,70)
    println(a)
  }

}
