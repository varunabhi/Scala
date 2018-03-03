import scala.collection.mutable
import scala.collection.mutable.ListBuffer






object UserSide{


  var hm_cart=new mutable.HashMap[Int,Double]() //clear was not available in simple Map


  def getSelOption():Int  ={
    println("Select your Preferred option:")
    println("1. Add items to Cart")
    println("2. Update your Cart")
    println("3. Remove items From Cart")
    println("4. View Cart")
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
    println("Size is "+local_lb.length)

    var local_list=local_lb.map(formatToList).toList
    local_list=List("ID","Name","Amount")::local_list
    return local_list
  }

  def finalToCart(opt:Int,qty:Double): Unit ={

      hm_cart+=(opt -> qty)
    println("Item Added")

    val id=getSelOption()
    shwSpecDetails(id)

  }


  def callAgain(): Unit = {
    val id=getSelOption()
    shwSpecDetails(id)
  }

  def viewCart(): Unit ={

//    println("size is "+hm_cart.size)
    var obj:Seq[Seq[Any]]=Seq[Seq[Any]]()
    for(x <- hm_cart){
      obj=obj:+Seq(x._1,local_lb(x._1).name,x._2)
    }
    obj=Seq("Id","Name","Qty")+:obj
    println(Tabulator.format(obj))

    callAgain()
  }

  def resetCart(): Unit ={
    hm_cart.clear()
  }

  def viewoptions(sel_opt: Int): Unit = {
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
      println("Final price is Rs. "+(qty*obj.amount))
    }

    println("Are you sure you want to add this item?")
    val read=readLine("Press Y or N").toLowerCase
    if(read.matches("n"))
    {
      println("No item added to cart")
      getCartDetails()
    }
    else
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
    for(x <- hm_cart){
      obj=obj:+Seq(x._1,local_lb(x._1).name,x._2)
    }
    obj=Seq("Id","Name","Qty")+:obj
    println(Tabulator.format(obj))

  }

  def showCurrentStats(id: Int): Unit = {
    println("Item Name: "+local_lb(id).name)
    println("Current Quantity: "+hm_cart.get(id))
    val x=readLine("Want to Change Quantity(Y/N)? ").toLowerCase
    if(x.matches("n")){
      val b=readLine("Want to remove this completely (Y/N) ?").toLowerCase()
      if(b.matches("y"))
        {
          hm_cart.remove(id)
          println("item removed")
        }
    }
    else if(x.matches("y")){
      val newQty=readLine("Enter Quantity you want: ").toDouble
      hm_cart.update(id,newQty)
      println("Cart Updated")
    }

  }

  def updateCart(): Unit = {
    printToBeupdatedCart()
    val id=readLine("Select Id to update").toInt
    showCurrentStats(id)
    callAgain()
  }

  def shwSpecDetails(id:Int): Unit = id match {
    case 1 => addToCart()
    case 2=> updateCart()
    case 4 => viewCart()

  }

  def main(args: Array[String]): Unit = {
    println("Hii! Welcome to the H & M Store")
    val id=getSelOption()
    shwSpecDetails(id)
  }
}
