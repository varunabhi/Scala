import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

object listSort extends App {

  var lst=List(11,54,23,89,123,1,22)


  def ins_sort(obj:List[Int]):List[Int]={
    if(obj==Nil)
      return Nil

    else if(obj.length==1)
      return List(obj.head)
    else
      {
         var lst=obj
        val len=lst.length
        val key=lst(len-1)
        var j=len-2 //j=2
        while(j>=0 && lst(j)>key){
         lst=lst.updated(j+1,lst(j))
          j=j-1
        }
        lst=lst.updated(j+1,key)
        return lst
      }

  }

  def sortList(obj:List[Int]):List[Int]= obj match {

    case Nil => return Nil
    case n::rest => { ins_sort(sortList(rest):+n)}

  }

  val b=sortList(lst)
  println(b)

}
