import xml._

object xml_searching extends App {

  val xml_file=XML.loadFile("/home/varun/IdeaProjects/xml_import/src/file1.xml")
  val stu=(xml_file \ "student")
  println(stu)

  //for printing Individual Student
  val stus=stu.map(i => (i \ "@name").text)
  stus.foreach(println)

  println("Number of Students are "+stu.length)
  val quiz1=(xml_file \ "quiz")
//  println(quiz1)
  val quiz2=(xml_file \\ "quiz")
//  println(quiz2)
  val name=(xml_file \ "student" \ "@name")
//  println(name)
}
