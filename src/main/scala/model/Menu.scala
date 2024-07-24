package model

case class Menu(hotDrinks: List[MenuItem], coldDrinks: List[MenuItem],hotFoods: List[MenuItem], coldFoods: List[MenuItem]) {

  def createMenu: List[MenuItem] = ???


  def addMenuItem (item: MenuItem): Menu = {
    item.itemType match {
      case ItemType.ColdDrinks => copy(coldDrinks = coldDrinks :+ item)
      case ItemType.HotDrinks => copy(hotDrinks = hotDrinks :+ item)
      case ItemType.ColdFoods => copy(coldFoods = coldFoods :+ item)
      case ItemType.HotFoods => copy(hotFoods = hotFoods :+ item)
    }

  }

}
