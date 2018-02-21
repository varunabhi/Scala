trait Hi { def hi: Unit }
trait A extends Hi { abstract override def hi = { println("A");super.hi} }
trait B extends Hi { abstract override def hi = { println("B") ; super.hi } }

class NoHi extends Hi { override def hi = { println("NoHi");} }

class C extends NoHi with A with B

object TraitsSample1 extends App {
  new C().hi
}