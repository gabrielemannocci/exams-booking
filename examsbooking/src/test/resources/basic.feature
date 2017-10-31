Feature: Register a student for an exam.
  
Scenario: Student registration
Given Registration page
When I'll select exam with id 1 from dropdown list
And I'll insert student information <mario>, <rossi>, <1267367>, <mario.rossi@stud.unifi.it>
Then User <rossi> is correctly registered for exam with id 1

Scenario: Student registration
Given Registration page
When I'll select exam with id 1 from dropdown list
And I'll insert student information incomplete such as <mario>, <rossi>, <mario.rossi@stud.unifi.it>
Then Invalid student exception is thrown
