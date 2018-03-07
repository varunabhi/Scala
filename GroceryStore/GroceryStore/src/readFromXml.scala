import java.util.concurrent.ScheduledExecutorService

import scala.collection.mutable.ListBuffer
import xml._

object readFromXml {

  val lb= new ListBuffer[dataStruct]()

  def importdata(): ListBuffer[dataStruct] ={
    lb.clear()
    val fileLoc=System.getProperty("user.dir")+"/src/GroceryData.xml"
    val file= XML.loadFile(fileLoc)
    val catId_elem= file \\ "category"
       val lnth=catId_elem.length
    var temp=0
    while (temp<lnth){
    val scID=catId_elem(temp).attribute("id").get.toString().toInt
    val uid_elem= catId_elem(temp) \\ "id"
    val name_elem= catId_elem(temp) \\ "name"
    val uom_elem= catId_elem(temp) \\ "uom"
    val us_elem= catId_elem(temp) \\ "unitSize"
    val amount_elem= catId_elem(temp) \\ "amount"
    val curr_elem= catId_elem(temp) \\ "currency"
    val stock_elem= catId_elem(temp) \\ "stock"

      var ct= (catId_elem(temp) \\ "items")
      var ln=(ct \\ "item").length
      var temp1=0
    while (temp1<ln){

      lb+= (new dataStruct(scID,uid_elem(temp1).text.toInt,name_elem(temp1).text,uom_elem(temp1).text,us_elem(temp1).text.toInt,amount_elem(temp1).text.toDouble,curr_elem(temp1).text,stock_elem(temp1).text))
      temp1+=1
    }
      temp+=1

    }
    return lb
  }

  import java.util.Calendar

  private def getHoursUntilTarget(targetHour: Int) = {
    val calendar = Calendar.getInstance
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    if (hour < targetHour) targetHour - hour
    else targetHour - hour + 24
  }

  def updateData(): Unit ={    // Lock issues can be there
    import java.util.Calendar
    import java.util.concurrent.Executors
    import java.util.concurrent.ScheduledExecutorService
    import java.util.concurrent.TimeUnit
    val scheduler = Executors.newScheduledThreadPool(1)
    import java.util.Calendar
    val calendar = Calendar.getInstance
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    scheduler.scheduleAtFixedRate(new Runnable {
      override def run(): Unit = importdata()
    }, getHoursUntilTarget(8), 24, TimeUnit.HOURS)
  }

  def getData(): Unit ={
    lb.foreach((obj) => (println(obj.SuperCatId,obj.uID,obj.name+" "+obj.us+" "+obj.uom+" "+obj.currency+" "+obj.amount+" "+obj.stock)))

  }

  def main(args: Array[String]): Unit = {
    importdata()
//    updateData()
    getData()
//    while (true){
//
//    }

  }

}

 class dataStruct(scId:Int,uid:Int,nm:String, um:String,usz:Int,amt:Double,curr:String, stk:String){
   val SuperCatId:Int=scId
   var uID:Integer=uid
   var name:String= nm
  var uom:String= um
  var us:Integer  = usz
  var amount:Double= amt
  var currency:String= curr
  var stock:String= stk

  }



