
sealed trait ItemType

object ItemType {
  case object ColdDrinks extends ItemType

  case object HotDrinks extends ItemType

  case object ColdFood extends ItemType

  case object HotFood extends ItemType
}

println(ItemType.ColdDrinks)

trait MenuItem {
  def name: String

  def price: Double

  def description: String

  def itemType: ItemType
}

trait PremiumItem {
  def name: String

  def price: Double

  def description: String
}


//case class ColdDrinks (name: String, price: Double, description: String) extends MenuItem
//case class HotDrinks (name: String, price: Double, description: String) extends MenuItem
//case class ColdFood (name: String, price: Double, description: String) extends MenuItem
case class HotFood(name: String, price: Double, description: String) extends MenuItem {
  val itemType = ItemType.HotFood
}

case class ColdFood(name: String, price: Double, description: String) extends MenuItem {
  val itemType = ItemType.ColdFood
}

case class ColdDrinks(name: String, price: Double, description: String) extends MenuItem {
  val itemType = ItemType.ColdDrinks
}

case class HotDrinks(name: String, price: Double, description: String) extends MenuItem {
  val itemType = ItemType.HotDrinks
}

val sandwich: ColdFood = ColdFood("tuna sandwich", 2, "a delicious tuna sandwich")

val panini = HotFood(name = "hand&cheese panini", price = 3.50, description = "Hot and delicious panini")
println(panini.itemType)
println(panini)
println(panini.name)
//
//val het: Haaeted = Haaeted("hah", 12, "adfsgsdfg")
//println(het)
val coke: ColdDrinks = ColdDrinks("Coke", 1.50, "Cool fizzy drink")
val fanta: ColdDrinks = ColdDrinks("Fanta", 1.50, "Cool fizzy drink")
val sprite: ColdDrinks = ColdDrinks("Sprite", 1.50, "Cool fizzy drink")
val coffee: HotDrinks = HotDrinks("Americano", 2, "A hot beverage")
val latte: HotDrinks = HotDrinks("Latte", 2, "A hot beverage")
val hotChocolate: HotDrinks = HotDrinks("Hot chocolate", 2, "A hot beverage")
val tunaSandwich: ColdFood = ColdFood("Tuna sandwich", 3, "A tuna sandwich")
val tomatoSoup: HotFood = HotFood("Tomato Soup", 3, "A delicious tomato soup")


//println(coke)
//println(fanta)
//println(sprite)


case class Lobster(name: String, price: Double, description: String) extends PremiumItem

val lobster: Lobster = Lobster("Lobster", 30, "A fine lobster!")
println(lobster)


case class Menu(items: List[MenuItem], premiumItems: List[PremiumItem]) {
  // variables and methods!
  def addStandardItem() = ???

  def addPremiumItem() = ???

  def removePremiumItem() = ???

}

val menu = Menu(List(coke, fanta, sprite, coffee, latte, hotChocolate, tunaSandwich, tomatoSoup), List(lobster))
println(menu)


case class OrderItem(item: MenuItem, quantity: Int, customer: Option)