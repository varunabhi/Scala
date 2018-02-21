object calculator extends App {

  def func(a:Double,b:Double,c:Char):Double= c match {
    case '+' =>  a+b
    case '-' =>  a-b
    case '/' =>  a/b
    case '*' =>  a*b
  }

  println(func(2,5,'+'))
  println(func(34,12,'-'))
  println(func(3,9,'*'))
  println(func(45,5,'/'))

}
