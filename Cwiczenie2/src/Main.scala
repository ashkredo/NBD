/**
 *
 *  @author Shkred Artur S15444
 *
 */

object Main {

  def main(args: Array[String]): Unit = {
  //=============== Zadanie 1 ===============//
    println("=============== Zadanie 1 ===============")
    println("Czwartek: " + getDayInfo("Czwartek") + ";")
    println("Wakacjek: " + getDayInfo("Wakacjek") + ";")
    println("Sobota: " + getDayInfo("sobota") + ";")
  //=============== Zadanie 2 ===============//
    println("=============== Zadanie 2 ===============")
    // -- Konto Bankowe 1
    println("--- Konto Bankowe 1 --- (początkowy stan konta na 0)")
    var kontoBankowe1 = new KontoBankowe()
    println("Stan konta 1: " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wplata(150000)
    println("Stan konta 1: " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wyplata(143700)
    println("Stan konta 1: " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wyplata(7000)
    println("Stan konta 1: " + kontoBankowe1.getStanKonta)
    // -- Konto Bankowe 2
    println("--- Konto Bankowe 2 --- (przyjmujący początkowy stan konta)")
    var kontoBankowe2 = new KontoBankowe(5000052)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)
    kontoBankowe2.wyplata(5000000)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)
    kontoBankowe2.wplata(10000)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)
  //=============== Zadanie 3 ===============//
    println("=============== Zadanie 3 ===============")
    var osoba1 = new Osoba("Elizabeth", "Lincoln")
    var osoba2 = new Osoba("Charlotte", "Cooper")
    var osoba3 = new Osoba("Marie", "Delamare")
    var osoba4  = new Osoba("Victoria", "Ashworth")

    var listOsob = List[Osoba](osoba1, osoba2, osoba3, osoba4);
    for(osoba <- listOsob) {
      println(osoba.imie + " " + osoba.nazwisko + ": " + patternMatching(osoba))
    }
  //=============== Zadanie 4 ===============//
    println("=============== Zadanie 4 ===============")
    println("Zastosowane trzykrotnie funkcje mnożenia na 9 do wartości 4: " + useFunctionThreeTimes(4, multiplyByNine));
  //=============== Zadanie 5 ===============//
    println("=============== Zadanie 5 ===============")
    val nowaOsoba = new Osoba2("Imie", "Nazwisko")
    println("Podatek Osoby: " + (nowaOsoba.podatek * 100) + "% pensji;")
    val student = new Osoba2("Charlotte", "Cooper") with Student
    println("Podatek Studenta: " + (student.podatek * 100) + "% pensji;")
    val pracownik = new Osoba2("Marie", "Delamare") with Pracownik
    println("Podatek Pracownika: " + (pracownik.podatek * 100) + "% pensji;")
    val nauczyciel = new Osoba2("Victoria", "Ashworth") with Nauczyciel
    println("Podatek Nauczyciela: " + (nauczyciel.podatek * 100) + "% pensji;")
    val nauczycielPracownik = new Osoba2("Victoria", "Ashworth") with Nauczyciel with Pracownik
    println("Podatek Nauczyciela-Pracownika: " + (nauczycielPracownik.podatek * 100) + "% pensji;")
    val pracownikNauczyciel = new Osoba2("Marie", "Delamare") with Pracownik with Nauczyciel
    println("Podatek Pracownika-Nauczyciela: " + (pracownikNauczyciel.podatek * 100) + "% pensji;")
  //=========================================//
  }
//--------------- Zadanie 1 ---------------//
  def getDayInfo(day: String): String = day.toUpperCase match {
    case "PONIEDZIAŁEK"  =>   "Praca"
    case "WTOREK"        =>   "Praca"
    case "ŚRODA"         =>   "Praca"
    case "CZWARTEK"      =>   "Praca"
    case "PIĄTEK"        =>   "Praca"
    case "SOBOTA"        =>   "Weekend"
    case "NIEDZIELA"     =>   "Weekend"
    case  _              =>   "Nie ma takiego dnia"
  }
//--------------- Zadanie 3 ---------------//
  case class Osoba(imie: String, nazwisko: String)
  //def patternMatching(osoba: Osoba) = osoba.nazwisko match {
  //  case "Lincoln" => "Cześć Marie Delamare & Charlotte Cooper!"
  //  case _ => "Dzień dobry!"
  //}
  def patternMatching(osoba: Osoba) = osoba match {
    case Osoba("Elizabeth", "Lincoln") => "Cześć Marie Delamare & Charlotte Cooper!"
    case Osoba("Marie", _) => "Dzień dobry Elizabeth Lincoln!"
    case Osoba(_, "Cooper") => "Witam Elizabeth Lincoln!"
    case default => "Ja też tu jestem, cześć..."
  }
//--------------- Zadanie 4 ---------------//
  def multiplyByNine(value:Int):Int = { value * 9 }
  def useFunctionThreeTimes(value: Int, func: Int => Int): Int = {
//    var result:Int = value
//    for (n <- 1 to 3) {
//      result = func(result)
//    }
//    result
    func(func(func(value)))
  }
//-----------------------------------------//
}
//--------------- Zadanie 2 ---------------//
class KontoBankowe() {
  private var stanKonta:Double = 0
  def getStanKonta = { stanKonta }

  def this(stanKonta:Double){
    this()
    this.stanKonta = stanKonta
  }

  def wplata(value: Double) {
    stanKonta += value
    println(value + " zostało wpłacone na konto.")
  }

  def wyplata(value: Double) {
    if (value <= stanKonta) {
      stanKonta -= value
      println(value + " zostało wypłacone z konta.")
    } else {
      println("Brak wystarczających środków na koncie (" + value + ").")
    }
  }
}
//--------------- Zadanie 5 ---------------//
  class Osoba2() {
    private var _imie: String = ""
    private var _nazwisko: String = ""
    private var _podatek: Double = 0

    def this(imie: String, nazwisko: String){
      this()
      this._imie = imie
      this._nazwisko = nazwisko
      this._podatek = 0
    }

    def imie : String = { _imie }
    def nazwisko : String = { _nazwisko }
    def podatek : Double = { _podatek }
  }

  trait Student extends Osoba2 {
    override def podatek:Double = 0.0
  }

  trait Pracownik extends Osoba2 {
    private var _pensja:Double = 0

    private def setPensja(value: Double) {
      _pensja = value
    }

    private def getPensja: Double = if(_pensja > 0) { _pensja * podatek } else { 0 }

    override def podatek: Double = 0.20
  }

  trait Nauczyciel extends Pracownik {
    override def podatek: Double = 0.10
  }
//-----------------------------------------//

/** NBD Ćwiczenia 2 – Scala
 * W każdym wypadku rozwiązanie powinno zawierać oczywiście instrukcje pozwalające wypisać wyniki itp.
 * 1. Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String. Dla stringów odpowiadających nazwom dni tygodnia
 *    funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów „Nie ma takiego dnia”.
 * 2. Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz własnością stanKonta - własność ma być tylko do odczytu.
 *    Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi, ustawiający początkowy stan konta na 0.
 * 3. Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy. Zdefiniuj funkcję, która przyjmuje
 *    obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie danej osoby. Zdefiniuj 2-3
 *    różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.
 * 4. Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
 *    Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.
 * 5. Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności read only: imie, nazwisko,
 *    podatek. Pracownik powinien mieć własność pensja (z getterem i seterem). Student i Pracownik powinni przesłaniać własność
 *    podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji. Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek
 *    zwraca 10% pensji. Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla każdego z nich. Stwórz obiekty z traitami
 *    Student i Pracownik, pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
 */
