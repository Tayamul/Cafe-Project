package model

sealed trait ItemType

object ItemType {
  case object ColdDrinks extends ItemType
  case object HotDrinks extends ItemType
  case object ColdFoods extends ItemType
  case object HotFoods extends ItemType
}