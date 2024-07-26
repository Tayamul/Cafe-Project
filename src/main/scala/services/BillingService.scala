package services

import scala.math.BigDecimal.RoundingMode
import model._
import services._
import OrderService._
import utils.Utils.roundTo2dp


object BillingService {

  def calculateLoyaltyDiscount(order: Order): Double = {
    val freeDrink = if(order.items)

    val menuTotal = calculateTotal(order)
    val customer = order.customer
    val hasColdDrink = order.items.exists(item => item.itemType == ItemType.ColdDrinks)
    val hasHotDrink = order.items.exists(item => item.itemType == ItemType.HotDrinks)

    customer.loyaltyCard match {
      case Some(DrinksLoyaltyCard) =>
        if (customer.loyaltyCard(DrinksLoyaltyCard(stamps = 10)) && hasColdDrink)
    }

  }

  //  def calculateTotal(order: Order): Double = {
  //    order.items.map(_.price).sum
  //  }

  // if purchase history = 10 && has DrinksDiscountCard - coldDrink(0).price
  // if has DiscountCard && bill > 20  THEN addStarsToDiscountCard(1)
  // if has DiscountCard && bill > 20 && 1 star = 2%, 2 stars = 4%, 3 = 6%, 4 = 8%, 5 = 10%, 6 = 12%, 7 = 14%, 8 = 16%, >8 = 16%
  //exclude premium items from discount

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
    roundTo2dp(totalServiceCharge)
  }

  def calculateBill(customer: Customer, order: Order, customServiceCharge: Option[CustomServiceCharge]): Bill = {
    val menuTotal = calculateTotal(order)
    val serviceCharge = calculateServiceCharge(order, customServiceCharge)
    val finalTotal = roundTo2dp(menuTotal + serviceCharge)

    Bill(customer, order, serviceCharge, finalTotal)
  }

}

