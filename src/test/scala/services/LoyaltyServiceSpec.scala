package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class LoyaltyServiceSpec extends AnyWordSpec with Matchers {

  "addStampsToDrinksCard" should {
    "add a stamp to the customer drinks card" when {
      "a drink is purchased" in {
        ???
      }
    }

  }

  "addStarsToDiscountCard" should {
    "add a star to the customer discount card" when {
      "a purchase is made" in {
        ???
      }
    }
  }

  "checkLoyaltyEligibility" should {
    "confirm a customer is eligible for loyalty perks" in {
      ???
    }
  }


  "calculateLoyaltyDiscount" should {
    "calculate the total discount including the loyalty bonus" when {
      "a purchase is made for ..." in {
        ???
      }
    }
  }

}