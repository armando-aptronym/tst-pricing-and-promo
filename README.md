# tst-pricing-and-promo
An exercise showing pricing and promotion capabilities of a platform leveraging third party data.

### Instructions for Validating Acceptance Criteria

- `$ sbt test`
- `$ sbt run` then select between each exercise
    - `[1] com.tst.pricing.Main`
    - `[2] com.tst.promo.Main`

---

### Problem 1

TST Cruises leverages third party data to solve several problems, including but not limited to finding the best price for a particular rate group.

#### Given Type Definitions for Problem 1

`CabinPrice`
- Price for a specific cabin on a specific cruise
- Single rate attached

```
case class CabinPrice(
    cabinCode: String,
    rateCode: String,
    price: BigDecimal
)
```

`Rate`
- Method of grouping related prices
- Defined by Rate Code and Rate Group

```
case class Rate(
    rateCode: String,
    rateGroup: String
)
```

`RateGroup`
- Enum type (e.g. Standard, Military, Senior, Promotion)
- One-to-many relationship between `RateGroup` and `Rate`

`BestGroupPrice`
```
case class BestGroupPrice(
    cabinCode: String,
    rateCode: String,
    price: BigDecimal,
    rateGroup: String
)
```

#### Objectives of Problem 1

1. Write a function based on the provided function header which
    - takes in a list of rates and a list of prices
    - returns the best price for each rate group
2. Run the following sample data through the function and output a `Seq[BestGroupPrice]` which matches the expected output

#### Callouts on Problem 1 Solution

- I added `enum` definitions for each provided case class in order to isolate error handling to the types which were required by the `GroupPricingService`
    - This way, when the `GroupPricingService` is used to get the best group prices, its methods themselves are not expected to fail
    - The enum types have additional unit tests to prove this isolation works
    - I handle the final exception in `Rate` instead of `Main` to simplify the mocked consumption of API inputs for the sake of time
- I decided/assumed that if inputs were empty, the methods should still succeed but would return empty results

--- 

### Problem 2

Cruise bookings can have one or more _compatible_ Promotions applied to them. The possible combinations of promotions must be reconciled by the TST application(s).

#### Given Type Definitions for Problem 2

`Promotion`
```
case class Promotion(
    code: String,
    notCombinableWith: Seq[String]
)
```

`PromotionCombo`
```
case class PromotionCombo(
    promotionCodes: Seq[String]
)
```

#### Objectives of Problem 2

1. Write a function to find all `PromotionCombo`s with the maximum number of combinable promotions in each using the provided function header
2. Write a function to find all `PromotionCombo`s for a given `Promoion` from a given list of other `Promotion`s using the provided function header
3. Run through the sample data on startup and output a `Seq[PromotionCombo]` which matches the expected output

#### Notes on Problem 2 Solution

- In this exercise, I did not add `enum` definitions for each provided case class in order to:
    - simplify the error handling and unit testing on Problem 2 for the sake of time
    - show how we could write similar code which is less opionated on the inputs returned by the third party API and is still functional
- I decided/assumed that if inputs were empty, the methods should still succeed but would return empty results
