package services

import scala.math.BigDecimal.RoundingMode
import model._
import services._
import OrderService._


object BillingService {

  def calculateServiceCharge(order: Order, customServiceCharge: Option[CustomServiceCharge]): Double = {
    val menuTotal = calculateTotal(order)
    val hasHotDrinkOrColdFood = order.items.exists(item => item.itemType == ItemType.HotDrinks || item.itemType == ItemType.ColdFoods)
    val hasHotFood = order.items.exists(_.itemType == ItemType.HotFoods)
    val hasPremiumItem = order.items.exists(_.itemQuality == ItemQuality.Premium)

    val standardServiceCharge = if (hasPremiumItem) {
      Math.min(menuTotal * 0.25, 40)
    } else if (hasHotFood) {
      Math.min(menuTotal * 0.2, 20)
    } else if (hasHotDrinkOrColdFood) {
      menuTotal * 0.1
    } else {
      0.00
    }

    val totalServiceCharge = customServiceCharge match {
      case Some(CustomServiceCharge(percentage, isAdditive)) if isAdditive =>
        standardServiceCharge + menuTotal * percentage / 100
      case Some(CustomServiceCharge(percentage, _)) =>
        menuTotal * percentage / 100
      case None =>
        standardServiceCharge
    }
    // Round to 2 decimal places
    BigDecimal(totalServiceCharge).setScale(2, RoundingMode.HALF_UP).toDouble
  }


  //def calculateBill(order: Order, customerServiceCharge: Option[Double] = None): Bill = {
  def calculateBill(order: Order): Bill = {
    calculateServiceCharge(order, customServiceCharge: Option[CustomServiceCharge])
    val menuTotal = calculateTotal(order)
    val customServiceCharge: Option[CustomServiceCharge] = None
    val standardServiceCharge:
    val bill = menuTotal + serviceCharge
  }


}

  //  def calculateLoyaltyDiscount
  //
  //  def generateBill


}

