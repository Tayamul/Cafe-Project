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
    "should contain 8 items" when {
      "method is called" in {
        testMenu.createMenu.length shouldBe 8
      }
    }
    "should contain coffee" when {
      "method is called" in {
        testMenu.createMenu.contains(coffee)
      }
    }
    "populate menu items" when {
      "method is called" in {
        testMenu.createMenu shouldEqual hotDrinks ++ coldDrinks ++ hotFoods ++ coldFoods
      }
    }
  }

  "addMenuItem" should {
    "take the new item hot chocolate" when {
      val hotChocolate: MenuItem = MenuItem(name = "hot chocolate", price = 7.5, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)
      "add a hot drink item to the hotDrinks list" in {
        testMenu.addMenuItem(hotChocolate) shouldEqual Right(Menu(hotDrinks :+ hotChocolate, coldDrinks, hotFoods, coldFoods))
      }
      "ensure the new hotDrink item is added to the updated list count" in {
        val initialLength = testMenu.hotDrinks.length
        val updateLength = testMenu.addMenuItem(hotChocolate)
        updateLength match {
          case Right(newMenu) =>
            newMenu.hotDrinks.length shouldEqual (initialLength + 1)
          case Left(error) => "Invalid item type!"
        }
      }
    }

    "take the new item frappuccino" when {
      val frappuccino: MenuItem = MenuItem(name = "frappuccino", price = 9.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)
      "add a cold drink item to the coldDrinks list" in {
        testMenu.addMenuItem(frappuccino) shouldEqual Right(Menu(hotDrinks, coldDrinks :+ frappuccino, hotFoods, coldFoods))
      }
      "ensure the new coldDrink item is added to the updated list count" in {
        val initialLength = testMenu.coldDrinks.length
        val updatedLength = testMenu.addMenuItem(frappuccino)
        updatedLength match {
          case Right(newMenu) =>
            newMenu.coldDrinks.length shouldEqual (initialLength + 1)
          case Left(error) => "Invalid item type!"
        }

      }
    }

    "take the new item smash burger" when {
      val smashBurger: MenuItem = MenuItem(name = "smash burger", price = 17.5, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFoods)
      "add a hot food item to the hotFoods list" in {
        testMenu.addMenuItem(smashBurger) shouldEqual Right(Menu(hotDrinks, coldDrinks, hotFoods :+ smashBurger, coldFoods))
      }
      "ensure the new hotFood item is added to the updated list count" in {
        val initialLength = testMenu.hotFoods.length
        val updatedLength = testMenu.addMenuItem(smashBurger)
        updatedLength match {
          case Right(newMenu) =>
            newMenu.hotFoods.length shouldEqual (initialLength + 1)
          case Left(error) => "Invalid item type!"
        }
      }
    }

    "take the new item hot chocolate" when {
      val tunaSandwich: MenuItem = MenuItem(name = "tuna sandwich", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFoods)
      "add a cold food item to the coldFoods list" in {
        testMenu.addMenuItem(tunaSandwich) shouldEqual Right(Menu(hotDrinks, coldDrinks, hotFoods, coldFoods :+ tunaSandwich))
      }
      "ensure the new coldFood item is added to the updated list count" in {
        val initialLength = testMenu.coldFoods.length
        val updatedLength = testMenu.addMenuItem(tunaSandwich)
        updatedLength match {
          case Right(newMenu) =>
            newMenu.coldFoods.length shouldEqual (initialLength + 1)
          case Left(error) => "Invalid item type!"
        }
      }
    }
  }

  "removeMenuItem" should {
    "remove item in hot foods" in {
      val initialLength = testMenu.hotFoods.length
      val updatedLength = testMenu.removeMenuItem(pancakes)
      updatedLength match {
        case Right(newMenu) =>
          newMenu.hotFoods.length shouldBe (initialLength - 1)
        case Left(error) => "Invalid item type!"
      }
    }
  }
  "remove item in cold foods" in {
    val coldFoodsTotal = testMenu.coldFoods
    val coldFoodsExists = testMenu.removeMenuItem(yogurtParfait)
    coldFoodsExists match {
      case Right(newMenu) =>
        newMenu.coldFoods shouldBe List(caesarSalad)
      case Left(error) => "Invalid item type!"
    }
  }
}
