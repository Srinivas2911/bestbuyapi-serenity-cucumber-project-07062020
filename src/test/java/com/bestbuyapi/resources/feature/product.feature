@Products
Feature: Testing the Product features on the best buy application

  Scenario: Verify if best buy product application can be accessed by users
    When User sends a GET request to the product endpoint,they must get back a valid status code 200

  Scenario Outline: Create a new Product & verify if the product is added
    When I create a new product by providing the information name "<name>" type "<type>" upc "<upc>" price "<price>"description "<description>" model "<model>"
    Then I verify that product is created with name "<name>"
    And I verify that the product with "id" is created

    Examples:
      | name            | type    | upc         | price | description                   | model      |
      | JVM XYZ Mobile  | iOS5656 | 36445464656 | 99.99 | latest high-tech mobile phone | JVM13243UK |
      | JVM ABC Mobile2 | iOS5656 | 36445464656 | 99.99 | latest high-tech mobile phone | JVM13243UK |

  Scenario:Getting the product by id and verifying
    When I get the product created with "id"
    Then I verify that the product with "id" is obtained

  Scenario:Updating the product created and verify it is updated with status 200
    When I update the product with name upc price
    Then I verify that the information is updated in the product

  Scenario:Deleting the product created and verify it is deleted
    When I delete the product created with "id"
    Then I verify that the product is deleted and get a status is 404
