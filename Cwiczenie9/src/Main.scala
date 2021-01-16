/**
 *
 *  @author Shkred Artur S15444
 *
 */

object Main {

  def main(args: Array[String]): Unit = {
    //=============== Zadanie 1 ===============//
    println("=============== Zadanie 1 ===============")
    val container = new Container[String]("Hello, World!")
    println(container.getContent)
    println(container.applyFunction((str: String) => "length: " + str.length))
    //=============== Zadanie 2 ===============//
    println("=============== Zadanie 2 ===============")
    val no = new No
    val yes = new Yes[String]("s15444")
    println("obiekt 'no' jest podtypem Maybe[_]: " + no.isInstanceOf[Maybe[_]])
    println("obiekt 'yes' jest podtypem Maybe[_]: " + yes.isInstanceOf[Maybe[_]])
    //=============== Zadanie 3 ===============//
    println("=============== Zadanie 3 ===============")
    val ExtendWithApplyFunctionForNo: ExtendWithApplyFunction[No] = new ExtendWithApplyFunction[No](new No())
    ExtendWithApplyFunctionForNo.applyFunction(a => a)
    println(ExtendWithApplyFunctionForNo.getContent)
    val ExtendWithApplyFunctionForYes: ExtendWithApplyFunction[Yes[String]] = new ExtendWithApplyFunction[Yes[String]](new Yes("s15444"))
    ExtendWithApplyFunctionForYes.applyFunction(a => new Yes("length: " + a.getContent.length))
    println(ExtendWithApplyFunctionForYes.getContent.getContent)
    //=============== Zadanie 4 ===============//
    println("=============== Zadanie 4 ===============")
    val ExtendWithGetOrElseForNo: ExtendWithGetOrElse[No] = new ExtendWithGetOrElse[No](new No())
    println(ExtendWithGetOrElseForNo.getOrElse)
    val ExtendWithGetOrElseForYes: ExtendWithGetOrElse[Yes[String]] = new ExtendWithGetOrElse[Yes[String]](new Yes("s15444"))
    println(ExtendWithGetOrElseForYes.getOrElse)
    //=========================================//
  }
}
//--------------- Zadanie 1 ---------------//
class Container[A](private val value: A) {
  def getContent: A = value
  def applyFunction[R] (fn: A => R) : R = fn(value)
}
//--------------- Zadanie 2 ---------------//
trait Maybe[A]
class No extends Maybe[Nothing]
class Yes[A](value: A) extends Maybe[A] {
  private val _value: A = value
  def getContent: A = _value
}
//--------------- Zadanie 3 ---------------//
class ExtendWithApplyFunction[A](value: A) {
  private var _value: A = value
  def getContent: A = _value
  def applyFunction(function: A => A): A = {
    function(_value) match {
      case _: No =>
        _value
      case _: Yes[_] =>
        _value = function(_value)
        _value
      case _ =>
        null.asInstanceOf[A]
    }
  }
}
//--------------- Zadanie 4 ---------------//
class ExtendWithGetOrElse[A](value: A) {
  private val _value: A = value
  def getContent: A = _value
  def getOrElse[B]: B = {
    _value match {
      case _: No =>
        "class No with no content".asInstanceOf[B]
      case _: Yes[_] =>
        _value.asInstanceOf[Yes[A]].getContent.asInstanceOf[B]
      case _ =>
        null.asInstanceOf[B]
    }
  }
}
//-----------------------------------------//

/** NBD Ćwiczenia 9 – Scala typy i funkcje parametryzowane
 * 1. Zaimplementuj klasę Container parametryzowaną typem A. Konstruktor klasy powinien przyjmować pojedynczą prywatną
 *    wartość wskazanego typu. Klasa powinna implementować następujące metody:
 *      a. getContent zwracającą przechowywaną wartość
 *      b. applyFunction przyjmującą funkcję typu A=>R i zwracającą wynik działania funkcji na zawartości kontenera
 * 2. Zaimplementuj trait Maybe parametryzowany typem A i dwie dziedziczące z niego klasy – klasę No rozszerzającą Maybe[Nothing]
 *    i klasę Yes parametryzowaną typem A i przechowującą pojedynczy obiekt wskazanego typu. Stwórz obiekty obu klas.
 *    Sprawdź, czy oba są podtypem Maybe[_] (do sprawdzenia tego służy metoda isInstanceOf parametryzowana sprawdzanym typem).
 * 3. Rozszerz rozwiązanie zadania 3 o funkcję applyFunction typu A=>R i zwracającą:
 *      a. dla No: No
 *      b. dla Yes: Yes(f(zawartość yes))
 * 4. Rozszerz rozwiązanie zadania 3 o funkcję getOrElse parametryzowaną typem i zwracającą:
 *      a. Dla No – parametr getOrElse
 *      b. Dla Yes – zawartość Yes
 */