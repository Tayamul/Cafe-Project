package model

case class Menu(items: List[MenuItem]) {

  def createMenu (hotFoods: List[MenuItem], coldFoods: List[MenuItem], coldDrinks: List[MenuItem], hotDrinks: List[MenuItem]): List[List[MenuItem]] = {
   List(hotDrinks, coldDrinks, hotFoods, coldFoods)
  }

  def addPremiumItem (item: MenuItem): Unit = {

  }
}

