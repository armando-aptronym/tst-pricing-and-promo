package com.tst.pricing.models

import scala.util.{Failure, Success, Try}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import com.tst.pricing.models.{BestGroupPrice, Rate, CabinPrice}

// TODO tag tests so that they can be used with `sbt testOnly`
// import org.scalatest.Tag

class RateCodeSpec extends AnyFunSuite {
    test("apply should work with a known RateCode and RateGroup") {
        RateCode.fromString(TestConstants.validRateCodeString) match {
            case Success(rateCode) => assert(rateCode == TestConstants.validRateCode)
            case Failure(_)        => fail("Expected Success but got Failure")
        }
    }
    test("apply should fail with an unknown RateCode and RateGroup") {
        val expectedMessage = "could not create RateCode with unknown value: InvalidRateCode"

        RateCode.fromString(TestConstants.invalidRateCodeString) match {
            case Failure(exception) => assert(exception.getMessage == expectedMessage)
            case Success(_)         => fail("Expected Failure but got Success")
        }
    }
}
