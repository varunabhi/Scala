//THIS IS USER SIDE, SWITCH ON THE ADMIN FIRST TO ACCESS DISCOUNTS



import java.io._
import java.net.Socket

import javax.swing.{JFrame, JOptionPane}
import javax.xml.parsers.{DocumentBuilder, DocumentBuilderFactory}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer






object UserSide {
  var hm_discounts = new mutable.HashMap[Int, String]()

  var hm_discountCart = new mutable.HashMap[Int, Double]()

  var hm_cart = new mutable.HashMap[Int, Double]() //clear was not available in simple Map
  var hm_cart_dup = new mutable.LinkedHashMap[Int, mutable.LinkedHashMap[Int, Double]]()

  def getSelOption(): Int = {
    println("Select your Preferred option:")
    println("1. Add items to Cart")
    println("2. Update your Cart")
    println("3. View Cart")
    println("4. Finalize order")
    println("5. Exit")
    val selected = readLine().toInt
    selected
  }

  def printTable(opt: Int): Unit = {
    val b = refreshCartData(opt)
    println(Tabulator.format(b))
  }

  def formatToList(obj: dataStruct): List[Any] = {
    List(obj.uID, obj.name, obj.amount)
  }


  var local_lb = readFromXml.importdata()

  def refreshCartData(opt: Int): List[List[Any]] = {

    val lb_catOpt = local_lb.filter(_.SuperCatId == opt)
    var local_list = lb_catOpt.map(formatToList).toList
    local_list = List("ID", "Name", "Amount") :: local_list
    return local_list
  }

  def finalToCart(opt: Int, opt_sub: Int, qty: Double): Unit = {

    if (hm_cart_dup.contains(opt)) {
      if (hm_cart_dup.get(opt).get.contains(opt_sub)){
        val old_qty=hm_cart_dup.get(opt).get.get(opt_sub).get
        hm_cart_dup.get(opt).get.update(opt_sub, old_qty+qty)
      }
      else{
        val temp=mutable.LinkedHashMap(opt_sub -> qty)
       hm_cart_dup.get(opt).get+=(opt_sub -> qty)
      }
    }

    else {
      val temp = mutable.LinkedHashMap(opt_sub -> qty)
      hm_cart_dup += (opt -> temp)
    }
    println("Item Added")

    val id = getSelOption()
    shwSpecDetails(id)

  }


  def callAgain(): Unit = {
    val id = getSelOption()
    shwSpecDetails(id)
  }

  var mapForView = new mutable.LinkedHashMap[String, Double]()

  def makeNewmap() = {
    totalFinal=0.0
    lstViewGlobal=null
    mapForView.clear()
    for (x <- hm_cart_dup) {

      var str = x._1.toString
      var lst = (x._2).keySet.toList
      for (y <- lst) {
        val stNew = str + y
        val qty = (x._2).get(y)
        mapForView += (stNew -> qty.get)
      }


    }
//    println("Following is the map")
//    mapForView.foreach(println)
  }


  def check(opt: Int, selOpt: Int, obj: dataStruct): Boolean = {
    if (obj.SuperCatId == opt && obj.uID == selOpt)
      return true
    else
      return false
  }

  import util.control.Breaks._

  def formatData(tuple: (String, Double)): List[Any] = {

    var str = tuple._1
    var opt = str(0).toInt - 48
    var selOpt = str(1).toInt - 48

      val uniqId=str.toInt

    val lb = local_lb.filter(check(opt, selOpt, _))
    val obj = lb(0)
    totalFinal+=obj.amount*tuple._2
    return List(uniqId,obj.name, obj.amount, tuple._2,(obj.amount*tuple._2))

  }


  var lstViewGlobal:Seq[Seq[Any]]=null
  var totalFinal=0.0
  def viewCart(x: Int = 0): Unit = {
    totalFinal=0
    makeNewmap()
    var total: Double = 0
    if (hm_cart_dup.isEmpty) {
      println("Cart is Empty")
      callAgain()
      return 0
    }
    else {

      var lst = mapForView.map(formatData(_)).toList
      lstViewGlobal = List("Unique Id","Name", "Amount", "Quantity","Total") :: lst
      lstViewGlobal=lstViewGlobal:+List("","","","",totalFinal)
      println(Tabulator.format(lstViewGlobal))

    }
    if(x==0)
    callAgain()
  }

    def resetCart(): Unit = {
      hm_cart.clear()
    }

    def sendDataToAdmin(i: Int, qty: Double): String = {
      dos.writeUTF("hello")
      dos.writeInt(i)
      dos.writeDouble(qty)

      val f = dis.readUTF()
      //println(f+" herehere")
      return f
    }


    def viewoptions(opt: Int, sel_opt: Int): Unit = {
      //    if(hm_discounts.contains(sel_opt-1)){
      //      println("Hurray Discount is Available")
      //      println("-----> "+hm_discounts.get(sel_opt-1).get)
      //    }
      val selObj = local_lb.filter(check(opt, sel_opt, _))

      val obj = selObj(0)
      var qty: Double = 0
      if (obj.uom == "kg") {
        println(obj.name + " are Rs. " + obj.amount + " per Kg")
        qty = readLine("Enter Weight in Kg you want: ").toDouble
        println("Final price is Rs. " + (qty * (obj.amount)))
      }
      else {
        println(obj.name + " is Rs. " + obj.amount + " per Unit")
        qty = readLine("Enter Number of Units you want: ").toInt
        var price = (qty * obj.amount)

        println("Final price is Rs. " + (price))
      }

      finalToCart(obj.SuperCatId, obj.uID, qty)

    }

    def getCartDetails(opt: Int): Unit = {

      printTable(opt)
      val sel_opt: Int = readLine("Select Id for the Item you want to add").toInt
      viewoptions(opt, sel_opt)
    }

