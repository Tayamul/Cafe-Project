package model

case class Menu(hotDrinks: List[MenuItem], coldDrinks: List[MenuItem], hotFoods: List[MenuItem], coldFoods: List[MenuItem]) {

  def createMenu: List[MenuItem] = {
    hotDrinks ++ coldDrinks ++ hotFoods ++ coldFoods
  }


  def addMenuItem(item: MenuItem): Menu = {
    item.itemType match {
      case ItemType.HotDrinks => copy(hotDrinks = hotDrinks :+ item)
      case ItemType.ColdDrinks => copy(coldDrinks = coldDrinks :+ item)
      case ItemType.HotFoods => copy(hotFoods = hotFoods :+ item)
      case ItemType.ColdFoods => copy(coldFoods = coldFoods :+ item)
    }
  }

  def removeMenuItem(item: MenuItem): Menu = {
    item.itemType match {
      case ItemType.HotDrinks => copy(hotDrinks = hotDrinks.filterNot(_ == item))
      case ItemType.ColdDrinks => copy(coldDrinks = coldDrinks.filterNot(_ == item))
      case ItemType.HotFoods => copy(hotFoods = hotFoods.filterNot(_ == item))
      case ItemType.ColdFoods => copy(coldFoods = coldFoods.filterNot(_ == item))
    }
  }

}
