package tst.pricing

package object models {
    implicit class OptionUtils[A](option: Option[A]) {
        def someOrFail: A = option match {
            case Some(value) => value
            case None        => throw new NoSuchElementException("no value present")
        }
    }
}
