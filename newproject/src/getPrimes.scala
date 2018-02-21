object getPrimes {

  def checkPrime(i:Int): Boolean ={
    if(i==2)
      return true
    else{
      for(k <- 2 to i/2){
        if(i%k==0)
          return false

      }
      return true

    }
  }
  def main(args: Array[String]): Unit = {
    val num=10000
    println("list of Primes is x")
    for(m <- 2 to num){
      if(checkPrime(m))
        println(m)
    }
  }
}
