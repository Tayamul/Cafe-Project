package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.OrderService.updatePurchaseHistory
import services._

import scala.language.postfixOps

class OrderServiceSpec extends AnyWordSpec with Matchers {

  private val customer: Customer = Customer("Tom", 22, None)
  private val customer2: Customer = Customer("Jake", 28, None, purchaseHistory = 3)
  private val items: List[MenuItem] = List(Items.coffee, Items.latte)

  "createOrder" should {
    //    "create a return of type Order" in {
    //      createOrder(items, customer) shouldBe isInstanceOf[Order]
    //    }
    "return the total customer's order" in {
      OrderService.createOrder(items, customer) shouldBe Order(List(Items.coffee, Items.latte), Customer("Tom", 22, None))
    }
    "return the total quantity of the items" in {
      val newOrder = OrderService.createOrder(items, customer)
      newOrder.items.length shouldEqual 2
    }
    "contain the item - coffee" in {
      val newOrder = OrderService.createOrder(items, customer)
      newOrder.items should contain(Items.coffee)
    }
    "not contain caeser salad" in {
      val newOrder = OrderService.createOrder(items, customer)
      newOrder.items should not contain (Items.caesarSalad)
    }
  }

  "updatePurchaseHistory" should {
    "increment a customer's purchase history by 1" when {
      "a new order is placed for a customer with no previous orders" in {
        val newOrder = OrderService.createOrder(items, customer)
        updatePurchaseHistory(newOrder).purchaseHistory shouldEqual 1
      }
      "a new order is placed for a customer with 3 previous orders" in {
        val newOrder = OrderService.createOrder(items, customer2)
        updatePurchaseHistory(newOrder).purchaseHistory shouldEqual 4
      }
    }
  }


  "calculateTotal" should {
    "return the total amount of all items" in {
      val newOrder: Order = OrderService.createOrder(items, customer)
      OrderService.calculateTotal(newOrder) shouldEqual 7.5
    }

    "return the correct amount of the total (7.5)" in {
      val newOrder: Order = OrderService.createOrder(items, customer)
      OrderService.calculateTotal(newOrder) should not equal 7
    }

  }
}
