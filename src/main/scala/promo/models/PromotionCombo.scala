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

    override def equals(other: Any): Boolean = other match
        case PromotionCombo(promotionCodes) =>
            promotionCodes.toSet == this.promotionCodes.toSet
        case _                              => false
}
