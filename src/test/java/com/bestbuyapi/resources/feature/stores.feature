@Stores

Feature: Testing the Stores features on the best buy application

  Scenario: Verify if best buy stores application can be accessed by users
    When User sends a GET request to the stores endpoint,they must get back a valid status code 200

  Scenario Outline: Create a new Store & verify if the product is added
    When I create a new Store by providing the information name "<name>" type "<type>" address "<address>" address2 "<address2>" city "<city>" state "<state>" zip "<zip>" lat "<lat>" lng "<lng>" hours "<hours>"
    Then I verify that Store is created with name "<name>"
    And I verify that the Store with "id" is created

    Examples:
      | name             | type               | address             | address2 | city   | state | zip      | lat       | lng        | hours                                                                  |
      | Automation Store | Rest Assured Tools | 260 Rockland Avenue | Uxbridge | London | West  | HA10 88D | 45.958785 | -90.445596 | Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6 |


  Scenario:Getting the Store by id and verifying
    When I get the Store created with "id"
    Then I verify that the Store with "id" is obtained

  Scenario:Updating the Store created and verify it is updated with status 200
    When I update the store with name type
    Then I verify that the information is updated in the store

  Scenario:Deleting the Store created and verify it is deleted
    When I delete the Store created with "id"
    Then I verify that the Store is deleted and get a status 404