    def addToCart(opt: Int): Unit = {
      println("Select From The Following items:")
      getCartDetails(opt)
    }

    def formattableForUpdate(tuple: (String,Double)):List[Any] ={
      val opt=(tuple._1)(0).toInt
      val selOpt=(tuple._1)(1)


     return List()
    }

    def printToBeupdatedCart(): Unit = {
      var obj: Seq[Seq[Any]] = Seq[Seq[Any]]()
      var total: Double = 0
      for(x <- mapForView){

      }
      obj = Seq("Id", "Name", "Qty", "Price") +: obj
      obj = obj :+ Seq("-", "-", "-", total)
      println(Tabulator.format(obj))
    }

    def showCurrentStats(id: Int): Unit = {
      val str=id.toString
      val opt=str(0).toInt-48
      val subOpt=str(1).toInt-48

      val lb=local_lb.filter(check(opt,subOpt,_))
      val obj=lb(0)
      val qty=hm_cart_dup.get(opt).get.get(subOpt).get

      println("Item Name: " + obj.name)
      println("Current Quantity: " + qty)
      val x = readLine("Want to Update or Remove Completely (U/R)? ").toLowerCase
      if (x.matches("r")) {

        hm_cart_dup.get(opt).get.remove(subOpt)
        if (hm_cart_dup.get(opt).get.isEmpty)
          hm_cart_dup.remove(opt)

        println("item removed")
      }

      else if (x.matches("u")) {
        val newQty = readLine("Enter Quantity you want: ").toDouble

        hm_cart_dup.get(opt).get.update(subOpt,newQty)

//        val b = sendDataToAdmin(id, newQty)
//        if (b.matches("soap")) {
//          var x = (newQty / 4).toInt
//          println(x + "Soaps added extra as Offer")
//          if (!hm_discountCart.contains(0)) {
//            //          println("price here")
//            hm_discountCart += (0 -> x)
//          }
//          else {
//            //          println("Control here")
//            hm_discountCart.update(0, x)
//          }
//        }
//        else if (b.matches("old")) {
//          val x = (newQty / 2).toInt
//          val disc = (0.2) * (newQty * local_lb(id).amount)
//
//          println("Discount of Rs. " + disc + " given as offer, Discount will be applied during checkout")
//          if (!hm_discountCart.contains(1))
//            hm_discountCart += (1 -> disc)
//          else {
//            hm_discountCart.update(1, disc)
//          }
//        }
      }
      println("Cart Updated")
    }


    def updateCart(): Unit = {
      if (hm_cart_dup.isEmpty) {
        println("Cart is Empty")
        callAgain()
      }
      else {
//        printToBeupdatedCart()
        println(Tabulator.format(lstViewGlobal))
        val Unid = readLine("Select Unique Id to update").toInt
        showCurrentStats(Unid)

        callAgain()
      }
    }

    def finalizeOrder(): Unit = {
      println("Your Final Cart Details are: ")
      println
      viewCart(1)
      val ans = readLine("Want to update any Item in your Cart (Y/N) ?").toLowerCase
      if (ans.matches("y"))
        updateCart
      else if (ans.matches("n")) {
        val x = readLine("Are you sure to Finalize your order (Y/N) ?").toLowerCase()
        if (x.matches("n"))
          callAgain()
        else if (x.matches("y")) {
          println("Thanx for Shopping with us :)")
          println("Your order summary is:")
          viewCart(1)
//          val total = viewCart() //This was double before
//          println("Catalogue also updated")

//          if (hm_discountCart.contains(1))
//            println("Your Final total after applying discount is " + (total - hm_discountCart.get(1).get))


//          if (hm_discountCart.size != 0) {
//            println("In extras You got")
//            if (hm_discountCart.contains(0))
//              println(hm_discountCart.get(0).get + " Soaps")
//            if (hm_discountCart.contains(1))
//              println(hm_discountCart.get(1).get + " Rs Discount on Final order")

          }
          System.exit(0)
        }

      }


    def showCategories(): Int = {
      println("Choose From The Following Categories")
      println("1. Beauty and Hygiene")
      println("2. Fruits")
      println("3. Drinks")
      val sel = readLine("Select Category:").toInt
      return sel
    }

    def shwSpecDetails(id: Int): Unit = id match {
      case 1 => val a = showCategories(); addToCart(a)
      case 2 => updateCart()
      case 3 => viewCart()
      case 4 => finalizeOrder()
      case 5 => {
        println("Thanx for Shopping with us :)");
        System.exit(0)
      }
      case _ => {
        println("Invalid Input");
        callAgain()
      }

    }

    def makeConnection() = {
      try {
        s = new Socket("127.0.0.1", 9000)
      }
      catch {
        case e: java.net.ConnectException => {
          println("Switch on the Admin First");
          System.exit(0)
        }
      }
    }

    var s: Socket = _
    var dos: DataOutputStream = _
    var dis: DataInputStream = _

    def startStreams() = {
      dos = new DataOutputStream(s.getOutputStream)
      dis = new DataInputStream(s.getInputStream)
    }

    def sendDataViaStreams() = {
      val str = dis.readUTF()
      if (str.matches("helloadmin"))
        println("Connected to Admin")
      else
        println("Switch on the Admin First")
    }

    def addDiscounts(): Unit = {
      hm_discounts.put(0, "Buy 4 Lux soaps and Get 1 free")
      hm_discounts.put(1, "Buy 2 Old Spice Deos and get 20% off ")
    }

    def main(args: Array[String]): Unit = {
      println("Hii! Welcome to the H & M Store")

      addDiscounts()
      makeConnection()
      startStreams()
      sendDataViaStreams()
      val id = getSelOption()
      shwSpecDetails(id)
    }
  }
