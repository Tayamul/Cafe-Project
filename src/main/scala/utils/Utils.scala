package utils

import scala.math.BigDecimal.RoundingMode
import java.time.LocalDate


object Utils {
  def roundTo2dp(num: Double): Double = {
    BigDecimal(num).setScale(2, RoundingMode.HALF_UP).toDouble
  }

  def onePerDay[T](lastActionDate: Option[LocalDate])(actionCard: => T): (Option[LocalDate], Option[T]) = {
    val today = LocalDate.now()
    lastActionDate match {
      case Some(date) if date.isEqual(today) =>
        // Action already performed today
        (lastActionDate, None)
      case _ =>
        // Perform the action
        (Some(today), Some(actionCard))
    }
  }
}


