package utils

import java.time.LocalDate
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import utils.Utils._

class UtilsSpec extends AnyWordSpec with Matchers {

  "onePerDay" should {
    "limit the action to once per day" when {
      "the action was performed today" in {
        val today = LocalDate.now
        val lastActionDate = Some(today)

        val (newLastActionDate, actionResult) = onePerDay(lastActionDate) {
          "action performed"
        }
        newLastActionDate shouldBe lastActionDate
        actionResult shouldBe None
      }
    }
  }


  //  def onePerDay[T](lastActionDate: Option[LocalDate])(actionCard: => T): (Option[LocalDate], Option[T]) = {
  //    val today = LocalDate.now()
  //    lastActionDate match {
  //      case Some(date) if date.isEqual(today) =>
  //        // Action already performed today
  //        (lastActionDate, None)
  //      case _ =>
  //        // Perform the action
  //        (Some(today), Some(actionCard))
  //    }
  //  }

}
