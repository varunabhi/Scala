class queueDS {

  val ls = scala.collection.mutable.ListBuffer.empty[Int]

  def enQueue(k:Int)=ls.append(k)

  def deQueue():Int={
    var a=ls.head
    ls.trimStart(1)
    return a
  }

  def display()={
  println("Elements in Queue are")
    for(k <- ls)
      print(k+" ")
  }

}
object demo_queue{
  def main(args: Array[String]): Unit = {
    val obj= new queueDS
    obj.enQueue(util.Random.nextInt(100))
    obj.enQueue(util.Random.nextInt(100))
    obj.enQueue(util.Random.nextInt(100))
    obj.enQueue(util.Random.nextInt(100))
    obj.enQueue(util.Random.nextInt(100))
    obj.enQueue(util.Random.nextInt())
    obj.display()
    println()

    obj.deQueue()
    obj.deQueue()
    obj.display()
  }
}