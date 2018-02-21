object Ques2 {

def sum(num:Int):Int={
  if(num==0)
    return 0
  else
    return num%10+sum(num/10)

}

  def main(args: Array[String]): Unit = {
    println(sum(12345))
    println(sum(345623))
  }

}
