package com.tst.pricing

import org.scalatest.funsuite.AnyFunSuite

import com.tst.pricing.models.{BestGroupPrice, Rate, CabinPrice}

// TODO tag tests so that they can be used with `sbt testOnly`
// import org.scalatest.Tag

class GroupPricingServiceSpec extends AnyFunSuite {
    test("should successfully return an empty sequence when given empty rates") {
        val testCabinPrices: Seq[CabinPrice]           = Seq(
          CabinPrice("CA", "M1", 200.00),
          CabinPrice("CA", "M2", 250.00),
          CabinPrice("CA", "S1", 225.00),
          CabinPrice("CA", "S2", 260.00),
          CabinPrice("CB", "M1", 230.00),
          CabinPrice("CB", "M2", 260.00),
          CabinPrice("CB", "S1", 245.00),
          CabinPrice("CB", "S2", 270.00)
        )
        val actualBestGroupPrices: Seq[BestGroupPrice] =
            GroupPricingService.getBestGroupPrices(Seq.empty[Rate], testCabinPrices)

        assert(actualBestGroupPrices.isEmpty)
    }
    test("should successfully return an empty sequence when given empty cabin prices") {
        val testRates: Seq[Rate]                       = Seq(
          Rate("M1", "Military"),
          Rate("M2", "Military"),
          Rate("S1", "Senior"),
          Rate("S2", "Senior")
        )
        val actualBestGroupPrices: Seq[BestGroupPrice] =
            GroupPricingService.getBestGroupPrices(testRates, Seq.empty[CabinPrice])

        assert(actualBestGroupPrices.isEmpty)
    }
    test("should successfully return an empty sequence when given empty rates and empty cabin prices") {
        assert(GroupPricingService.getBestGroupPrices(Seq.empty[Rate], Seq.empty[CabinPrice]).isEmpty)
    }
    test("should successfully return best prices by rate group for nonempty inputs") {
        val testRates: Seq[Rate] = Seq(
          Rate("M1", "Military"),
          Rate("M2", "Military"),
          Rate("S1", "Senior"),
          Rate("S2", "Senior")
        )

        val testCabinPrices: Seq[CabinPrice] = Seq(
          CabinPrice("CA", "M1", 200.00),
          CabinPrice("CA", "M2", 250.00),
          CabinPrice("CA", "S1", 225.00),
          CabinPrice("CA", "S2", 260.00),
          CabinPrice("CB", "M1", 230.00),
          CabinPrice("CB", "M2", 260.00),
          CabinPrice("CB", "S1", 245.00),
          CabinPrice("CB", "S2", 270.00)
        )

        val expectedBestGroupPrices: Seq[BestGroupPrice] = Seq(
          BestGroupPrice("CA", "M1", 200.00, "Military"),
          BestGroupPrice("CA", "S1", 225.00, "Senior"),
          BestGroupPrice("CB", "M1", 230.00, "Military"),
          BestGroupPrice("CB", "S1", 245.00, "Senior")
        )
        val actualBestGroupPrices: Seq[BestGroupPrice]   =
            GroupPricingService.getBestGroupPrices(testRates, testCabinPrices)

        assert(actualBestGroupPrices.nonEmpty)
        assert(expectedBestGroupPrices.size == actualBestGroupPrices.size)
        assert(expectedBestGroupPrices.toSet == actualBestGroupPrices.toSet)
    }
}
