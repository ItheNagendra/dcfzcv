package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.user;
import io.restassured.response.Response;

public class userTest {

    // Faker instance for generating fake data
    Faker faker;
    
    // User POJO instance to hold the generated data
    user userpayload;

    @BeforeClass
    public void setupData() {
        // Initialize Faker and user payload
        faker = new Faker();
        userpayload = new user();

        // Setting random values to user payload using Faker
        userpayload.setId(faker.idNumber().hashCode()); // Using hashCode for unique ID
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassword(faker.internet().password());
        userpayload.setPhone(faker.phoneNumber().cellPhone());
    }

    // Test method to create a new user
    @Test(priority = 1)
    public void createUser() {
        // Send a POST request to create a user
        Response response = userEndpoints.createUser(userpayload);

        // Log the response for debugging
        response.then().log().all();

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // Test method to get the created user
    @Test(priority = 2)
    public void getUser() {
        // Send GET request to retrieve the user by username
        Response response = userEndpoints.readUser(userpayload.getUsername());

        // Log the response for debugging
        response.then().log().all();

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // Test method to update the user details
    @Test(priority = 3)
    public void updateUser() {
        // Update user data using Faker
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());

        // Send PUT request to update the user
        Response response = userEndpoints.updateUser(userpayload.getUsername(), userpayload);

        // Log the response for debugging purposes
        response.then().log().body().statusCode(200);

        // Validate that the user was updated successfully
        Response responseAfterUpdate = userEndpoints.readUser(userpayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    // Test method to delete the user
    @Test(priority = 4)
    public void deleteUser() {
        // Send DELETE request to delete the user
        Response response = userEndpoints.deleteUser(userpayload.getUsername());

        // Log the response for debugging
        response.then().log().all();

        // Validate that the user was deleted successfully
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
