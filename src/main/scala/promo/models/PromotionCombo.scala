package com.tst.promo.models

final case class PromotionCombo(
    promotionCodes: Seq[String]
) {
    final def addPromotionCode(promotionCode: String): PromotionCombo =
        PromotionCombo(this.promotionCodes :+ promotionCode)

    final def containsSubsetOfPromotionCodes(otherCombos: Seq[PromotionCombo]): Boolean =
        otherCombos.exists(otherCombo =>
            otherCombo.promotionCodes.containsSlice(this.promotionCodes) && (otherCombo != this)
        )
}
