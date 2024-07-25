package services

import model._

object OrderService {

  def createOrder(items: List[MenuItem], customer: Customer): Order = {
    Order(items, customer)
  }

  val customer: Customer = Customer("Jake", 28, None)
  val items: List[MenuItem] = List(Items.coffee, Items.latte, Items.caesarSalad, Items.smoothie, Items.coffee)

  def calculateTotal(order: Order): Double = {
    order.items.map(_.price).sum
  }


}
