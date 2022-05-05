Feature: User shopping

  Scenario: User wants to buy items at the shop.

    Given User is at directory products page
    And There are products in the shop available for purchase
    When User adds products in the shopping cart
    And User clicks on the cart button
    Then User proceeds to complete the purchase with checkout button
