object Options_demo {
  def show(b: Option[Int])= b match {
    case Some(s) => s
    case None => "Null"

  }
  def main(args: Array[String]): Unit = {
    var a:Option[Int]=Some(10)
    var b:Option[Int]=None


    println("a is "+show(a))
    println("b is "+show(b))

    // getElse

    println("get else gives")
    println(a.getOrElse(12))
    println(b.getOrElse(22))

    println("is empty")
    println(a.isEmpty)
    println(b.isEmpty)
  }

}

//object options extends App{
//
//  def show(b:Option[Int])= b match {
//    case Some(s) => s
//    case None => "Null"
//
//  }
//}
