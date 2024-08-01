package com.tst.promo

import org.scalatest.funsuite.AnyFunSuite
import com.tst.promo.models.{Promotion, PromotionCombo}

// TODO tag tests so that they can be used with `sbt testOnly`
// import org.scalatest.Tag

import org.scalatest.funsuite.AnyFunSuite

class PromoCombinationServiceSpec extends AnyFunSuite {

    private val testPromotions: Seq[Promotion] = Seq(
      Promotion("P1", Seq("P3")),
      Promotion("P2", Seq("P4", "P5")),
      Promotion("P3", Seq("P1")),
      Promotion("P4", Seq("P2")),
      Promotion("P5", Seq("P2"))
    )

    test("should successfully return the complete collection of combinable promotions") {
        val expectedCombinablePromotions: Seq[PromotionCombo] = Seq(
          PromotionCombo(Seq("P1", "P2")),
          PromotionCombo(Seq("P1", "P4", "P5")),
          PromotionCombo(Seq("P2", "P3")),
          PromotionCombo(Seq("P3", "P4", "P5"))
        )
        val actualCombinablePromotions: Seq[PromotionCombo]   =
            PromoCombinationService.allCombinablePromotions(testPromotions)

        assert(actualCombinablePromotions.nonEmpty)
        assert(actualCombinablePromotions.size == expectedCombinablePromotions.size)
        assert(actualCombinablePromotions == expectedCombinablePromotions)
    }
    test("should successfully return the collection of combinable promotions for a given promotion") {
        val expectedCombinablePromotions: Seq[PromotionCombo] = Seq(
          PromotionCombo(Seq("P1", "P2")),
          PromotionCombo(Seq("P1", "P4", "P5"))
        )
        val targetPromotionCode: String                       = "P1"
        val actualCombinablePromotions: Seq[PromotionCombo]   =
            PromoCombinationService.combinablePromotions(targetPromotionCode, testPromotions)

        assert(actualCombinablePromotions.nonEmpty)
        assert(actualCombinablePromotions.size == expectedCombinablePromotions.size)
        assert(actualCombinablePromotions == expectedCombinablePromotions)
    }
    test("should successfully return the collection of combinable promotions for a different given promotion") {
        val targetPromotionCode: String                       = "P3"
        val expectedCombinablePromotions: Seq[PromotionCombo] = Seq(
          PromotionCombo(Seq("P3", "P2")),
          PromotionCombo(Seq("P3", "P4", "P5"))
        )

        val actualCombinablePromotions: Seq[PromotionCombo] =
            PromoCombinationService.combinablePromotions(targetPromotionCode, testPromotions)

        assert(actualCombinablePromotions.nonEmpty)
        assert(actualCombinablePromotions.size == expectedCombinablePromotions.size)
        assert(actualCombinablePromotions == expectedCombinablePromotions)
    }
    test(
      "should successfully return empty collection of other combinable promotions if a given promotion has no other combinable promotions"
    ) {
        val targetPromotionCode: String = "DNE"
        assert(PromoCombinationService.combinablePromotions(targetPromotionCode, testPromotions).isEmpty)
    }
    test(
      "should successfully return empty collection of other combinable promotions if an empty set of allPromotions is input"
    ) {
        val targetPromotionCode: String = "ANY"
        assert(PromoCombinationService.combinablePromotions(targetPromotionCode, Seq.empty[Promotion]).isEmpty)
    }
    test(
      "should successfully return empty collection of all possibly combinable promotions if an empty set of promotions is input"
    ) {
        assert(PromoCombinationService.allCombinablePromotions(Seq.empty[Promotion]).isEmpty)
    }
}
