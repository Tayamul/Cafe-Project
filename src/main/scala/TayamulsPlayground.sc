import model.{Customer, ItemQuality, ItemType, MenuItem, Order}
import services.OrderService.calculateTotal
//import model.Order
//
//sealed trait ItemType
//
//object ItemType {
//  case object ColdDrinks extends ItemType
//
//  case object HotDrinks extends ItemType
//
//  case object ColdFood extends ItemType
//
//  case object HotFood extends ItemType
//}
//
//println(ItemType.ColdDrinks)
//
//trait MenuItem {
//  def name: String
//
//  def price: Double
//
//  def description: String
//
//  def itemType: ItemType
//}
//
//trait PremiumItem {
//  def name: String
//
//  def price: Double
//
//  def description: String
//}
//
//
////case class ColdDrinks (name: String, price: Double, description: String) extends MenuItem
////case class HotDrinks (name: String, price: Double, description: String) extends MenuItem
////case class ColdFood (name: String, price: Double, description: String) extends MenuItem
//case class HotFood(name: String, price: Double, description: String) extends MenuItem {
//  val itemType = ItemType.HotFood
//}
//
//case class ColdFood(name: String, price: Double, description: String) extends MenuItem {
//  val itemType = ItemType.ColdFood
//}
//
//case class ColdDrinks(name: String, price: Double, description: String) extends MenuItem {
//  val itemType = ItemType.ColdDrinks
//}
//
//case class HotDrinks(name: String, price: Double, description: String) extends MenuItem {
//  val itemType = ItemType.HotDrinks
//}
//
//val sandwich: ColdFood = ColdFood("tuna sandwich", 2, "a delicious tuna sandwich")
//
//val panini = HotFood(name = "hand&cheese panini", price = 3.50, description = "Hot and delicious panini")
//println(panini.itemType)
//println(panini)
//println(panini.name)
////
////val het: Haaeted = Haaeted("hah", 12, "adfsgsdfg")
////println(het)
//val coke: ColdDrinks = ColdDrinks("Coke", 1.50, "Cool fizzy drink")
//val fanta: ColdDrinks = ColdDrinks("Fanta", 1.50, "Cool fizzy drink")
//val sprite: ColdDrinks = ColdDrinks("Sprite", 1.50, "Cool fizzy drink")
//val coffee: HotDrinks = HotDrinks("Americano", 2, "A hot beverage")
//val latte: HotDrinks = HotDrinks("Latte", 2, "A hot beverage")
//val hotChocolate: HotDrinks = HotDrinks("Hot chocolate", 2, "A hot beverage")
//val tunaSandwich: ColdFood = ColdFood("Tuna sandwich", 3, "A tuna sandwich")
//val tomatoSoup: HotFood = HotFood("Tomato Soup", 3, "A delicious tomato soup")
//
//
////println(coke)
////println(fanta)
////println(sprite)
//
//
//case class Lobster(name: String, price: Double, description: String) extends PremiumItem
//
//val lobster: Lobster = Lobster("Lobster", 30, "A fine lobster!")
//println(lobster)
//
//
//case class Menu(items: List[MenuItem], premiumItems: List[PremiumItem]) {
//  // variables and methods!
//  def addStandardItem() = ???
//
//  def addPremiumItem() = ???
//
//  def removePremiumItem() = ???
//
//}
//
//val menu = Menu(List(coke, fanta, sprite, coffee, latte, hotChocolate, tunaSandwich, tomatoSoup), List(lobster))
//println(menu)
//
//
//case class OrderItem(item: MenuItem, quantity: Int, customer: Option)
//

object BillingService extends App {
  val coffee: MenuItem = MenuItem(name = "coffee", price = 3.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val greenTea: MenuItem = MenuItem(name = "green tea", price = 2.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val decaf: MenuItem = MenuItem(name = "decaf", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val latte: MenuItem = MenuItem(name = "latte", price = 4.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)
  val caramelLatte: MenuItem = MenuItem(name = "caramel latte", price = 2.7, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)

  // Cold Drinks
  val icedCoffee: MenuItem = MenuItem(name = "iced coffee", price = 3.5, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val lemonade: MenuItem = MenuItem(name = "lemonade", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val smoothie: MenuItem = MenuItem(name = "smoothie", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)
  val icedTea: MenuItem = MenuItem(name = "iced tea", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val gourmetSmoothie: MenuItem = MenuItem(name = "gourmet smoothie", price = 5.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)

  // Hot Food
  val pancakes: MenuItem = MenuItem(name = "pancakes", price = 5.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFoods)
  val eggsBenedict: MenuItem = MenuItem(name = "eggs benedict", price = 8.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFoods)
  val grilledCheese: MenuItem = MenuItem(name = "grilled cheese sandwich", price = 4.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFoods)
  val veggieBurger: MenuItem = MenuItem(name = "veggie burger", price = 7.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFoods)
  val steakSandwich: MenuItem = MenuItem(name = "steak sandwich", price = 10.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFoods)

  // Cold Food
  val caesarSalad: MenuItem = MenuItem(name = "caesar salad", price = 6.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFoods)
  val yogurtParfait: MenuItem = MenuItem(name = "greek yogurt parfait", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFoods)
  val hamCheeseSandwich: MenuItem = MenuItem(name = "ham and cheese sandwich", price = 5.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFoods)
  val fruitSalad: MenuItem = MenuItem(name = "fruit salad", price = 4.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFoods)
  val smokedSalmonBagel: MenuItem = MenuItem(name = "smoked salmon bagel", price = 8.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFoods)

  val hotDrinks: List[MenuItem] = List(coffee, greenTea, decaf, latte, caramelLatte)
  val coldDrinks: List[MenuItem] = List(icedCoffee, lemonade, smoothie, icedTea, gourmetSmoothie)
  val hotFoods: List[MenuItem] = List(pancakes, eggsBenedict, grilledCheese, veggieBurger, steakSandwich)
  val coldFoods: List[MenuItem] = List(caesarSalad, yogurtParfait, hamCheeseSandwich, fruitSalad, smokedSalmonBagel)

  val tom: Customer = Customer("Tom", 22, None)
  val newOrder: Order = Order(List(coffee, hamCheeseSandwich), tom)

  val newTotal = calculateTotal(newOrder)
  println(newTotal)

  /** Ability to add a custom additional service charge, either in addition to or instead of the
   * optional automatically applied service charge. */
  def customAdditionalServiceCharge(order: Order, optionalCharge: Option[Double]) = {

  }

  println(customAdditionalServiceCharge(newOrder, Some(20)))

}