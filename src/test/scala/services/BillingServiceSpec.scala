package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.BillingService._

import scala.language.postfixOps


class BillingServiceSpec extends AnyWordSpec with Matchers {

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

  // Price Items
  private val smashBurger: MenuItem = MenuItem(name = "smash burger", price = 200.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFoods)
  private val chickenBurger: MenuItem = MenuItem(name = "chicken burger", price = 200.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFoods)

  private val tom: Customer = Customer("Tom", 22, None)
  //  private val gary: Customer = Customer("Gary", 22, None)
  //  private val items: List[MenuItem] = List(Items.coffee, Items.latte)

  "calculateServiceCharge" should {
    "calculate service charge at 25%" when {
      "order contains only one premium item" in {
        val newOrder: Order = Order(List(yogurtParfait, pancakes), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 2.38
      }
      "order contains multiple premium items" in {
        val newOrder: Order = Order(List(yogurtParfait, pancakes, eggsBenedict, lemonade), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 5.13
      }
    }
    "calculate service charge at 25% and cap at 40" in {
      val newOrder: Order = Order(List(smashBurger), tom)
      calculateServiceCharge(newOrder, None) shouldEqual 40.0
    }
    "calculate service charge at 20%" when {
      "order contains only one hot food item, but no premium" in {
        val newOrder: Order = Order(List(pancakes), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 1.0
      }
      "order contains multiple items including hot food, but no premium" in {
        val newOrder: Order = Order(List(pancakes, greenTea), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 1.5
      }
    }
    "calculate service charge at 20% and cap at 20" in {
      val newOrder: Order = Order(List(chickenBurger), tom)
      calculateServiceCharge(newOrder, None) shouldEqual 20.0
    }
    "calculate service charge at 10%" when {
      "order contains hot drinks" in {
        val newOrder: Order = Order(List(greenTea), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 0.25
      }
      "order contains cold food" in {
        val newOrder: Order = Order(List(caesarSalad), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 0.6
      }
      "order contains cold food and hot drinks" in {
        val newOrder: Order = Order(List(caesarSalad, greenTea), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 0.85
      }
    }
    "calculate no service charge" when {
      "order contains only cold drinks and no premium items" in {
        val newOrder: Order = Order(List(icedCoffee), tom)
        calculateServiceCharge(newOrder, None) shouldEqual 0.0
      }
    }
    "calculate the additional custom service charge" when {
      "the custom service charge isAdditive" in {
        val newOrder: Order = Order(List(icedCoffee), tom)
        calculateServiceCharge(newOrder, Some(CustomServiceCharge(30, isAdditive = true))) shouldEqual 1.05
      }
      "the custom service charge isAdditive and when the food is hot" in {
        val newOrder: Order = Order(List(icedCoffee, pancakes), tom)
        calculateServiceCharge(newOrder, Some(CustomServiceCharge(30, isAdditive = true))) shouldEqual 4.25
      }
      "the custom service charge isAdditive and when premium hot food is included" in {
        val newOrder: Order = Order(List(eggsBenedict, pancakes), tom)
        calculateServiceCharge(newOrder, Some(CustomServiceCharge(30, isAdditive = true))) shouldEqual 7.15
      }
      "custom service charge !isAdditive" in {
        val newOrder: Order = Order(List(icedCoffee), tom)
        calculateServiceCharge(newOrder, Some(CustomServiceCharge(30, isAdditive = false))) shouldEqual 1.05
      }
    }
  }

  //  "calculateLoyaltyDiscount" should {
  //    "return the total amount of all items" in {
  //      val newOrder: Order = createOrder(items, customer)
  //      calculateTotal(newOrder) shouldEqual 7.5
  //    }
  //  }
  //
  //
  //  "generateBill" should {
  //    "return the total amount of all items" in {
  //      val newOrder: Order = createOrder(items, customer)
  //      calculateTotal(newOrder) shouldEqual 7.5
  //    }
  //  }

}
