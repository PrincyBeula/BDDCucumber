#Author: Princy Gracious

@FunctionalTest
Feature: Book a flight in Trainline.com

  @Demo
  Scenario: Book a one-way flight from London to Leeds
    Given user is on the trainline homepage
    Then user login to the website using the credentials "princygracious@gmail.com" and "Admin@123"
    When user enter the journey details
    |From  | To    | Type    | Date  | Option    | Time   | Adult | Children|
    |London| Leeds | One Way | Today | Leaving at| 16:45  |  1    |   0     |
    And click on get tickets
    Then user should be taken to the search results page
    And validate journey details are displayed properly
    |Source  | Destination | Type   | Adult | Children |
    |London  | Leeds       | Single | 1     | 0        |
    Then user click on continue button
    And select seating preferences
    |Direction      | Position | Coach Type | Preferences |
    |Forward facing | Window   | Quiet      | Power socket|
    Then user click on continue button
    Then user select delivery option "eticket"
    When user click on continue button
    Then user should get redirected to payment page
    And get the reserved coach details
    
  @Demo
  Scenario: Book a two-way flight from London to Leeds
    Given user is on the trainline homepage
    Then user login to the website using the credentials "princygracious@gmail.com" and "Admin@123"
    When user enter the journey details
    |From    | To        | Type    | Date      | Option     | Time   | Return Date |Return Option |Return Time|Adult | Children|Children Age|
    |Stafford| Newcastle | Return  | 22-Oct-21 | Arriving by| 18:30  |  30-Oct-21  |Leaving at    | 10:00     |1     |   1     |5-15        |
    And click on get tickets
    Then user should be taken to the search results page
    And validate journey details are displayed properly
    |Source  | Destination | Type   | Adult | Children |
    |Stafford| Newcastle   | Return | 1     | 1        |
    Then user click on continue button
    And select seating preferences
    |ReserveSeat|Direction      | Position | Coach Type | Preferences |
    |Yes        |Forward facing | Window   | Quiet      | Power socket|
    Then user click on continue button
    Then user select delivery option "KioskTicket"
    When user click on continue button
    Then user should get redirected to payment page
    
  @Demo
 	Scenario: Clear the existing tickets reserved under Basket
    Given user is on the trainline homepage
    Then user login to the website using the credentials "princygracious@gmail.com" and "Admin@123"
    When user click on Basket link
    Then user should get redirected to payment page
    And user should be able to remove the existing tickets
    