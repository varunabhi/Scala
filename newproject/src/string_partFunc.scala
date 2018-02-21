object string_partFunc {

     def string_rev(str:String*)={
       for(s1 <- str){
         var b=s1.reverse
         println(b)
       }

     }

  def main(args: Array[String]): Unit = {
      string_rev("Varun")
       println()
      string_rev("Varun","Abhi")
      println()
      string_rev("Varun","Abhi","Hashmap")

  }

}
