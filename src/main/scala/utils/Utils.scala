package utils

import scala.math.BigDecimal.RoundingMode

object Utils {
  def roundTo2dp(num: Double): Double = {
    BigDecimal(num).setScale(2, RoundingMode.HALF_UP).toDouble
  }
}
