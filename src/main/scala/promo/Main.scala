package com.tst.promo

import com.tst.promo.models.{Promotion, PromotionCombo}

// assume these came from some API response as raw, primitive types
// TODO: define external API types?
private object Input {
    val promotions: Seq[Promotion] = Seq(
      Promotion("P1", Seq("P3")),
      Promotion("P2", Seq("P4", "P5")),
      Promotion("P3", Seq("P1")),
      Promotion("P4", Seq("P2")),
      Promotion("P5", Seq("P2"))
    )
}

object Main {

    def main(args: Array[String]): Unit = {
        val allPromotionCombos: Seq[PromotionCombo] =
            PromoCombinationService.allCombinablePromotions(Input.promotions)
        println(s"The complete collection of combinable promotion combos is: $allPromotionCombos")

        val promotionCombosCombinableWithP1: Seq[PromotionCombo] =
            PromoCombinationService.combinablePromotions("P1", Input.promotions)
        println(
          s"The complete collection of combinable promotions for promotion P1 is: $promotionCombosCombinableWithP1"
        )

        val promotionCombosCombinableWithP3 = PromoCombinationService.combinablePromotions("P3", Input.promotions)
        println(
          s"The complete collection of combinable promotions for promotion P3 is: $promotionCombosCombinableWithP3"
        )
    }

}
