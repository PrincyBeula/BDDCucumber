$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("BookFlight.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: Princy Gracious"
    }
  ],
  "line": 4,
  "name": "Book a flight in Trainline.com",
  "description": "",
  "id": "book-a-flight-in-trainline.com",
  "keyword": "Feature",
  "tags": [
    {
      "line": 3,
      "name": "@FunctionalTest"
    }
  ]
});
formatter.before({
  "duration": 6712896000,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Book a one-way flight from London to Leeds",
  "description": "",
  "id": "book-a-flight-in-trainline.com;book-a-one-way-flight-from-london-to-leeds",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 6,
      "name": "@Demo"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "user is on the trainline homepage",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "user login to the website using the credentials \"princygracious@gmail.com\" and \"Admin@123\"",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "user enter the journey details",
  "rows": [
    {
      "cells": [
        "From",
        "To",
        "Type",
        "Date",
        "Option",
        "Time",
        "Adult",
        "Children"
      ],
      "line": 11
    },
    {
      "cells": [
        "London",
        "Leeds",
        "One Way",
        "Today",
        "Leaving at",
        "16:45",
        "1",
        "0"
      ],
      "line": 12
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "click on get tickets",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "user should be taken to the search results page",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "validate journey details are displayed properly",
  "rows": [
    {
      "cells": [
        "Source",
        "Destination",
        "Type",
        "Adult",
        "Children"
      ],
      "line": 16
    },
    {
      "cells": [
        "London",
        "Leeds",
        "Single",
        "1",
        "0"
      ],
      "line": 17
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "user click on continue button",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "select seating preferences",
  "rows": [
    {
      "cells": [
        "Direction",
        "Position",
        "Coach Type",
        "Preferences"
      ],
      "line": 20
    },
    {
      "cells": [
        "Forward facing",
        "Window",
        "Quiet",
        "Power socket"
      ],
      "line": 21
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "user click on continue button",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "user select delivery option \"eticket\"",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "user click on continue button",
  "keyword": "When "
});
formatter.step({
  "line": 25,
  "name": "user should get redirected to payment page",
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "get the reserved coach details",
  "keyword": "And "
});
formatter.match({
  "location": "BookFlightSteps.user_is_on_the_trainline_homepage()"
});
formatter.result({
  "duration": 1067909900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "princygracious@gmail.com",
      "offset": 49
    },
    {
      "val": "Admin@123",
      "offset": 80
    }
  ],
  "location": "BookFlightSteps.user_login_to_the_website_using_the_credentials_and(String,String)"
});
formatter.result({
  "duration": 3901921800,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.user_enter_the_journey_details(DataTable)"
});
formatter.result({
  "duration": 6603524000,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.click_getickets()"
});
formatter.result({
  "duration": 2903558300,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.verify_search_results_page()"
});
formatter.result({
  "duration": 84019900,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.validate_journey_details(DataTable)"
});
formatter.result({
  "duration": 316486700,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.click_continue_button()"
});
formatter.result({
  "duration": 2413788900,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.select_seating_preferences(DataTable)"
});
formatter.result({
  "duration": 4371267300,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.click_continue_button()"
});
formatter.result({
  "duration": 419245300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "eticket",
      "offset": 29
    }
  ],
  "location": "BookFlightSteps.user_select_delivery_option(String)"
});
formatter.result({
  "duration": 2800274300,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.click_continue_button()"
});
formatter.result({
  "duration": 392198800,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.verify_payment_page()"
});
formatter.result({
  "duration": 6481699300,
  "status": "passed"
});
formatter.match({
  "location": "BookFlightSteps.get_the_reserved_coach_details()"
});
formatter.result({
  "duration": 1745430700,
  "error_message": "java.lang.RuntimeException: Coach deatils is empty\r\n\tat com.framework.core.Helper.handleTestFaliure(Helper.java:28)\r\n\tat com.framework.pages.Payment.getReservationDetails(Payment.java:38)\r\n\tat stepdefs.BookFlightSteps.get_the_reserved_coach_details(BookFlightSteps.java:65)\r\n\tat ✽.And get the reserved coach details(BookFlight.feature:26)\r\n",
  "status": "failed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 1073258300,
  "status": "passed"
});
});