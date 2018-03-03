import scala.collection.mutable.ListBuffer
import xml._

 class dataStruct(uid:Int,nm:String, um:String,usz:Int,amt:Double,curr:String, stk:String,extra:String){
  var uID:Integer=null
   var name:String= null
  var uom:String= null
  var us:Integer  = null
  var amount:Double= _
  var currency:String= null
  var stock:String= null

  def this(uid:Int,nm:String, um:String,usz:Int,amt:Double,curr:String, stk:String){
    this(uid,nm,um,usz,amt,curr,stk,null)
    this.uID=this.uid
    this.name=this.nm
    this.uom=this.um
    this.us=this.usz
    this.amount=this.amt
    this.currency=this.curr
    this.stock=this.stk

  }


}

object readFromXml {

  val lb= new ListBuffer[dataStruct]()

  def importdata(): Unit ={
    val file= XML.loadFile("/home/varun/IdeaProjects/GroceryStore/src/GroceryData..xml")
    val uid_elem= file \\ "id"
    val name_elem= file \\ "name"
    val uom_elem= file \\ "uom"
    val us_elem= file \\ "unitSize"
    val amount_elem= file \\ "amount"
    val curr_elem= file \\ "currency"
    val stock_elem= file \\ "stock"

    val count=name_elem.length
    var temp=0

    while (temp<count){
      lb+= (new dataStruct(uid_elem(temp).text.toInt,name_elem(temp).text,uom_elem(temp).text,us_elem(temp).text.toInt,amount_elem(temp).text.toDouble,curr_elem(temp).text,stock_elem(temp).text))
      temp+=1
    }

  }

  def getData(): Unit ={
    lb.foreach((obj) => (println(obj.uID,obj.name+" "+obj.us+" "+obj.uom+" "+obj.currency+" "+obj.amount+" "+obj.stock)))

  }

  def main(args: Array[String]): Unit = {
    importdata()
    getData()
  }

}
