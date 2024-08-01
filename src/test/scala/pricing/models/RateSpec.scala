package com.tst.pricing.models

import scala.util.{Success, Try}
import org.scalatest.funsuite.AnyFunSuite
import com.tst.pricing.models.{BestGroupPrice, Rate, CabinPrice}

// TODO tag tests so that they can be used with `sbt testOnly`
// import org.scalatest.Tag

class RateSpec extends AnyFunSuite {

    test("apply should work with a known RateCode and RateGroup") {
        assert(Try(Rate(TestConstants.validRateCodeString, TestConstants.validRateGroupString)).isSuccess)
    }
    test("apply should fail with an unknown RateCode and RateGroup") {
        val thrown: IllegalArgumentException =
            intercept[IllegalArgumentException](
              Rate(TestConstants.invalidRateCodeString, TestConstants.invalidRateGroupString)
            )

        val expectedMessage =
            "could not create Rate with unknown RateCode value: InvalidRateCode and unknown RateGroup value: InvalidRateGroup"
        assert(expectedMessage == thrown.getMessage)
    }
    test("apply should fail with an unknown RateCode") {
        val thrown: IllegalArgumentException =
            intercept[IllegalArgumentException](
              Rate(TestConstants.invalidRateCodeString, TestConstants.validRateGroupString)
            )

        val expectedMessage = "could not create Rate with unknown RateCode value: InvalidRateCode"
        assert(expectedMessage == thrown.getMessage)
    }
    test("apply should fail with an unknown RateGroup") {
        val thrown: IllegalArgumentException =
            intercept[IllegalArgumentException](
              Rate(TestConstants.validRateCodeString, TestConstants.invalidRateGroupString)
            )

        val expectedMessage = "could not create Rate with unknown RateGroup value: InvalidRateGroup"
        assert(expectedMessage == thrown.getMessage)
    }
}
