abstract class Ques1 {
   var math_marks:Int
  var phy_marks:Int
  var chem_marks:Int

  def avg:Int

}

trait maharashtra{
  var hindi:Int
  var marathi:Int
}
trait punjab{
  var punjabi:Int
  var hindi:Int
}
trait haryana{
  var french:Int
  var sanskrit:Int

}

class Student1 extends Ques1 with punjab{
  override var math_marks: Int = 98
  override var phy_marks: Int = 73
  override var chem_marks: Int = 88
  override var punjabi: Int = 92
  override var hindi: Int = 90

  override def avg: Int = (this.chem_marks+this.hindi+this.math_marks+this.phy_marks+this.punjabi)/5
}

class Student2 extends Ques1 with maharashtra {
  override var math_marks: Int = 98
  override var phy_marks: Int = 73
  override var chem_marks: Int = 88

  override var hindi: Int = 90
  override var marathi: Int = 88

  override def avg: Int =(this.chem_marks+this.hindi+this.marathi+this.math_marks+this.phy_marks)/5
}

class Student3 extends Ques1 with haryana {
  override var math_marks: Int = 98
  override var phy_marks: Int = 73
  override var chem_marks: Int = 88
  override var french: Int = 92
  override var sanskrit: Int = 80

  override def avg: Int = (this.chem_marks+this.french+this.math_marks+this.phy_marks+this.sanskrit)/5
}

object demo extends App{
  println(new Student1().avg)
  println(new Student2().avg)
  println(new Student3().avg)

}