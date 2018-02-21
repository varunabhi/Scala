  /*5) write a function to calculate x ^ n = x*x*...n times. Ex: 2 ^3 = 8 (2 power 3).
      Create another function which accepts x, n and above function as parameters.
    Increment x by 2 and n by 5 before invoking the power function.*/
object Ques5_New extends App {

    def power(x:Int,n:Int): Int ={
      var p=1
      for(i<-1 to n){
        p=p*x
      }
      //print(p)
      return p
    }
    def powerNext(x:Int,n:Int,pw:(Int,Int)=>Int): Int ={
      var ans=pw(x+2,n+5)
      return ans
    }
    println(powerNext(3,1,power))
  }


