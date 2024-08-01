package com.tst.pricing

import com.tst.pricing.models.{BestGroupPrice, CabinPrice, Rate, RateCode, RateGroup}

object GroupPricingService {

    final case class CabinCodeRateGroupKey(
        cabinCode: String,
        rateGroup: RateGroup
    )

    final def getBestGroupPrices(
        rates: Seq[Rate],
        cabinPrices: Seq[CabinPrice]
    ): Seq[BestGroupPrice] =
        cabinPrices
            .map { cabinPrice =>
                rates
                    .map(rate => (rate.rateCode, rate.rateGroup))
                    .toMap
                    .get(cabinPrice.rateCode)
                    .map { rateGroup =>
                        BestGroupPrice(
                          cabinPrice.cabinCode,
                          cabinPrice.rateCode,
                          cabinPrice.price,
                          rateGroup
                        )
                    }
            }        // Seq[Option[BestGroupPrice]]
            .flatten // Seq[BestGroupPrice]
            .groupBy { bestGroupPrice =>
                CabinCodeRateGroupKey(bestGroupPrice.cabinCode, bestGroupPrice.rateGroup)
            }        // Map[CabinCodeRateGroupKey, Seq[BestGroupPrice]]
            .map { case (cabinCodeRateGroup: CabinCodeRateGroupKey, bestGroupPrices: Seq[BestGroupPrice]) =>
                bestGroupPrices.sortBy(_.price).headOption
            }        // Iterable[Option[BestGroupPrice]]
            .flatten // Iterable[BestGroupPrice]
            .toSeq   // Seq[BestGroupPrice]

}
