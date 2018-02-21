object pattern {
  def printStar(i:Int)={
    for(l <- 1 to i)
      print("*")

  }

  def main(args: Array[String]): Unit = {
    var i=1
    var j=1

    println("Using for loop")
    for(i <- 1 until  5 ; j <- 1 until  i+1 ){
      print("*")
      if(j==i)
        println()
    }

    println("Using do while loop")
    var count=1
    do{
      printStar(count)
      println()
      count=count+1
    }while(count<5)

  }

}
