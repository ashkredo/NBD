/**
 *
 *  @author Shkred Artur S15444
 *
 */

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
  //=============== Zadanie 1 ===============//
    println("=============== Zadanie 1 ===============")
    val days = List[String]("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    // -- 1a
    println("--- #1a")
    println(getListItemsByFor(days))
    // -- 1b
    println("--- #1b")
    println(getListItemsByForStartsWithP(days))
    // -- 1c
    println("--- #1c")
    println(getListItemsByWhile(days))
  //=============== Zadanie 2 ===============//
    println("=============== Zadanie 2 ===============")
    // -- 2a
    println("--- #2a")
    println(recursive(days))
    // -- 2b
    println("--- #2b")
    println(backwardsRecursive(days))
  //=============== Zadanie 3 ===============//
    println("=============== Zadanie 3 ===============")
    println(tailRecursive(days))
  //=============== Zadanie 4 ===============//
    println("=============== Zadanie 4 ===============")
    // -- 4a
    println("--- #4a")
    println(getListItemsByFoldl(days))
    // -- 4b
    println("--- #4b")
    println(getListItemsByFoldr(days))
    // -- 4c
    println("--- #4c")
    println(getListItemsByFoldlStartsWithP(days))
  //=============== Zadanie 5 ===============//
    println("=============== Zadanie 5 ===============")
    var products = Map("Mleko" -> 4.0, "Kawa" -> 100.00)
    println("Przed rabatem: " + products.map(_.productIterator.mkString(":")).mkString("; "))
    //val products2 = products.transform((_, cost) => cost * 0.9)
    var products2 = getDiscount(products, 10);
    println("Po rabacie: " + products2.map(_.productIterator.mkString(":")).mkString("; "))
  //=============== Zadanie 6 ===============//
    println("=============== Zadanie 6 ===============")
    println(getTupleValues(!false, 10.0f, 100))
  //=============== Zadanie 7 ===============//
    println("=============== Zadanie 7 ===============")
    println(getMapItemValueByKey(products, "Kawa"))
  //=============== Zadanie 8 ===============//
    println("=============== Zadanie 8 ===============")
    val integerValues = List[Int](1, 0, 4, 9, 2, 4, 4, 0, 0, 5, 0, 0, 3, 10, 0)
    println("Przed usunięciem zer z listy: " + integerValues)
    println("Po usunięciu zer z listy: " + getListWithoutZeros(integerValues))
  //=============== Zadanie 9 ===============//
    println("=============== Zadanie 9 ===============")
    val integerValues2  = List[Int](99, 2, 0, 9, 2, 3, 3, 0, 0, 1)
    println("Przed zwiększeniem liczb listy o 1: " + integerValues2)
    println("Po zwiększeniu liczb listy o 1: " + getIncrementedList(integerValues2))
  //=============== Zadanie 10 ==============//
    println("=============== Zadanie 10 ==============")
    val realNumbers  = List[Double](-1.2, 3.4, 5.5, -9.0, 1.6, 3.7, -3.0, 34.1)
    println("Lista przed filtrowaniem: " + realNumbers)
    println("Lista po filtrowaniu: " + getAbsoluteValuesList(realNumbers, -5.0, 12.0))
  //=========================================//
  }
//--------------- Zadanie 1 ---------------//
// -- 1a
  def getListItemsByFor(list: List[String]): String = {
    var resultStr:String = ""
    for(item <- list) {
      resultStr += (if(item != list.head) ", " else "") + item
    }
    resultStr
  }
// -- 1b
  def getListItemsByForStartsWithP(list: List[String]): String = {
    var resultStr:String = ""
    for(item <- list if item.startsWith("P")) {
      resultStr += (if(item != list.head) ", " else "") + item
    }
    resultStr
  }
// -- 1c
  def getListItemsByWhile(list: List[String]): String = {
    var resultStr:String = ""
    var i:Int = 0
    while(i < list.length) {
      resultStr += (if(list(i) != list.head) ", " else "") + list(i)
      i+=1
    }
    resultStr
  }
