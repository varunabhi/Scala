object zipWithIndex_demo extends  App{

  var a=List('a','b','c')
    // Using forEach
  a.zipWithIndex.foreach{case(k:Char, count:Int) => println(s"$count is $k")}

  //Using For
  for((ch,count) <- a.zipWithIndex){
    println(s"$count is $ch")

  }
}
