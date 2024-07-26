package model

import java.time.LocalDate

case class DiscountLoyaltyCard(stars: Int, totalSpent: Double, lastActionDate: Option[LocalDate] = None) extends LoyaltyCard
