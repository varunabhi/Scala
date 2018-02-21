
object array_demo extends App {
     var a=Array.tabulate(5)(_*2)
      for(i <- a)
        print(i+" ")
     print("\n")
     var b= Array.fill(10)(200)
     for (x <- b)
       print(x+" ")



    println()
     var arr = new Array[Int](5)
      arr(0)=1
      arr(1)=1
      arr(2)=1
      arr(3)=1
     for (x <- arr)
       print(x+" ")






}
