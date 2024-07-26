package services

import model._

object OrderService {

  def createOrder(items: List[MenuItem], customer: Customer): Order = {
    Order(items, customer)

  }

  //  customer purchase history .. store time of action, +1 to purchase history
  //    def updatePurchaseHistory(order: Order): Customer = {
  //      val updatedCustomer = order.customer.copy(order.customer.purchaseHistory + 1 localdate.now//something)
  //      customer = updatedCustomer //might be reassignment to val
  //    }

  def calculateTotal(order: Order): Double = {
    order.items.map(_.price).sum
  }

}
