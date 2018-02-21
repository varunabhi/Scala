import scala.collection.mutable

object mapping_calendar extends App {
    val hm= new mutable.LinkedHashMap[String,Int]()
  hm+=("MONDAY" -> java.util.Calendar.MONDAY)
  hm+=("TUESDAY" -> java.util.Calendar.TUESDAY)
  hm+=("WEDNESDAY" -> java.util.Calendar.WEDNESDAY)
  hm+=("THURSDAY" -> java.util.Calendar.THURSDAY)
  hm+=("FRIDAY" -> java.util.Calendar.FRIDAY)
  hm+=("SATURDAY" -> java.util.Calendar.SATURDAY)
  hm+=("SUNDAY" -> java.util.Calendar.SUNDAY)

  println("Map Contains ")
  println(hm)

}
