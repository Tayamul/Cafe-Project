package model

sealed trait LoyaltyCard {
  case class DrinksLoyaltyCard(stamps: Int) extends LoyaltyCard

  case class DiscountLoyaltyCard(stars: Int, totalSpent: Double) extends LoyaltyCard
}
