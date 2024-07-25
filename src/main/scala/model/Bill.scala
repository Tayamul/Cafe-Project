package model

case class Bill(order: Order, total: Double, serviceCharge: Double, loyaltyDiscount: Double, finalTotal: Double)
