Feature: Rest Assured Framework

Scenario: Retrieve all the details for user "Delphine" using Rest Assured Framework and Cucumber

Given Retrieve the user details for user “Delphine"

Then Use the details fetched to make a search for the posts written by the user

Then  fetch the comments and validate if the emails in the comment section are in the proper format