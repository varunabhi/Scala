object Ques5 {



  def power(i:Int,j:Int):Int={
    var ans=1
    for (x <- 1 to j){
      ans=ans*i
    }
    return ans
  }


  def func(x:Int,n:Int,op: => Unit):Int={

      return power(x+2,n+5)
  }



  def main(args: Array[String]): Unit = {
    println(func(1,2,power(1,2)))

  }

}
