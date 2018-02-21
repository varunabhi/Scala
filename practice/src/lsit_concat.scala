object list_concat extends App {
  var n=List(1,2,3)
  var p=List(33)
  var m=5::6::n
  var q=m::n
  var j=m:::n:::p
  println(n)
  println(m)
  println(q)
  println(j)

  var b=n:+p
  println(b)



}
