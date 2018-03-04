//THIS IS USER SIDE, SWITCH ON THE ADMIN FIRST TO ACCESS DISCOUNTS



import java.io.{BufferedInputStream, BufferedOutputStream, DataInputStream, DataOutputStream}
import java.net.Socket
import javax.swing.{JFrame, JOptionPane}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer






object UserSide {
  var hm_discounts= new mutable.HashMap[Int,String]()

   var hm_discountCart= new mutable.HashMap[Int,Double]()

  var hm_cart=new mutable.HashMap[Int,Double]() //clear was not available in simple Map


  def getSelOption():Int  ={
    println("Select your Preferred option:")
    println("1. Add items to Cart")
    println("2. Update your Cart")
    println("3. View Cart")
    println("4. Finalize order")
    println("5. Exit")
    val selected=readLine().toInt
    selected
  }

  def printTable(): Unit = {
      val b= refreshCartData()
     println(Tabulator.format(b))
  }

  def formatToList(obj:dataStruct): List[Any] ={
    List(obj.uID,obj.name,obj.amount)
  }


  var local_lb=readFromXml.importdata()

  def refreshCartData():List[List[Any]] = {


    var local_list=local_lb.map(formatToList).toList
    local_list=List("ID","Name","Amount")::local_list
    return local_list
  }

  def finalToCart(opt:Int,qty:Double): Unit ={

    if(hm_cart.contains(opt)){
      hm_cart.update(opt,(hm_cart.get(opt).get)+qty)
    }

    else{
    hm_cart+=(opt -> qty)
    }
    println("Item Added")

    val id=getSelOption()
    shwSpecDetails(id)

  }


  def callAgain(): Unit = {
    val id=getSelOption()
    shwSpecDetails(id)
  }

  def viewCart(x:Int=0): Double = {

    if (hm_cart.isEmpty) {
      println("Cart is Empty")
      callAgain()
      return 0
    }
    else {
      var total:Double=0
      var obj: Seq[Seq[Any]] = Seq[Seq[Any]]()
      for (x <- hm_cart) {
        obj = obj :+ Seq(x._1, local_lb(x._1).name, x._2,(x._2*(local_lb(x._1).amount)))
        total+=(local_lb(x._1).amount * x._2)
      }
      obj = Seq("Id", "Name", "Qty","Price") +: obj
      obj = obj :+ Seq("-","-","-",total)
      println(Tabulator.format(obj))

      if(x==0){
      if(hm_discountCart.size!=0){
        println("In extras You got")
        if(hm_discountCart.contains(0))
          println(hm_discountCart.get(0).get+ " Soaps")
        if(hm_discountCart.contains(1))
          println(hm_discountCart.get(1).get+ " Rs Discount on Final order")



      if(x==0)
      callAgain()
    }
      }
      return total
  }

  }
  def resetCart(): Unit ={
    hm_cart.clear()
  }

  def sendDataToAdmin(i: Int, qty: Double):String = {
    dos.writeUTF("hello")
    dos.writeInt(i)
    dos.writeDouble(qty)

    val f=dis.readUTF()
    return f
  }

  def viewoptions(sel_opt: Int): Unit = {
    if(hm_discounts.contains(sel_opt-1)){
      println("Hurray Discount is Available")
      println("-----> "+hm_discounts.get(sel_opt-1).get)
    }


    val obj=local_lb(sel_opt-1)
    var qty:Double=0
    if(obj.uom=="kg"){
      println(obj.name+" are Rs. "+obj.amount+" per Kg")
      qty=readLine("Enter Weight in Kg you want: ").toDouble
       println("Final price is Rs. "+(qty*(obj.amount)))
    }
    else{
      println(obj.name+" is Rs. "+obj.amount+" per Unit")
      qty=readLine("Enter Number of Units you want: ").toInt
      var price=(qty * obj.amount)
      println("Checking for Discounts...waiting for Admin")
      val b= sendDataToAdmin(sel_opt-1,qty)
      if(b.matches("soap")){
       var x=(qty/4).toInt
        println(x+ "Soaps added extra as Offer")
        if(!hm_discountCart.contains(0)){
//          println("price here")
          hm_discountCart+=(0 -> x)
        }
        else{
//          println("Control here")
          hm_discountCart.update(0,hm_discountCart.get(0).get+x)
        }
      }
      else if(b.matches("old")){
        val x=(qty/2).toInt
        val disc=(0.2)*price

        println("Discount of Rs. "+disc+" given as offer, Discount will be applied during checkout")
        if(!hm_discountCart.contains(1))
          hm_discountCart+=(1 -> disc)
        else{
          hm_discountCart.update(1,hm_discountCart.get(1).get+disc)
        }
      }

//      else if(b.matches("red")){
//        val x=(qty/3).toInt
//        println(x +" Old spice added as gift")
//        if(!hm_discountCart.contains(4))
//          hm_discountCart+=(4 -> x)
//        else{
//          hm_discountCart.update(4,hm_discountCart.get(4).get+x)
//        }
//
//      }


      println("Final price is Rs. "+(price))
    }

//    println("Are you sure you want to add this item?")
//    val read=readLine("Press Y or N").toLowerCase
//    if(read.matches("n"))
//    {
//      println("No item added to cart")
//      callAgain()
//    }
//    else
    finalToCart(sel_opt-1,qty)

  }

