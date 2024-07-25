package model

case class Bill(customer: Customer, order: Order, serviceCharge: Double, finalTotal: Double)
