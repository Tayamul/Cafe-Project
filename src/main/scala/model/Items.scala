package model

object Items {

  // Hot Drink
  val coffee: MenuItem = MenuItem(name = "coffee", price = 3.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val greenTea: MenuItem = MenuItem(name = "green tea", price = 2.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val decaf: MenuItem = MenuItem(name = "decaf", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotDrinks)
  val latte: MenuItem = MenuItem(name = "latte", price = 4.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)
  val breakfastTea: MenuItem = MenuItem(name = "caramel latte", price = 2.7, itemQuality = ItemQuality.Premium, itemType = ItemType.HotDrinks)

  // Cold Drinks
  val icedCoffee: MenuItem = MenuItem(name = "iced coffee", price = 3.5, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val lemonade: MenuItem = MenuItem(name = "lemonade", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val smoothie: MenuItem = MenuItem(name = "smoothie", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)
  val icedTea: MenuItem = MenuItem(name = "iced tea", price = 3.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdDrinks)
  val gourmetSmoothie: MenuItem = MenuItem(name = "gourmet smoothie", price = 5.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdDrinks)

  // Hot Food
  val pancakes: MenuItem = MenuItem(name = "pancakes", price = 5.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFood)
  val eggsBenedict: MenuItem = MenuItem(name = "eggs benedict", price = 8.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFood)
  val grilledCheese: MenuItem = MenuItem(name = "grilled cheese sandwich", price = 4.5, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFood)
  val veggieBurger: MenuItem = MenuItem(name = "veggie burger", price = 7.0, itemQuality = ItemQuality.Standard, itemType = ItemType.HotFood)
  val steakSandwich: MenuItem = MenuItem(name = "steak sandwich", price = 10.0, itemQuality = ItemQuality.Premium, itemType = ItemType.HotFood)

  // Cold Food
  val caesarSalad: MenuItem = MenuItem(name = "caesar salad", price = 6.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFood)
  val yogurtParfait: MenuItem = MenuItem(name = "greek yogurt parfait", price = 4.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFood)
  val hamCheeseSandwich: MenuItem = MenuItem(name = "ham and cheese sandwich", price = 5.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFood)
  val fruitSalad: MenuItem = MenuItem(name = "fruit salad", price = 4.0, itemQuality = ItemQuality.Standard, itemType = ItemType.ColdFood)
  val smokedSalmonBagel: MenuItem = MenuItem(name = "smoked salmon bagel", price = 8.5, itemQuality = ItemQuality.Premium, itemType = ItemType.ColdFood)

}