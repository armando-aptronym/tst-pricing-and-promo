package com.tst.pricing.models

import scala.util.{Success, Try}

final case class CabinPrice(
    cabinCode: String,
    rateCode: RateCode,
    price: BigDecimal
)

object CabinPrice {
    def apply(cabinCode: String, rateCode: String, price: BigDecimal): CabinPrice =
        RateCode.fromString(rateCode) match {
            case Success(rc) =>
                CabinPrice(cabinCode, rc, price)
            case _           => throw new NoSuchElementException("unknown rate code used to construct CabinPrice")
        }
}
