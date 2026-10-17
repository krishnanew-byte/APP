"""
Question 3: E-Commerce Festival Discount using Functional Programming
====================================================================
An e-commerce platform maintains a list of product prices.
During a festival sale, the company offers a 20% discount on products costing more than ₹1,000.
a) Write a pure function to calculate the discounted price.
b) Use map() to apply the discount to eligible products.
c) Use filter() to identify products costing more than ₹1,000.
d) Explain how the pure function avoids modifying the original product list.
"""

import sys
from typing import List, Dict, Any

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


# -------------------------------------------------------------------------
# Part (a): Pure function to calculate the discounted price
# -------------------------------------------------------------------------
def calculate_discounted_price(price: float) -> float:
    """
    Pure Function to calculate the discounted price of a product.

    Rules:
      - 20% discount if price > 1000.
      - Otherwise, price remains unchanged.

    Pure Function Properties:
      1. Deterministic: For any given input `price`, it always yields the exact same output.
      2. Zero Side-Effects: Does not modify any global variable, outer state, or mutate input.
      3. Referential Transparency: The function call can be replaced with its value.
    """
    DISCOUNT_RATE = 0.20
    DISCOUNT_THRESHOLD = 1000.0

    if price > DISCOUNT_THRESHOLD:
        discount_amount = price * DISCOUNT_RATE
        return round(price - discount_amount, 2)
    return round(price, 2)


def run_question_3():
    print("=" * 70)
    print("QUESTION 3: E-COMMERCE PRODUCT DISCOUNT (PURE FUNCTIONS & MAP/FILTER)")
    print("=" * 70)

    # Initial Dataset of Product Prices
    product_catalog: List[Dict[str, Any]] = [
        {"id": "PROD01", "name": "Mechanical Keyboard", "price": 3500.0},
        {"id": "PROD02", "name": "USB-C Fast Cable", "price": 499.0},
        {"id": "PROD03", "name": "Wireless Gaming Mouse", "price": 1850.0},
        {"id": "PROD04", "name": "Laptop Cooling Pad", "price": 950.0},
        {"id": "PROD05", "name": "27-inch 4K Monitor", "price": 24000.0},
        {"id": "PROD06", "name": "Screen Cleaning Kit", "price": 250.0},
        {"id": "PROD07", "name": "Noise Cancelling Headphones", "price": 8999.0},
        {"id": "PROD08", "name": "Ergonomic Mousepad", "price": 1000.0},  # Exactly 1000 -> Not > 1000
    ]

    # Raw price list
    prices: List[float] = [p["price"] for p in product_catalog]

    print("\n[Initial Product Catalog]:")
    print(f"  {'ID':<8} {'Product Name':<28} {'Original Price (INR)':>22}")
    print("  " + "-" * 62)
    for p in product_catalog:
        print(f"  {p['id']:<8} {p['name']:<28} Rs. {p['price']:>16,.2f}")
    print(f"\n  Original Prices List: {prices}")

    # -------------------------------------------------------------------------
    # Part (c): Use filter() to identify products costing more than ₹1,000
    # -------------------------------------------------------------------------
    THRESHOLD = 1000.0
    eligible_products = list(filter(lambda p: p["price"] > THRESHOLD, product_catalog))
    eligible_prices = list(filter(lambda price: price > THRESHOLD, prices))

    print("\n" + "-" * 70)
    print("Part (c): Identify products costing more than Rs. 1,000 using filter()")
    print("-" * 70)
    print(f"  Condition: price > Rs. {THRESHOLD:,.2f}")
    print(f"  Eligible products count: {len(eligible_products)} of {len(product_catalog)}")
    for p in eligible_products:
        print(f"  [ELIGIBLE] {p['id']:<8} {p['name']:<28} Rs. {p['price']:>10,.2f}")
    print(f"  Filtered eligible prices list: {eligible_prices}")

    # -------------------------------------------------------------------------
    # Part (b): Use map() to apply discount to eligible products
    # -------------------------------------------------------------------------
    # Method 1: Applying pure function directly to the list of eligible prices
    discounted_eligible_prices = list(map(calculate_discounted_price, eligible_prices))

    # Method 2: Applying pure function across the entire catalog to demonstrate mapping
    revised_catalog = list(
        map(
            lambda p: {
                "id": p["id"],
                "name": p["name"],
                "original_price": p["price"],
                "discounted_price": calculate_discounted_price(p["price"]),
                "discount_received": round(p["price"] - calculate_discounted_price(p["price"]), 2),
                "is_discounted": p["price"] > THRESHOLD,
            },
            product_catalog,
        )
    )

    print("\n" + "-" * 70)
    print("Part (b): Apply discount using map() and calculate_discounted_price()")
    print("-" * 70)
    print(f"  Eligible original prices : {eligible_prices}")
    print(f"  After 20% discount (map) : {discounted_eligible_prices}")

    print("\n  Full catalog mapped with pure function:")
    print(f"  {'ID':<8} {'Product Name':<25} {'Original':>12} {'Discounted':>12} {'Savings':>10} {'Status'}")
    print("  " + "-" * 75)
    for p in revised_catalog:
        status = "20% OFF" if p["is_discounted"] else "Regular (<= 1000)"
        print(
            f"  {p['id']:<8} {p['name']:<25} Rs. {p['original_price']:>8,.2f} "
            f"Rs. {p['discounted_price']:>8,.2f} Rs. {p['discount_received']:>6,.2f}  {status}"
        )

    # -------------------------------------------------------------------------
    # Part (d): Explain how the pure function avoids modifying the original list
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (d): Explanation - How Pure Function Avoids Modifying Original List")
    print("-" * 70)

    # Programmatic demonstration of immutability
    original_prices_copy = [p["price"] for p in product_catalog]
    is_original_untouched = prices == original_prices_copy

    print(f"  [Verification Test]:")
    print(f"  - Original prices list before operations : {prices}")
    print(f"  - Original prices list after operations  : {original_prices_copy}")
    print(f"  - Did original list remain completely unchanged? -> {is_original_untouched}")
    print(f"  - Memory ID of original prices list : {id(prices)}")
    print(f"  - Memory ID of discounted prices    : {id(discounted_eligible_prices)} (Distinct new object)\n")

    explanation = """
1. Pass-by-Value Semantics for Immutable Numerics:
   In Python, numeric types (floats, ints) are strictly immutable. When 'calculate_discounted_price(price)'
   is invoked, it receives the primitive value. Mathematical operations (*, -) create and return
   a completely new float object; the caller's reference is never mutated.

2. Absence of In-Place List Mutation Operations:
   A non-pure (impure) function might modify the collection directly via index assignment
   (e.g., 'prices[i] = new_val') or methods like '.append()', '.pop()', or '.sort()'.
   The pure function 'calculate_discounted_price()' has no reference to the enclosing collection;
   it only receives an individual scalar number and returns a new scalar number.

3. Lazy Evaluation and New Collection Allocation by map():
   The 'map()' higher-order function creates an independent iterator that generates transformed
   values on demand. When wrapped in 'list(map(...))', Python allocates an entirely new list
   in heap memory, leaving the source list's memory address, elements, and length untouched.

4. Referential Transparency:
   Pure functions treat input data as read-only constants. This prevents catastrophic unintended
   consequences, like accidental double-discounting when code is rerun or accessed by multiple threads.
"""
    print(explanation.strip())
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_3()
