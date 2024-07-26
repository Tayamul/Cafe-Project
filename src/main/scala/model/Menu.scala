package model

import utils.{InvalidMenuItemType, _}

case class Menu(hotDrinks: List[MenuItem], coldDrinks: List[MenuItem], hotFoods: List[MenuItem], coldFoods: List[MenuItem]) {

  def createMenu: List[MenuItem] = {
    hotDrinks ++ coldDrinks ++ hotFoods ++ coldFoods
  }


  def addMenuItem(item: MenuItem): Either[InvalidMenuItemType, Menu] = {
    item.itemType match {
      case ItemType.HotDrinks => Right(copy(hotDrinks = hotDrinks :+ item))
      case ItemType.ColdDrinks => Right(copy(coldDrinks = coldDrinks :+ item))
      case ItemType.HotFoods => Right(copy(hotFoods = hotFoods :+ item))
      case ItemType.ColdFoods => Right(copy(coldFoods = coldFoods :+ item))
      case _ => Left(InvalidMenuItemType("Invalid item type!"))
    }
  }

  def removeMenuItem(item: MenuItem): Either[InvalidMenuItemType, Menu] = {
    item.itemType match {
      case ItemType.HotDrinks => Right(copy(hotDrinks = hotDrinks.filterNot(_ == item)))
      case ItemType.ColdDrinks => Right(copy(coldDrinks = coldDrinks.filterNot(_ == item)))
      case ItemType.HotFoods => Right(copy(hotFoods = hotFoods.filterNot(_ == item)))
      case ItemType.ColdFoods => Right(copy(coldFoods = coldFoods.filterNot(_ == item)))
      case _ => Left(InvalidMenuItemType("Something went wrong, try again!"))
    }
  }

}
