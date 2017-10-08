Feature: Register a student for an exam.
  
Scenario Outline: Student registration
Given Registration page
When I'll select an exam from dropdown list
And I'll insert student information <first_name>, <last_name>, <id_number>, <email>
Then User <last_name> is correctly registered for exam
	
	Examples:
    | first_name   | last_name | id_number  |       email                 | 
    |    mario     |  rossi    |  1267367   |  mario.rossi@stud.unifi.it  |