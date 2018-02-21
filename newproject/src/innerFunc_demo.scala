
object innerFunc_demo{



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



  def findprimeAndfact(i:Int,j:Int)={


    var prime=0
    var fact:BigInt=1
    import util.control.Breaks._
    //println("Control here")
    breakable {
      for (ob <- i to j) {
//        println("Control here "+i+" "+j)
        if (checkPrime(ob)) {
          prime = ob
//          println("prime is "+prime)
          break()
        }
      }
    }


    def getfact(p:Int):BigInt={

      //println("In inner fun "+fact)
      for(k <- 1 to prime)
        fact=fact*k

//      println("here now this is "+fact)
      return fact
    }
    // println("prime here is "+prime)


      //print("Fact here is "+fact)
    (prime,getfact(prime))
  }

  def main(args: Array[String]): Unit = {
   val (prime_num,fact1)= findprimeAndfact(50,100)
    println("Prime Number is "+prime_num)
    println("factorial is "+fact1)

  }

}
