package com.tst.pricing

package object models {

    object TestConstants {
        val validRateCode: RateCode       = RateCode.Military1
        val validRateCodeString: String   = "M1"
        val invalidRateCodeString: String = "InvalidRateCode"

        val validRateGroup: RateGroup      = RateGroup.Military
        val validRateGroupString: String   = "Military"
        val invalidRateGroupString: String = "InvalidRateGroup"
    }
}
