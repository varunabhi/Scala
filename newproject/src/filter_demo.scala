object filter_demo {
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
    var list=List.range(1,100)
    var b=list.filter(checkPrime(_))
    for (x <- b){
      print(x+" ")
    }
  }
}
