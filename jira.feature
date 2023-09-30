Feature: Jira Ticket Management

Background:
Given Set the Endpoint
And set the auth

Scenario: Create the Jira Ticket
When Create new ticket with file '<filename>'
Then Vaildate the status code as 201
Examples:
|filename|
|data.json|

Scenario: Update the jira
When update the ticket with string body '{"fields": {"description": "Bug creation Using REST API for testing"}}'
Then Vaildate the status code as 204

Scenario: get the current ticket
When get the ticket
Then Vaildate the status code as 200

Scenario: Delete the ticket
When delete the ticket
Then Vaildate the status code as 204

Scenario: To verify the ticket got deleted.
When get the ticket