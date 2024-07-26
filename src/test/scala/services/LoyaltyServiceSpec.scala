package services

import model._

import java.time.LocalDate
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.LoyaltyService.checkEligibility

class LoyaltyServiceSpec extends AnyWordSpec with Matchers {


  "addStampsToDrinksCard" should {
    "add a stamp to the customer drinks card" when {
      "a drink is purchased" in {
        val today = LocalDate.now()
        val initialCard = DrinksLoyaltyCard(stamps = 5)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(stamps = 6, Some(today)))
      }
    }
    "fail to add a stamp" when {
      "the customer already has 10 stamps" in {
        val initialCard = DrinksLoyaltyCard(stamps = 10)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe Some(DrinksLoyaltyCard(stamps = 10))
      }
      "the customer has already made a purchase once today" in {
        val today = LocalDate.now()
        val lastActionDate = Some(today)
        val initialCard = DrinksLoyaltyCard(stamps = 8, lastActionDate)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStampToDrinksCard(tom)
        updatedTom.loyaltyCard shouldBe Some(initialCard)
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
        val today = LocalDate.now()
        val initialCard = DiscountLoyaltyCard(stars = 3, totalSpent = 20.00)
        val tom: Customer = Customer("Tom", 22, Some(initialCard))
        val updatedTom: Customer = LoyaltyService.addStarsToDiscountCard(tom, 25.00)
        updatedTom.loyaltyCard shouldBe Some(DiscountLoyaltyCard(4, 45.00, Some(today)))
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

  "hasALoyaltyCard" should {
    "return a Left" when {
      "a customer already has a Loyalty Card" in {

      }
    }

    "return a Right" when {
      "a customer does not have a Loyalty Card" in {

      }
    }
  }

  private val tom: Customer = Customer("Tom", 22, None, purchaseHistory = 7)
  private val jake: Customer = Customer("Jake", 28, loyaltyCard = Some(DrinksLoyaltyCard(stamps = 8, lastActionDate = Some(LocalDate.now()))), purchaseHistory = 8)
  private val mike: Customer = Customer("Mike", 28, Some(DiscountLoyaltyCard(stars = 2, totalSpent = 19, lastActionDate = Some(LocalDate.now()))), purchaseHistory = 5)
  private val farah: Customer = Customer("Farah", 28, None, purchaseHistory = 2)
  private val ariel: Customer = Customer("Ariel", 17, None)

  "checkEligibility" should {
    "return a Right" when {
      "a customer does not have a loyalty card, is over 18 and has made more than 5 purchases" in {
        checkEligibility(tom) shouldEqual Right(s"${tom.name} is eligible for a loyalty card.")
      }
    }

    "return a Left" when {
      "a customer has a DrinksLoyaltyCard" in {
        checkEligibility(jake) shouldEqual Left(false)
      }
      "a customer has a DiscountLoyaltyCard" in {
        checkEligibility(mike) shouldEqual Left(false)
      }
      "a customer is under the required age" in {
        checkEligibility(ariel) shouldEqual Left(false)
      }
      "a customer's order purchase is less than 5" in {
        checkEligibility(farah) shouldEqual Left(false)
      }
    }
  }


  //  "checkLoyaltyEligibility" should {
  //    "confirm a customer is eligible for DrinksLoyaltyCard" when {
  //      "customer has purchased more than 5 times and is over 18" in {
  //        val tom: Customer = Customer("Tom", 22, None, List.fill(5)(Purchase(5.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "drinks")
  //        isEligible shouldBe true
  //      }
  //    }
  //    "refuse eligibility for DrinksLoyaltyCard" when {
  //      "customer has purchased less than 5 times and is over 18" in {
  //        val tom: Customer = Customer("Tom", 22, None, List.fill(4)(Purchase(5.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "drinks")
  //        isEligible shouldBe false
  //      }
  //      "customer is under 18" in {
  //        val tom: Customer = Customer("Tom", 17, None, List.fill(5)(Purchase(5.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "drinks")
  //        isEligible shouldBe false
  //      }
  //      "customer already has a DiscountLoyaltyCard" in {
  //        val tom: Customer = Customer("Tom", 22, Some(DiscountLoyaltyCard(3, 5.00)), List.fill(5)(Purchase(5.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "drinks")
  //        isEligible shouldBe false
  //      }
  //    }
  //    "confirm a customer is eligible for DiscountLoyaltyCard" when {
  //      "customer has made at least 5 purchases and spent over £150 in total" in {
  //        val tom: Customer = Customer("Tom", 22, None, List.fill(5)(Purchase(30.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "discount")
  //        isEligible shouldBe true
  //      }
  //    }
  //    "refuse eligibility for DiscountLoyaltyCard" when {
  //      "customer has made less than 5 purchases" in {
  //        val tom: Customer = Customer("Tom", 22, None, List.fill(4)(Purchase(40.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "discount")
  //        isEligible shouldBe false
  //      }
  //      "customer has made at least 5 purchases but spent under £150 in total" in {
  //        val tom: Customer = Customer("Tom", 22, None, List.fill(5)(Purchase(20.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "discount")
  //        isEligible shouldBe false
  //      }
  //      "customer is under 18" in {
  //        val tom: Customer = Customer("Tom", 17, None, List.fill(5)(Purchase(30.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "discount")
  //        isEligible shouldBe false
  //      }
  //      "customer already has a DrinksLoyaltyCard" in {
  //        val tom: Customer = Customer("Tom", 22, Some(DrinksLoyaltyCard(5)), List.fill(5)(Purchase(30.00)))
  //        val isEligible = LoyaltyService.checkLoyaltyEligibility(tom, "discount")
  //        isEligible shouldBe false
  //      }
  //    }
  //  }

  //  "calculateLoyaltyDiscount" should {
  //    "calculate the total discount including the loyalty bonus" when {
  //      "a purchase over £20 is made with no premium items" in {
  //        val initialCard = DiscountLoyaltyCard(stars = 4, totalSpent = 100.00)
  //        val tom: Customer = Customer("Tom", 22, Some(initialCard))
  //        val total = LoyaltyService.calculateLoyaltyDiscount(tom, 50.00, 0.00)
  //        total shouldBe 50.00 * 0.92 // 4 stars * 2% discount = 8% discount
  //      }
  //      "a purchase over £20 is made with some premium items" in {
  //        val initialCard = DiscountLoyaltyCard(stars = 8, totalSpent = 200.00)
  //        val tom: Customer = Customer("Tom", 22, Some(initialCard))
  //        val total = LoyaltyService.calculateLoyaltyDiscount(tom, 50.00, 20.00)
  //        total shouldBe 20.00 + (30.00 * 0.84) // 8 stars * 2% discount = 16% discount
  //      }
  //      "a purchase under £20 is made" in {
  //        val initialCard = DiscountLoyaltyCard(stars = 4, totalSpent = 100.00)
  //        val tom: Customer = Customer("Tom", 22, Some(initialCard))
  //        val total = LoyaltyService.calculateLoyaltyDiscount(tom, 10.00, 0.00)
  //        total shouldBe 10.00 // No discount applied
  //      }
  //    }
  //  }

}
