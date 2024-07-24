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

object Menu {

  var standardItems: List[Menu] = List(
    Menu("Coke", 1.50, MenuItemType.Standard),
    Menu("Sprite", 1.50, MenuItemType.Standard),
    Menu("Beer", 3.00, MenuItemType.Standard),
    Menu("Cocktail", 5.00, MenuItemType.Standard)
  )

  var premiumItems: List[Menu] = List(
    Menu("Stella", 1.50, MenuItemType.Standard),
    Menu("Pin Colada", 1.50, MenuItemType.Standard),
    Menu("Red Wine", 3.00, MenuItemType.Standard),
    Menu("White Wine", 5.00, MenuItemType.Standard)
  )

  def getMenuItems: List[Menu] = standardItems ++ premiumItems