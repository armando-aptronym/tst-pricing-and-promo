package com.tst.promo.models

final case class Promotion(
    code: String,
    notCombinableWith: Seq[String]
) {
    final def isCombinableWith(promotion: Promotion): Boolean  = !notCombinableWith.contains(promotion)
    final def notCombinableWith(promotion: Promotion): Boolean = !isCombinableWith(promotion)

    final def allValidCombos(otherPromotions: Seq[Promotion]): Seq[PromotionCombo] =
        otherPromotions match
            case Nil => Seq.empty[PromotionCombo]
            case _   =>
                val startingCombo = Set(this.code)
                Promotion
                    .combine(
                      currentCombo = startingCombo,
                      filterSet = this.notCombinableWith.toSet,
                      recursiveSet = otherPromotions
                    )
                    .toSeq
                    .map(_.toSeq)
                    .map(PromotionCombo.apply)

    final def allValidCombosIterative(otherPromotions: Seq[Promotion]): Seq[PromotionCombo] =
        val accumulatedCombos: Set[Set[String]] = otherPromotions.foldLeft(Set.empty[Set[String]]) {
            (acc, currentPromotion) =>
                val newCombos = acc.flatMap { newCombo =>
                    if (newCombo.forall(promotionCode => !currentPromotion.notCombinableWith.contains(promotionCode))) {
                        Some(newCombo + currentPromotion.code)
                    } else {
                        None
                    }
                }
                (acc ++ newCombos + Set(currentPromotion.code)).filter { combo =>
                    !acc.exists(otherCombo => otherCombo != combo && combo.subsetOf(otherCombo))
                }
        }
        accumulatedCombos
            .map(combo => PromotionCombo(combo.toSeq))
            .toSeq
}

object Promotion {
    final def filterSubsets(
        superSet: Set[Set[String]]
    ): Set[Set[String]] =
        superSet.filter { combo =>
            !superSet.exists { other =>
                combo.toSet.subsetOf(other.toSet) && !(combo == other)
            }
        }

    final def combine(
        recursiveSet: Seq[Promotion],
        filterSet: Set[String],
        currentCombo: Set[String]
    ): Set[Set[String]] =
        recursiveSet.headOption.fold(Set(currentCombo)) { currentPromotion =>
            val compatible: Boolean = !filterSet.contains(currentPromotion.code)
                && currentCombo.exists(promotionCode => !currentPromotion.notCombinableWith.contains(promotionCode))

            if (compatible) {

                // recursively find max sets including and not including the current combo
                val includingCurrent = combine(
                  recursiveSet = recursiveSet.tail,
                  filterSet = filterSet ++ currentPromotion.notCombinableWith,
                  currentCombo = currentCombo + currentPromotion.code
                )
                val excludingCurrent = combine(
                  recursiveSet = recursiveSet.tail,
                  filterSet = filterSet,
                  currentCombo = currentCombo
                )

                // filter out combos that are subsets of the max sets above
                Promotion.filterSubsets(includingCurrent ++ excludingCurrent)
            } else {
                combine(
                  recursiveSet = recursiveSet.tail,
                  filterSet = filterSet,
                  currentCombo = currentCombo
                )
            }
        }
}
