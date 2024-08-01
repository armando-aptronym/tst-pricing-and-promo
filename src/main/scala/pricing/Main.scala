package com.tst.pricing

import com.tst.pricing.models.{BestGroupPrice, CabinPrice, Rate}

// assume these came from some API response as raw, primitive types
// TODO: define external API types?
private object Input {
    val rates: Seq[Rate] = Seq(
      Rate("M1", "Military"),
      Rate("M2", "Military"),
      Rate("S1", "Senior"),
      Rate("S2", "Senior")
    )

    val cabinPrices: Seq[CabinPrice] = Seq(
      CabinPrice("CA", "M1", 200.00),
      CabinPrice("CA", "M2", 250.00),
      CabinPrice("CA", "S1", 225.00),
      CabinPrice("CA", "S2", 260.00),
      CabinPrice("CB", "M1", 230.00),
      CabinPrice("CB", "M2", 260.00),
      CabinPrice("CB", "S1", 245.00),
      CabinPrice("CB", "S2", 270.00)
    )
}

object Main {

    def main(args: Array[String]): Unit = {
        val bestPricesByRateGroup: Seq[BestGroupPrice] = GroupPricingService.getBestGroupPrices(
          rates = Input.rates,
          cabinPrices = Input.cabinPrices
        )
        println(s"The Best Prices organized by group are: $bestPricesByRateGroup")
    }

}
