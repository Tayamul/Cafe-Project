package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.OrderService._

import scala.language.postfixOps

class OrderServiceSpec extends AnyWordSpec with Matchers {

  private val customer: Customer = Customer("Tom", 22)
  private val items: List[MenuItem] = List(Items.coffee, Items.latte)

  "createOrder" should {
    //    "create a return of type Order" in {
    //      createOrder(items, customer) shouldBe isInstanceOf[Order]
    //    }
    "return the total customer's order" in {
      createOrder(items, customer) shouldBe Order(List(Items.coffee, Items.latte), Customer("Tom", 22))
    }
    "return the total quantity of the items" in {
      val newOrder = createOrder(items, customer)
      newOrder.items.length shouldEqual 2
    }
    "contain the item - coffee" in {
      val newOrder = createOrder(items, customer)
      newOrder.items should contain(Items.coffee)
    }
    "not contain caeser salad" in {
      val newOrder = createOrder(items, customer)
      newOrder.items should not contain (Items.caesarSalad)
    }
  }


  "calculateTotal" should {
    "return the total amount of all items" in {
      val newOrder: Order = createOrder(items, customer)
      calculateTotal(newOrder) shouldEqual 7.5
    }

    "return the correct amount of the total (7.5)" in {
      val newOrder: Order = createOrder(items, customer)
      calculateTotal(newOrder) should not equal 7
    }

  }
}