//--------------- Zadanie 2 ---------------//
// -- 2a
  def recursive(list: List[String]): String = {
    if (list.length == 1) {
      list.head
    } else if (!list.isEmpty) {
      list.head + ", " + recursive(list.tail)
    } else {
      throw new Exception("List is Empty")
    }
  }
// -- 2b
  def backwardsRecursive(list: List[String]): String = {
    if (list.length == 1) {
      list.head
    } else if (!list.isEmpty) {
      backwardsRecursive(list.tail) + ", " + list.head
    } else {
      throw new Exception("List is Empty")
    }
  }
//--------------- Zadanie 3 ---------------//
  @tailrec def tailRecursive(list: List[String], result: String = ""): String = {
    if (list.head != list.last) {
      // println(result.concat(list.head))
      tailRecursive(list.tail, result.concat(list.head) + ", ")
    } else {
      result.concat(list.head)
    }
  }
//--------------- Zadanie 4 ---------------//
// -- 4a
  def getListItemsByFoldl(list: List[String]): String = {
    list.foldLeft("")((A, B) =>
      A + (if(B != list.head) ", " else "") + B
    )
  }
// -- 4b
  def getListItemsByFoldr(list: List[String]): String = {
    list.foldRight("")((A, B) =>
      (if(A != list.head) ", " else "") + A + B
    )
  }
// -- 4c
  def getListItemsByFoldlStartsWithP(list: List[String]): String = {
    list.foldLeft("")((A, B) =>
      A +(if (B.startsWith("P")) {
            ((if(B != list.head) ", " else "") + B)
          } else { "" })
    )
  }
//--------------- Zadanie 5 ---------------//
  def getDiscount(map: Map[String, Double], percent: Double): Map[String, Double] = {
    map.view.mapValues(cost => {
      cost - cost * 0.01 * percent
    }).toMap
  }
//--------------- Zadanie 6 ---------------//
  def getTupleValues(tuple: (Any, Any, Any)): String = {
    (tuple._1.getClass.getSimpleName + ": " + tuple._1 + ";\n" +
      tuple._2.getClass.getSimpleName + ": " + tuple._2 + ";\n" +
      tuple._3.getClass.getSimpleName + ": " + tuple._3 + ";")
  }
//--------------- Zadanie 7 ---------------//
  def getMapItemValueByKey(map: Map[String, Double], key: String): Option[Double] = {
    map.get(key)
  }
//--------------- Zadanie 8 ---------------//
  @tailrec
  def getListWithoutZeros(list: List[Int], result: List[Int] = List.empty[Int]): List[Int] = list match {
    case head::tail => {
      if (head != 0) {
        getListWithoutZeros(tail, result.appended(head))
      } else {
        getListWithoutZeros(tail, result)
      }
    }
    case Nil => result
  }
//--------------- Zadanie 9 ---------------//
  def getIncrementedList(list: List[Int]): List[Int] = {
    list.map(value => value + 1)
  }
//--------------- Zadanie 10 ---------------//
  def getAbsoluteValuesList(list: List[Double], min: Double, max: Double): List[Double] = {
    list.filter(value => value >= min && value <= max)
      .map(value => value.abs)
  }
//-----------------------------------------//
}

/** NBD Ćwiczenia 1 – Scala
 * W każdym wypadku rozwiązanie powinno zawierać oczywiście instrukcje pozwalające wypisać wyniki itp.
 * 1. Stwórz 7 elementową listę zawierającą nazwy dni tygodnia.
 *    Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
 *    a. Pętli for
 *    b. Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
 *    c. Pętli while
 * 2. Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
 *    a. Funkcji rekurencyjnej
 *    b. Funkcji rekurencyjnej wypisując elementy listy od końca
 * 3. Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa
 *    zawierającego elementy listy z ćwiczenia 1
 * 4. Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
 *    a. Metody foldl
 *    b. Metody foldr
 *    c. Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”
 * 5. Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen.
 *    Wykorzystaj mechanizm mapowania kolekcji.
 * 6. Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
 * 7. Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
 * 8. Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).
 *    Wykorzystaj rekurencję.
 * 9. Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę,
 *    w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
 * 10.Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą
 *    wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie
 */
