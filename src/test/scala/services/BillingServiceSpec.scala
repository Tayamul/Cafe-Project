package services

package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


import scala.language.postfixOps


class BillingServiceSpec extends AnyWordSpec with Matchers {

  //  private val customer: Customer = Customer("Tom", 22)
  //  private val items: List[MenuItem] = List(Items.coffee, Items.latte)

  "calculateServiceCharge" should {
    "create a return of type Order" in {
      createOrder(items, customer) shouldBe isInstanceOf[Order]
    }

  }


  "calculateLoyaltyDiscount" should {
    "return the total amount of all items" in {
      val newOrder: Order = createOrder(items, customer)
      calculateTotal(newOrder) shouldEqual 7.5
    }
  }


  "generateBill" should {
    "return the total amount of all items" in {
      val newOrder: Order = createOrder(items, customer)
      calculateTotal(newOrder) shouldEqual 7.5
    }
  }

}
