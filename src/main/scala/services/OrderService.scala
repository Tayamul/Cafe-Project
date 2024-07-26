package services

import model._

object OrderService {

  def createOrder(items: List[MenuItem], customer: Customer): Order = {
    Order(items, customer)
  }

  def updatePurchaseHistory(order: Order): Customer = {
    val updatedCustomer = order.customer.copy(purchaseHistory = order.customer.purchaseHistory + 1)
    updatedCustomer
  }

  def calculateTotal(order: Order): Double = {
    order.items.map(_.price).sum
  }

}
