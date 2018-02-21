object fact_rec {
    def get_fact(num:Int): Int ={

      if (num==0)
        return 1

      else
        return num*get_fact(num-1)

    }

  def main(args: Array[String]): Unit = {
      println("Factorial is "+get_fact(8))
  }

}
