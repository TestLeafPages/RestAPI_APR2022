Feature: Issue Management - Jira

Scenario: Create Issue with Request body as file
Given Set the endpoint for Jira issue management
And Set the authentication for JiraAPI access
And Set the following headers in the input request
	|Content-Type	|application/json|
	|Accept				|application/json|
And Set the request body as 'CreateIssue.json' in the create issue request
When Send the Post request to the issue management
Then Validate the status code is 201
And validate the response field key contains 'AP'

Scenario: Get Issue with the key created above
Given Set the endpoint for Jira issue management
And Set the authentication for JiraAPI access
And Set the following headers in the input request
	|Accept	|application/json|
When Send the GET request to the issue management
Then Validate the status code is 200
And Validate the GET response contains the fields with values
	|fields.summary			|Create Issue - Restassured with Cucumber	|
	|fields.description	|Create a issue through BDD script				|