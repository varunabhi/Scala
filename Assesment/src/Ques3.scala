object Ques3 {
  val list1=List('g','o','o','d',' ','m','o','r','n','i','n','g')
  val list2=List('h','o','w','a','r','e','y','o','u')



  def func_sort(a:List[Char],b:List[Char]):List[Char]={

    var list3=a.filter(!b.contains(_))

     return list3


  }

  def main(args: Array[String]): Unit = {
    var list_ans=func_sort(list2,list1)
    println(list_ans)
  }

}
