/** Testing MVP branch */
// Jamie update

sealed trait ColdDrinks

object ColdDrinks {
  case object Coke extends ColdDrinks
  case object Sprite extends ColdDrinks
  case object Beer extends ColdDrinks
  case object Cocktail extends ColdDrinks

}


case class MENU(drink: ColdDrinks)

// Example usage
val cokeMenu = MENU(ColdDrinks.Coke)
val spriteMenu = MENU(ColdDrinks.Sprite)
val beerMenu = MENU(ColdDrinks.Beer)
val cocktailMenu = MENU(ColdDrinks.Cocktail)





sealed trait ItemQuality

object ItemQuality {
  case object Standard extends ItemQuality
  case object Premium extends ItemQuality
}

sealed trait ItemType

object ItemType {
  case object ColdDrink extends ItemType
  case object HotDrink extends ItemType
  case object ColdFood extends ItemType
  case object HotFood extends ItemType
}

case class Menu(
                 name: String,
                 Price: Double,
                 itemQuality: ItemQuality,
                 itemType: ItemType
               )

object Menu {

  val standardItems: List[Menu] = List(
    Menu("Coke", 1.50, ItemQuality.Standard, ItemType.ColdDrink),
    Menu("Sprite", 1.50, ItemQuality.Standard, ItemType.ColdDrink),
    Menu("Beer", 7.50, ItemQuality.Standard, ItemType.ColdDrink),
    Menu("Cocktail", 12.50, ItemQuality.Standard, ItemType.ColdDrink),
    Menu("Latte", 4.75, ItemQuality.Standard, ItemType.HotDrink),
    Menu("Americano", 4.00, ItemQuality.Standard, ItemType.HotDrink),
    Menu("Breakfast Tea", 3.75, ItemQuality.Standard, ItemType.HotDrink),
    Menu("Green Tea", 3.75, ItemQuality.Standard, ItemType.HotDrink)
  )

  val premiumItems: List[Menu] = List(
    Menu("Stella", 1.50, ItemQuality.Premium, ItemType.ColdDrink),
    Menu("Pin Colada", 1.50, ItemQuality.Premium, ItemType.ColdDrink),
    Menu("Red Wine", 3.00,ItemQuality.Premium, ItemType.ColdDrink),
    Menu("White Wine", 5.00, ItemQuality.Premium, ItemType.ColdDrink)
  )


  def getMenuItems: List[Menu] = standardItems ++ premiumItems

  case class MenuItem(
                       name: String,
                       price: Double,
                       isHot: Boolean = false,
                       isFood: Boolean = false,
                       isPremium: Boolean = false
                     )
  case class Order(items: List[MenuItem])

  case class Bill(
                   order: Order,
                   total: Double,
                   serviceCharge: Double)