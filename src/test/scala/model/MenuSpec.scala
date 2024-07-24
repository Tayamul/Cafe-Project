package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MenuSpec extends AnyWordSpec with Matchers {

  // Hot Drink
  private val coffee: MenuItem = MenuItem(name = "coffee", price = 3.5, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)
  private val greenTea: MenuItem = MenuItem(name = "green tea", price = 2.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)

  // Cold Drinks
  private val icedCoffee: MenuItem = MenuItem(name = "iced coffee", price = 3.5, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  private val lemonade: MenuItem = MenuItem(name = "lemonade", price = 3.0, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)

  // Hot Food
  private val pancakes: MenuItem = MenuItem(name = "pancakes", price = 5.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFoods)
  private val eggsBenedict: MenuItem = MenuItem(name = "eggs benedict", price = 8.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFoods)

  // Cold Food
  private val caesarSalad: MenuItem = MenuItem(name = "caesar salad", price = 6.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFoods)
  private val yogurtParfait: MenuItem = MenuItem(name = "greek yogurt parfait", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFoods)

  private val hotDrinks: List[MenuItem] = List(coffee, greenTea)
  private val coldDrinks: List[MenuItem] = List(icedCoffee, lemonade)
  private val hotFoods: List[MenuItem] = List(pancakes, eggsBenedict)
  private val coldFoods: List[MenuItem] = List(caesarSalad, yogurtParfait)

  private val testMenu: Menu = Menu(hotDrinks, coldDrinks, hotFoods, coldFoods)

  "createMenu" should {
    "populate item list" when {
      "method is called" in {

      }
    }
  }

}
