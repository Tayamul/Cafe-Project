case class Bill(order: Order, total: Double, Option[serviceCharge: Double], loyaltyDiscount: Double, finalTotal: Double) {

}

case class Order(items: List[MenuItem], customer: Customer) {

}

case class Customer(name: String, age: Int, loyaltyCard: Option[LoyaltyCard])

case class ServiceCharge(percentage: Double, maxCharge: Option[Double] = None)


sealed trait LoyaltyCard

case class DrinksLoyaltyCard(stamps: Int) extends LoyaltyCard
case class DiscountLoyaltyCard(stars: Int, totalSpent: Double) extends LoyaltyCard

object OrderService {

  def createOrder //items listmenu, customer, return order type order (items, customer)?

  def calculateTotal

  def calculateServiceCharge

  //if statement for premium item increase service charge

  def generateBill

}

object BillingService {
  def calculateServiceCharge

  def calculateLoyaltyDiscount

  def generateBill
}

object LoyaltyService {

  def addStampsToDrinksCard

  def addStarsToDiscountCard

  def checkLoyaltyEligibility

  def calculateLoyaltyDiscount

}




//Utilities

def roundToTwoDecimals
def calculatePercentage

//ext utils
def convertCurrency
def happyHour //java.time
