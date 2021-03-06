Feature: Adding membership and dependents to the employee

  Background:
    * user enters valid admin credentials
    * user clicks on login button
    * navigates to PIM option and click EmployeeList
    * selects first employee

  @test @addDependents
  Scenario: Adding employee dependents (US-3)
    And select Dependends
    And clicks on add button
    When admin user able to add all dependent details
    Then click save button

  @test @addMembership
  Scenario Outline: Adding membership to the employee (US-14)
    When click the membership option
    And click the add button
    Then user can select "<membershipOption>" from drop down list
    And click on the subscription paid by drop down and select "<subscriptionPaidBy>"
    And enters the subscription "<amount>" in the text box
    And click on the currency drop down and select "<currency>"
    And click the Subscription Start Date field and select "<subscriptionMonth>" "<subscriptionYear>"
    And click on the subscription renewal date calendar and select "<renewalMonth>" "<renewalYear>"
    Then click on save button

    Examples:
      | membershipOption | subscriptionPaidBy | amount | currency             | subscriptionMonth | subscriptionYear | renewalMonth | renewalYear
      | gold             | Company            | 200    | United States Dollar | Jun               | 2022             | Jul          | 2023
      | Gym              | Individual         | 300    | United States Dollar | Apr               | 2022             | Apr          | 2023


