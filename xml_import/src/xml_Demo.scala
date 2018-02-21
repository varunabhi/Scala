import xml._


object xml_Demo extends App {

  case class Grade(value:Int,comment:Option[String])
  case class Student(name:String,quizzes:List[Grade],tests:List[Grade],assignments:List[Grade])

  def toGrade(node:Node):Grade={    //What is Node
    val value=( node \ "@value").text.toInt
    val comments= ( node \ "comment").map(_.text)
    val comment= if(comments.isEmpty) None else Some(comments.head) //Head??
    Grade(value,comment)
  }

  def toStudent(node:Node):Student={    //What is Node
    val name=( node \ "@name").text
    val quizzes= ( node \ "quiz").map(toGrade).toList
    val tests= ( node \ "test").map(toGrade).toList
    val assignments= ( node \ "assignment").map(toGrade).toList
      Student(name,quizzes,tests,assignments)
  }

  val gradeXML=XML.loadFile("/home/varun/IdeaProjects/xml_import/src/file1.xml")
  val students=(gradeXML \ "student").map(toStudent)

  students.foreach(println)

}
