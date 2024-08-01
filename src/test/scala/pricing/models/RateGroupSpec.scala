package com.tst.pricing.models

import scala.util.Failure
import scala.util.Success
import org.scalatest.funsuite.AnyFunSuite
import com.tst.pricing.models.{BestGroupPrice, Rate, CabinPrice}
import com.tst.pricing.models.TestConstants.validRateCode

// TODO tag tests so that they can be used with `sbt testOnly`
// import org.scalatest.Tag

class RateGroupSpec extends AnyFunSuite {
    test("apply should work with a known RateCode and RateGroup") {
        RateGroup.fromString(TestConstants.validRateGroupString) match {
            case Success(rateGroup) => assert(rateGroup == TestConstants.validRateGroup)
            case Failure(exception) => fail("Expected Success but got Failure")
        }
    }
    test("apply should fail with an unknown RateGroup") {
        val expectedMessage = "could not create RateGroup with unknown value: InvalidRateGroup"

        RateGroup.fromString(TestConstants.invalidRateGroupString) match {
            case Failure(exception) => assert(exception.getMessage == expectedMessage)
            case Success(_)         => fail("Expected Failure but got Success")
        }
    }
}
