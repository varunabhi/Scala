trait a{
  var a:Int=5
  def show(p:Int):Unit
}

trait b extends a{
  def show()=println("Hello world")

}

class ss extends b {
  override def show(p: Int): Unit = ???
}

object demo_p{

  def main(args: Array[String]): Unit = {
   new ss().show()
  }
}
