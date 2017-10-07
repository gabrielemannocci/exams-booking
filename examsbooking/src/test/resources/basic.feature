Feature: Register a student for an exam.
  
  Scenario: Student registration
	Given Registration page
	When I'll select an exam from dropdown list
	And I'll insert student information
	Then User is registered for exam
