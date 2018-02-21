object Numbers{
  def main(args: Array[String]): Unit = {
    var sum=0
    var n=(50-10)+1
    for(i <- 10 to 50)
      sum=sum+i

    var avg=sum/n

    println(f"Sum is $sum%3.3f")
    println(f"Avg is $avg%3.3f")

    println(raw"/\r /\t/\n")

  }

}