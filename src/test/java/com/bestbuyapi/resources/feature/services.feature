@Services

Feature: Testing the Services features on the best buy application

  Scenario: Verify if best buy application services can be accessed by users
    When User sends a GET request to the services endpoint,they must get back a valid status code 200

  Scenario:Create a new Service & verify if the Service is added
    When I create a new service by providing the information name "<Championship>"
    Then I verify that the service with "<Championship>" is created

  Scenario:Getting the services by id and verifying
    When I get the service created with "id"
    Then I verify that the services with "id" is obtained


  Scenario:Updating the Service created and verify it is updated with status 200
    When I update the service with name
    Then I verify that the information is updated in the services

  Scenario:Deleting the service created and verify it is deleted
    When I delete the service created with "id"
    Then I verify that the service is deleted and get a status is 404
