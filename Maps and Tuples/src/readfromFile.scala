/* Write a program that reads words from a file. Use a mutable map to count how often
each word appears. To read the words, simply use a java.util.Scanner:
 */


import scala.collection.mutable.Map

object readfromFile extends App {

  val map_alpha=Map[Char,Int]()
  var chars='a' to 'z'

  val b=new java.util.Scanner(new java.io.File("/home/varun/IdeaProjects/Maps and Tuples/src/globalwarming.txt"))
  var count=0

  for(x <- chars)
    map_alpha.+=(x -> 0)


  while(b.hasNext) {
      var word=b.next()
       var arr=word.toCharArray
     for(x <- arr){
       if(map_alpha.contains(x)){
         var value=map_alpha(x)
         value+=1
         map_alpha.update(x,value)
       }
     }
    count += 1
  }

  println("No of words are "+count)
  println("This map shows the corresponding values for esch character")
  println(map_alpha)
}
