Feature: Service Now Incident Management - Incidents

Scenario: TC001 Create Incident with request body as file
Given Set the end point for Service now Incident Management
And Set the authentication for Service now application
And Set the Queryparam in the request
	|sysparm_fields	|sys_id, number, category, short_description, description	|
And Set the Content type in the create Incident request
And Set the accept in the create Incident request
And Set the request body as 'CreateRequest.json' in create Incident request
When Send the POST request to Service now Incident Management
Then Validate the status code as 201
And Validate the response that has number starts with INC
And Validate the following fields in the response
|Summary| Application not working|
|Description| Create Incident is returning 404|


Scenario: TC002 Create Incident with request body as file
Given Set the end point for Service now Incident Management
And Set the authentication for Service now application
And Set the Queryparam in the request
	|sysparm_fields	|sys_id, number, category, short_description, description	|
And Set the Content type in the create Incident request
And Set the accept in the create Incident request
And Set the request body as 'CreateRequestWithSoftware.json' in create Incident request
When Send the POST request to Service now Incident Management
Then Validate the status code as 201
And Validate the response that has number starts with INC

@CreateIncident @Regression @PostWithFile
Scenario Outline: TC003 Create Incident with request body as file
Given Set the end point for Service now Incident Management
And Set the authentication for Service now application
And Set the Queryparam in the request
	|sysparm_fields	|sys_id, number, category, short_description, description	|
And Set the Content type in the create Incident request
And Set the accept in the create Incident request
And Set the request body as <File_Name> in create Incident request
When Send the POST request to Service now Incident Management
Then Validate the status code as 201
And Validate the response that has number starts with INC

Examples:
|File_Name												|
|'CreateRequest.json'							|
|'CreateRequestWithSoftware.json'	|

@RunTest @GetIncident
Scenario: TC004 Get all Incidents with category as filter
Given Set the end point for Service now Incident Management
And Set the authentication for Service now application
And Set the Queryparam in the request
|sysparm_fields	|sys_id, number, category, short_description, description	|
|category				|software																									|
And Set the accept in the create Incident request
When Send the GET request to Service now Incident Management
Then Validate the status code as 200

