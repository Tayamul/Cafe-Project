package model

case class MenuItem(name: String, price: Double, quantity: Int = 1, itemQuality: ItemQuality, itemType: ItemType)