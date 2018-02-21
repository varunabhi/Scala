object seq_demo {

  def main(args: Array[String]): Unit = {
    var a:Seq[Int]=Seq(13,56,33,44,33)
    println("using For each")
    a.foreach((elemt:Int) => println(elemt+" "))
    println()
    println("Using index")
    println(a(3))
  }
}
