class valueClass(val a:Int) extends AnyVal {

  def calculate1(i:Int)=a*i
  def calculate2()=a*2

}

object demo{
  def main(args: Array[String]): Unit = {
    println(new valueClass(3).calculate1(5))
    println(new valueClass(6).calculate2())
  }

}
