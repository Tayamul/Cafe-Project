package model

case class Customer(name: String, age: Int, loyaltyCard: Option[LoyaltyCard], purchaseHistory: Int = 0)
