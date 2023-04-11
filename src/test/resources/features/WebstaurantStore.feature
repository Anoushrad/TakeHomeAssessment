Feature: Search and add stainless work table to cart

  Scenario Outline: Verify Search Result
    Given User navigates to WebstaurantStore application <browser> <region>
    When User searches for an item <itemName>
    Then User verifies page title contains searched item <itemName>
    And User verifies product title contains the word <text>

    @prod
    Examples: 
      | browser  | region | itemName               | text    |
      | "chrome" | "prod" | "Stainless work table" | "Table" |

  Scenario Outline: Verify User able to add last item on last page
    Given User navigates to WebstaurantStore application <browser> <region>
    When User searches for an item <itemName>
    And User navigates to the last page of search result
    And User adds last item from search result to the cart
    Then System displays notification pop-up <notifMessage>
    And User verifies the Cart Item Count shows correct number <itemCount>

    @prod
    Examples: 
      | browser  | region | itemName               | notifMessage                | itemCount |
      | "chrome" | "prod" | "Stainless work table" | "1 item added to your cart" |         1 |

  Scenario Outline: Verify User able remove items from the cart using Item Delete Icon functionality
    Given User navigates to WebstaurantStore application <browser> <region>
    When User searches for an item <itemName>
    And User adds item from search result to the cart <numOfItemAddToCart>
    And User redirects to Cart Page
    And User User clicks Delete Icon to remove the item from Cart
    Then User verifies Cart is empty <message>

    @prod
    Examples: 
      | browser  | region | itemName               | numOfItemAddToCart | message               |
      | "chrome" | "prod" | "Stainless work table" |                  1 | "Your cart is empty." |
      | "chrome" | "prod" | "Stainless work table" |                  5 | "Your cart is empty." |

  Scenario Outline: Verify User able remove items from the cart using Empty Cart Functionality
    Given User navigates to WebstaurantStore application <browser> <region>
    When User searches for an item <itemName>
    And User adds item from search result to the cart <numOfItemAddToCart>
    And User redirects to Cart Page
    And User clicks Empty Cart button to remove the item from Cart
    Then User verifies Cart is empty <message>

    @prod
    Examples: 
      | browser  | region | itemName               | text    | numOfItemAddToCart | message               |
      | "chrome" | "prod" | "Stainless work table" | "Table" |                  1 | "Your cart is empty." |
      | "chrome" | "prod" | "Stainless work table" | "Table" |                  3 | "Your cart is empty." |
