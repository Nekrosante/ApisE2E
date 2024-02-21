#Autor: Luis Miguel Arcia Moreno
@PetStoreUsers
Feature:  Verify the operation of the pet store's services
  As a regular user,
  I want to use the services of the pet store,
  To verify the operation of those related to user services.

  Background:
    Given that the services of the pet store are available

    @CP001 @CreateUser
    Scenario: Verify the creation of a user in the pet store
      When submit the data of a valid user to the Create User method
      Then validate the response from the user creation service

    @CP002 @SearchUser
    Scenario: Verify the user search method of the pet store
      When execute the search method with a valid username
      Then validate that the search is successful

    @CP003 @UpdateUser
    Scenario: Verify the method that updates user information in the pet store
      When execute the user update method with valid data
      Then validate the user update using the search method

    @CP003 @DeleteUser
    Scenario: Verify the deletion of a previously created user
      When create a new user using the corresponding method
      And delete the user from the pet store
      Then verify with the search method that the user is deleted