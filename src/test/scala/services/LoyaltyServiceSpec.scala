package services

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class LoyaltyServiceSpec extends AnyWordSpec with Matchers {

  "addStampsToDrinksCard" should {
    "add a stamp to the customer drinks card" when {
      "a drink is purchased" in {
        val initialCard = DrinksLoyaltyCard(stamps = 5)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(stamps = 6))
      }
    }
    "fail to add a stamp" when {
      "the customer already has 10 stamps" in {
        val initialCard = DrinksLoyaltyCard(stamps = 10)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(stamps = 10))
      }
    }
    "not modify the customer" when {
      "the customer does not have a DrinksLoyaltyCard" in {
        val tom: Customer = Customer("Tom", 22, None)
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe None
      }
    }
    "not modify the customer" when {
      "the loyalty card is not a DrinksLoyaltyCard" in {
        val initialCard = DiscountLoyaltyCard(stars = 3, totalSpent = 5.00)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 5.00)
        updatedTom.loyaltyCard shouldBe Some(DiscountLoyaltyCard(3, 5.0))
      }
    }


  }

  "addStarsToDiscountCard" should {
    "add a star to the customer discount card" when {
      "a purchase over £20 is made" in {
        val initialCard = DiscountLoyaltyCard(stars = 3, totalSpent = 20.00)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 25.00)
        updatedTom.loyaltyCard shouldBe Some(DiscountLoyaltyCard(4, 45.0))
      }
    }
  }

  //  "checkLoyaltyEligibility" should {
  //    "confirm a customer is eligible for loyalty perks" in {
  //      ???
  //    }
  //  }
  //
  //
  //  "calculateLoyaltyDiscount" should {
  //    "calculate the total discount including the loyalty bonus" when {
  //      "a purchase is made for ..." in {
  //        ???
  //      }
  //    }
  //  }

}