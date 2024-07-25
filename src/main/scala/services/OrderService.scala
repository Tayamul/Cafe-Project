package services

import model._

object OrderService {

  def createOrder(items: List[MenuItem], customer: Customer): Order = {
    Order(items, customer)
  }

  def calculateTotal(order: Order): Double = {
    order.items.map(_.price).sum
  }

}
