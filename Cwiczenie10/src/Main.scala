/**
 *
 *  @author Shkred Artur S15444
 *
 */

object Main {

  def main(args: Array[String]): Unit = {
    //=============== Zadanie 1 ===============//
    println("=============== Zadanie 1 ===============")
    def pair: Iterator[(Int, Int)] = for {
      a <- Iterator.from(1)
      b <- 1 until a + 1 if a % b == 0
    } yield (a, b)

    println("Metoda next")
    val p = pair.buffered
    for(_ <- 1 to 20) {
      println(p.next)
    }

    println("Metoda take:")
    pair.take(20).foreach(println)
    //=============== Zadanie 2 ===============//
    println("=============== Zadanie 2 ===============")
    val test = for {
      a <- Yes("s15444")
    } yield a
    println(test)
    //=========================================//
  }
}
//--------------- Zadanie 2 ---------------//
class Maybe[A](var value: A) {
  def map[B](function: A => B): Maybe[B] = {
    val newValue = function(value)
    new Maybe(newValue)
  }
  def flatMap[B](function: A => Maybe[B]): Maybe[B] = {
    val newValue = function(value)
    newValue
  }
  override def toString: String = value.toString
}

class Yes[A](value: A) extends Maybe[A](value) {
  private val _value: A = value
  def getContent: A = _value
}

object Maybe {
  def apply[A](value: A): Maybe[A] = new Maybe(value)
}

object Yes {
  def apply[A](value: A): Yes[A] = new Yes(value)
}
//-----------------------------------------//

/** NBD Ćwiczenia 10 – Scala c.d.
 * 1. Zaimplementuj przy użyciu for generator kolejnych par (a,b) takich, że a jest liczbą całkowitą a b jest jej dzielnikiem.
 *    Zadbaj o to, by generator był leniwie ewaluowany. Przy użyciu metod take i next wypisz w kilku kolejnych wywołaniach 20
 *    pierwszych elementów ciągu.
 * 2. Zmodyfikuj klasę Maybe z ćwiczenia 9 tak, by implementowała metody map i flatMap. Zaprezentuj ich działanie.
 */
