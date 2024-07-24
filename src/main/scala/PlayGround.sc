/** Testing MVP branch */

sealed trait ColdDrinks

object ColdDrinks {
  case object COKE extends ColdDrinks
  case object SPRITE extends ColdDrinks
  case object BEER extends ColdDrinks
  case object COCKTAIL extends ColdDrinks

}


case class MENU(drink: ColdDrinks)

// Example usage
val cokeMenu = MENU(ColdDrinks.COKE)
val spriteMenu = MENU(ColdDrinks.SPRITE)
val beerMenu = MENU(ColdDrinks.BEER)
val cocktailMenu = MENU(ColdDrinks.COCKTAIL)


case class Menu(
                 name: String,
                 Price: Double,
                 itemType: MenuItemType
               )


sealed trait MenuItemType

object MenuItemType {
  case object Standard extends MenuItemType
  case object Premium extends MenuItemType
}

case class MenuItem(name: String, price: Double, itemType: MenuItemType)

object Menu {

  var standardItems: List[MenuItem] = List(
    MenuItem("Coke", 1.50, MenuItemType.Standard),
    MenuItem("Sprite", 1.50, MenuItemType.Standard),
    MenuItem("Beer", 3.00, MenuItemType.Standard),
    MenuItem("Cocktail", 5.00, MenuItemType.Standard)
  )

  var premiumItems: List[MenuItem] = List(
    MenuItem("Stella", 1.50, MenuItemType.Standard),
    MenuItem("Pin Colada", 1.50, MenuItemType.Standard),
    MenuItem("Red Wine", 3.00, MenuItemType.Standard),
    MenuItem("White Wine", 5.00, MenuItemType.Standard)
  )

  def getMenuItems: List[MenuItem] = standardItems ++ premiumItems