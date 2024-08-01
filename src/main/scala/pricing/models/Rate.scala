package com.tst.pricing.models

import scala.util.{Failure, Success, Try}

final case class Rate(
    rateCode: RateCode,
    rateGroup: RateGroup
)

object Rate {
    def apply(rateCode: String, rateGroup: String): Rate = {
        val (tryRateCode, tryRateGroup) = (RateCode.fromString(rateCode), RateGroup.fromString(rateGroup))

        (tryRateCode, tryRateGroup) match {
            case (Success(rc), Success(rg)) =>
                Rate(rc, rg)
            case (Failure(_), Failure(_))   =>
                throw new IllegalArgumentException(
                  s"could not create Rate with unknown RateCode value: $rateCode and unknown RateGroup value: $rateGroup"
                )
            case (Failure(_), Success(_))   =>
                throw new IllegalArgumentException(s"could not create Rate with unknown RateCode value: $rateCode")
            case (Success(_), Failure(_))   =>
                throw new IllegalArgumentException(s"could not create Rate with unknown RateGroup value: $rateGroup")
        }
    }
}
