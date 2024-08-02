package com.tst.promo

import com.tst.promo.models.{Promotion, PromotionCombo}
import com.tst.promo.Input.promotions

object IterativePromoCombinationService {

    final def allCombinablePromotions(
        allPromotions: Seq[Promotion]
    ): Seq[PromotionCombo] =
        filterOutSubsets(
          allPromotions.zipWithIndex.map { case (promotion: Promotion, index: Int) =>
              val remainingSubsetOfPromotions = allPromotions.drop(index)
              combinablePromotions(promotion.code, remainingSubsetOfPromotions)
          }.flatten
        )

    private final def filterOutSubsets: Seq[PromotionCombo] => Seq[PromotionCombo] = { set =>
        set.filterNot(combo => combo.containsSubsetOfPromotionCodes(set))
    }

    final def combinablePromotions(
        promotionCode: String,
        allPromotions: Seq[Promotion]
    ): Seq[PromotionCombo] =
        allPromotions.find(_.code == promotionCode) match
            case Some(promotion) => promotion.allValidCombosIterative(allPromotions)
            case None            => Seq.empty
}
