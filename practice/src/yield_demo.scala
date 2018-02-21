object yield_demo extends App {
var b= List.range(1,10)
  var c=for (x <- b) yield x*2
  println(b)
  println(c)
}
