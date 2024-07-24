package model

sealed trait ItemType

object ItemType {
  case object ColdDrinks extends ItemType
  case object HotDrinks extends ItemType
  case object ColdFood extends ItemType
  case object HotFood extends ItemType
}