  def getCartDetails(): Unit ={

    printTable()
    val sel_opt:Int=readLine("Select Id for the Item you want to add").toInt
    viewoptions(sel_opt)
  }

  def addToCart(): Unit ={
    println("Select From The Following items:")
    getCartDetails()
  }

  def printToBeupdatedCart(): Unit = {
    var obj:Seq[Seq[Any]]=Seq[Seq[Any]]()
    var total:Double=0
    for (x <- hm_cart) {
      obj = obj :+ Seq(x._1, local_lb(x._1).name, x._2,(x._2*(local_lb(x._1).amount)))
      total+=(local_lb(x._1).amount * x._2)
    }
    obj = Seq("Id", "Name", "Qty","Price") +: obj
    obj = obj :+ Seq("-","-","-",total)
    println(Tabulator.format(obj))
  }

  def showCurrentStats(id: Int): Unit = {
    println("Item Name: "+local_lb(id).name)
    println("Current Quantity: "+hm_cart.get(id).get)
    val x=readLine("Want to Update or Remove Completely (U/R)? ").toLowerCase
    if(x.matches("r")){

          hm_cart.remove(id)
          if(hm_discountCart.contains(id))
            hm_discountCart.remove(id)

          println("item removed")
        }

    else if(x.matches("u")){
      val newQty=readLine("Enter Quantity you want: ").toDouble
      if(newQty==0.0){
        hm_cart.remove(id)
        println("item removed")
      }
      else {

        hm_cart.update(id, newQty)

        val b= sendDataToAdmin(id,newQty)
        if(b.matches("soap")){
          var x=(newQty/4).toInt
          println(x+ "Soaps added extra as Offer")
          if(!hm_discountCart.contains(0)){
            //          println("price here")
            hm_discountCart+=(0 -> x)
          }
          else{
            //          println("Control here")
            hm_discountCart.update(0,hm_discountCart.get(0).get+x)
          }
        }
        else if(b.matches("old")){
          val x=(newQty/2).toInt
          val disc=(0.2)*(newQty*local_lb(id).amount)

          println("Discount of Rs. "+disc+" given as offer, Discount will be applied during checkout")
          if(!hm_discountCart.contains(1))
            hm_discountCart+=(1 -> disc)
          else{
            hm_discountCart.update(1,hm_discountCart.get(1).get+disc)
          }
        }
      }
      println("Cart Updated")
    }

  }

  def updateCart(): Unit = {
    if(hm_cart.isEmpty){
      println("Cart is Empty")
      callAgain()
    }
    else{
    printToBeupdatedCart()
    val id=readLine("Select Id to update").toInt
    showCurrentStats(id)

    callAgain()
    }
  }

  def finalizeOrder(): Unit = {
    println("Your Final Cart Details are: ")
    println
    viewCart(1)
    val ans=readLine("Want to update any Item in your Cart (Y/N) ?").toLowerCase
    if(ans.matches("y"))
      updateCart
    else if(ans.matches("n"))
      {
        val x=readLine("Are you sure to Finalize your order (Y/N) ?").toLowerCase()
        if(x.matches("n"))
          callAgain()
        else if(x.matches("y")){
          println("Thanx for Shopping with us :)")
          println("Your order summary is:")
          val total=viewCart(1)

          println("Your Final total after applying discount is "+(total-hm_discountCart.get(1).get))


          if(hm_discountCart.size!=0){
            println("In extras You got")
            if(hm_discountCart.contains(0))
              println(hm_discountCart.get(0).get+ " Soaps")
            if(hm_discountCart.contains(1))
              println(hm_discountCart.get(1).get+ " Rs Discount on Final order")

          }
          System.exit(0)
        }

      }
  }

  def shwSpecDetails(id:Int): Unit = id match {
    case 1 => addToCart()
    case 2 => updateCart()
    case 3 => viewCart()
    case 4 => finalizeOrder()
    case 5 => {println("Thanx for Shopping with us :)");System.exit(0)}
    case _ => {println("Invalid Input");callAgain()}

  }

  def makeConnection() = {
    try {
      s = new Socket("127.0.0.1", 9000)
    }
    catch {
      case e : java.net.ConnectException => {println("Switch on the Admin First");System.exit(0)}
    }
  }

  var s:Socket=_
   var dos:DataOutputStream=_
  var dis:DataInputStream=_

  def startStreams() = {
    dos=new DataOutputStream(s.getOutputStream)
    dis=new DataInputStream(s.getInputStream)
  }

  def sendDataViaStreams() = {
    val str=dis.readUTF()
    if(str.matches("helloadmin"))
      println("Connected to Admin")
    else
      println("Switch on the Admin First")
  }

  def addDiscounts(): Unit = {
    hm_discounts.put(0,"Buy 4 Lux soaps and Get 1 free")
    hm_discounts.put(1,"Buy 2 Old Spice Deos and get 20% off ")
  }

  def main(args: Array[String]): Unit = {
    println("Hii! Welcome to the H & M Store")

    addDiscounts()
    makeConnection()
    startStreams()
    sendDataViaStreams()
    val id=getSelOption()
    shwSpecDetails(id)
  }
}
