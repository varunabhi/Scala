//class position {



//}


object position{

  def main(args: Array[String]): Unit = {
    var a="is scala better than java"
    println("position of better is "+a.indexOf("better"))

    var b=a.replace("java","python")
    println("after replacing we get "+b)

    var c=b.split(" ")
    println("iterating through loop we get ")

    for(i <- 0 to c.length-1)
      println(c(i))

  }
}
