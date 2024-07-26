package model

import java.time.LocalDate

case class DrinksLoyaltyCard(val stamps: Int = 0, lastActionDate: Option[LocalDate] = None) extends LoyaltyCard
