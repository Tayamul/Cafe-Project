package services

import scala.math.BigDecimal.RoundingMode
import model._
import services._


object BillingService {

  def calculateServiceCharge(order: Order): Double = {
    val menuTotal = order.items.map(_.price).sum
    val hasHotDrinkOrColdFood = order.items.exists(item => item.itemType == ItemType.HotDrinks || item.itemType == ItemType.ColdFoods)
    val hasHotFood = order.items.exists(_.itemType == ItemType.HotFoods)
    val hasPremiumItem = order.items.exists(_.itemQuality == ItemQuality.Premium)

    val autoServiceCharge = if (hasPremiumItem) {
      Math.min(menuTotal * 0.25, 40)
    } else if (hasHotFood) {
      Math.min(menuTotal * 0.2, 20)
    } else if (hasHotDrinkOrColdFood) {
      menuTotal * 0.1
    } else {
      0.00
    }
    // Round to 2 decimal places
    BigDecimal(autoServiceCharge).setScale(2, RoundingMode.HALF_UP).toDouble
  }


  //      val serviceCharge = autoServiceCharge // customServiceCharge.getOrElse - too add later
  //      val total = menuTotal + serviceCharge
  //      total


  //def calculateBill(order: Order, customerServiceCharge: Option[Double] = None): Bill = {


  //  def calculateLoyaltyDiscount
  //
  //  def generateBill


}

