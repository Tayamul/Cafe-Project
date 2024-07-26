package model

import java.time.LocalDate

case class DrinksLoyaltyCard(val stamps: Int, lastActionDate: Option[LocalDate] = None) extends LoyaltyCard
