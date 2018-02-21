

class scalabale_modi{
  def bar(x:Int)=x

}

trait one extends scalabale_modi{
  override def bar(x: Int): Int = x+ super.bar(x)

}

trait two extends scalabale_modi{
  override def bar(x: Int): Int = x*super.bar(x)
}

object demo{
  def main(args: Array[String]): Unit = {
    println((new scalabale_modi with one with two).bar(10))
    println((new scalabale_modi with two with one).bar(10))
  }

}
