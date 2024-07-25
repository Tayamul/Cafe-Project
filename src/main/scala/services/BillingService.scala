package services


import model._
import services._


object BillingService {

  def calculateServiceCharge(order: Order, serviceCharge: ServiceCharge): Double = {
    val menuTotal = order.items.map(_.price).sum
    val hasHotDrinkOrColdFood = order.items.exists(item => item.itemType == ItemType.HotDrinks || item.itemType == ItemType.ColdFoods)
    val hasHotFood = order.items.exists(_.itemType == ItemType.HotFoods)
    val hasPremiumItem = order.items.exists(_.itemQuality == ItemQuality.Premium)

    if (hasPremiumItem) {
      Math.min(menuTotal * 1.25, 40)
    } else if (hasHotFood) {
      Math.min(menuTotal * 1.2, 20)
    } else if (hasHotDrinkOrColdFood) {
      menuTotal * 1.1
    } else {
      menuTotal
    }

  }



  //def calculateBill(order: Order, customerServiceCharge: Option[Double] = None): Bill = {


  def calculateLoyaltyDiscount

  def generateBill


}

