package services

import model._
import services._

object LoyaltyService {

  def addStampToDrinksCard(customer: Customer): Customer = {
    customer.loyaltyCard match {
      case Some(card: DrinksLoyaltyCard) if card.stamps < 10 =>
        val updatedCard = card.copy(stamps = card.stamps + 1)
        customer.copy(loyaltyCard = Some(updatedCard))

      case _ => customer
    }
  }

  //  def addStarsToDiscountCard
  //
  //  def checkLoyaltyEligibility
  //
  //  def calculateLoyaltyDiscount

  // Test case 1: Adding a stamp to a DrinksLoyaltyCard with less than 10 stamps
  // Test case 2: Not modifying the customer if the DrinksLoyaltyCard already has 10 stamps
  // Test case 3: Not modifying the customer if the customer does not have a DrinksLoyaltyCard
  // Test case 4: Not modifying the customer if the loyalty card is not a DrinksLoyaltyCard


}

/** Loyalty scheme:
 * o A customer can have one of two types of loyalty card – they cannot have both. Either a
 * drinks loyalty card or a discount loyalty card.
 * o A customer can apply for a loyalty card but are not eligible if they already have the
 * other card, have purchased from the café less than 5 times or are under 18.
 * Drinks loyalty card – every time they purchase a drink, they receive a stamp (maximum
 * of 1 per day), on the 10th visit their drink is free.
 * o Discount loyalty card - every time their bill comes to more than £20 (once per day) they
 * receive a star. Each star will generate a discount on the total bill (before service charge
 * is applied) of 2%.
 * § The maximum amount of stars is 8, once 8 is reached they will not receive any
 * more stars but the total % discount of 16% will be taken off each bill over £20
 * (not including service charge).
 * § Premium menu items are excluded from this discount.
 * § In order to quality for this discount card, their total spend over a minimum of 5
 * purchases needs to be £150.
 * § E.g. if a customer purchased 4 times, each a minimum of £20, on 4 different
 * days, they will be entitled to a discount of 8% on every purchase (regardless of
 * total price). They can redeem this discount with every purchase, multiple times
 * per day. On their 5th purchase of minimum £20 (after discount scheme), they
 * will receive their 8% discount and a star (taking them to 5). */