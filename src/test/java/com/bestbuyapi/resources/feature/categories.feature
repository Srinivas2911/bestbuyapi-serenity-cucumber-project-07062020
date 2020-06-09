@Categories
Feature: Testing the Categories features on the best buy application

  Scenario: Verify if best buy application categories can be accessed by users
    When User sends a GET request to the categories endpoint,they must get back a valid status code 200

  Scenario:Create a new Category & verify if the Category is added
    When I create a new category by providing the information name "<Roger Moore Films>" and id "<id>"
    Then I verify that the Category with "<Roger Moore Films>" is created

  Scenario:Getting the Category by id and verifying
    When I get the Category created with "id"
    Then I verify that Category with "id" is obtained

  Scenario:Updating the Category created and verify it is updated with status 200
    When I update the Category with name
    Then I verify that the information is updated in the Category

  Scenario:Deleting the Category created and verify it is deleted
    When I delete the Category created with "id"
    Then I verify that the Category is deleted and get the status 404
