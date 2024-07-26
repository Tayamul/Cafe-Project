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
      //    "fail to add a stamp" when {
      //      "the customer has already made a purchase once today" in {
      //        val initialCard = DrinksLoyaltyCard(stamps = 10)
      //        val tom: Customer = Customer("Tom", 22, Some(initialCard))
      //        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
      //        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(stamps = 10))
      //        }
      //    }     test not written yet(placeholder)
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
  }

  "addStarsToDiscountCard" should {
    "add a star to the customer discount card" when {
      "a purchase over £20 is made" in {
        val initialCard = DiscountLoyaltyCard(stars = 3, totalSpent = 20.00)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 25.00)
        updatedTom.loyaltyCard shouldBe Some(DiscountLoyaltyCard(4, 45.00))
      }
    }
    "not add a star or the running total spend to the customer discount card" when {
      "a purchase under £20 is made" in {
        val initialCard = DiscountLoyaltyCard(stars = 3, totalSpent = 10.00)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 10.00)
        updatedTom.loyaltyCard shouldBe Some(DiscountLoyaltyCard(3, 10.00)) // should this be 10 or 20? Is the total added even if stars aren't?
      }
    }
    "not modify the customer" when {
      "the customer does not have a DiscountLoyaltyCard" in {
        val tom: Customer = Customer("Tom", 22, None)
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 25.00)
        updatedTom.loyaltyCard shouldBe None
      }
    }
    "not modify the customer" when {
      "the loyalty card is not a DiscountLoyaltyCard" in {
        val initialCard = DrinksLoyaltyCard(stamps = 5)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 5.00)
        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(5))
      }
    }
  }

  //  "checkLoyaltyEligibility" should {
  //    "confirm a customer is eligible for DrinksLoyaltyCard" when {
  //    "customer has purchased less than 5 times and is over 18" in {
  //      ???
  //    }
  // }
  //    "refuse eligibility for DrinksLoyaltyCard" when {
  //    "customer has purchased more than 5 times and is over 18" in {
  //      ???
  //    }
  // }
  //    "customer has purchased less than 5 times and is under 18" in {
  //      ???
  //    }
  // }
  //    "customer has purchased more than 5 times and is under 18" in {
  //      ???
  //    }
  // }
  //    "customer already has DiscountLoyaltyCard" in {
  //      ???
  //    }
  // }


  //This part doesn't make sense?  * In order to quality for this discount card, their total spend over a minimum of 5 purchases needs to be £150. E.g. if a customer purchased 4 times, each a minimum of £20, on 4 different  days, they will be entitled to a discount of 8% on every purchase (regardless of * total price).
  //Should this mean £100 not £150??

  //    "confirm a customer is eligible for DiscountLoyaltyCard" when {
  //    "customer has made at least 5 times and spent over £150 in total" in {
  //      ???
  //    }
  // }
  //    "refuse eligibility for DiscountLoyaltyCard" when {
  //    "customer has made less than 5 purchases and spent over £150 in total" in {
  //      ???
  //    }
  // }
  //    "customer has made at least 5 purchases and spent under £150 in total" in {
  //      ???
  //    }
  // }
  //    "customer has purchased more than 5 times and is under 18" in {
  //      ???
  //    }
  // }
  //    "customer already has DrinksLoyaltyCard" in {
  //      ???
  //    }
  // }
  //  }
  //
  //throw an error / notify when a customer already has one type of loyalty card
  //not be eligible is already have other card [eithers here], error message to show customer already has a loyalty card
  // also not eligible if purchased less than 5 times
  // also not eligible if under 18
  //
  //  "calculateLoyaltyDiscount" should {
  //    "calculate the total discount including the loyalty bonus" when {
  //      "a purchase is made for ..." in {
  //        ???
  //      }
  //    }
  //  }

}