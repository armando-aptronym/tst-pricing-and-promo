package com.tst.pricing.models

import scala.util.Try
import tst.pricing.models.OptionUtils

enum RateCode(val name: String) {
    case Military1 extends RateCode("M1")
    case Military2 extends RateCode("M2")
    case Senior1   extends RateCode("S1")
    case Senior2   extends RateCode("S2")

    override def toString(): String = name
}

object RateCode {

    def fromString(name: String): Try[RateCode] = {
        Try(
          values
              .find(_.name == name)
              .someOrFail
        )
            .recover(_ => throw new IllegalArgumentException(s"could not create RateCode with unknown value: $name"))
    }
}
