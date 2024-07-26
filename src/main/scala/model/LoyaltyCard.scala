package model

import java.time.LocalDate

trait LoyaltyCard {
  def lastActionDate: Option[LocalDate]
}
