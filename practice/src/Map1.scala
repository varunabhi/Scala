import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/* Set up a map of prices for a number of gizmos that you covet. Then produce a
second map with the same keys and the prices at a 10 percent discount.
 */

object Map1 extends App {



  val a=mutable.LinkedHashMap(1 -> 120, 2 -> 230 , 3 -> 160 , 4 -> 220 , 5-> 500 )

  var set_keys=a.keySet
  var set_values=a.values
//  println(set_keys)
//  println(set_values)
   var arr_keys=set_keys.toArray
  var arr_values=set_values.toArray

  var count=0
  val b=new mutable.LinkedHashMap[Int,Double]
  for(x <- arr_keys){
    val c=arr_values(count)-(arr_values(count)*0.1)
    b+=(arr_keys(count) -> c)
    count+=1
  }

  println("new map is")
  println(b)




}
