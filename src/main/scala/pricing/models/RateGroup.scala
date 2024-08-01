package com.tst.pricing.models

import scala.util.Try
import tst.pricing.models.OptionUtils

enum RateGroup(val name: String) {
    case Standard  extends RateGroup("Standard")
    case Military  extends RateGroup("Military")
    case Senior    extends RateGroup("Senior")
    case Promotion extends RateGroup("Promotion")

    override def toString(): String = name
}

object RateGroup {

    def fromString(name: String): Try[RateGroup] = {
        Try(
          values
              .find(_.name == name)
              .someOrFail
        )
            .recover(_ => throw new IllegalArgumentException(s"could not create RateGroup with unknown value: $name"))
    }
}
