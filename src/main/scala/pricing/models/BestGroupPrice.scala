package com.tst.pricing.models

import scala.util.{Success, Try}

final case class BestGroupPrice(
    cabinCode: String,
    rateCode: RateCode,
    price: BigDecimal,
    rateGroup: RateGroup
)

object BestGroupPrice {
    def apply(
        cabinCode: String,
        rateCode: String,
        price: BigDecimal,
        rateGroup: String
    ): BestGroupPrice = {
        val (tryRateCode, tryRateGroup) = (RateCode.fromString(rateCode), RateGroup.fromString(rateGroup))

        (tryRateCode, tryRateGroup) match {
            case (Success(rc), Success(rg)) =>
                BestGroupPrice(cabinCode, rc, price, rg)
            case _                          =>
                throw new NoSuchElementException("unknown rate code or rate group used to construct BestGroupPrice")
        }
    }
}
