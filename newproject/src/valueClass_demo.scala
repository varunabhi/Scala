class valueClass_demo(val i:Int) extends AnyVal {

  def twice():Int={
    return i*2
  }
}

object valueClass_demo extends App{
  var obj = new valueClass_demo(5)
  println(obj.twice())
}
