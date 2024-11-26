package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.user;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class userEndpoints {

    // 1 - Create User
    public static Response createUser(user payload) {
        // Sending POST request to create a user
        Response response = given()
                .contentType(ContentType.JSON)  // Setting Content-Type to JSON
                .accept(ContentType.JSON)      // Expecting a JSON response
                .body(payload)                 // Adding the payload (request body)
        .when()
                .post(routes.post_url);        // Sending POST request to the defined URL in routes

        return response;  // Returning the response object
    }

    // 2 - Read User (Get user details by username)
    public static Response readUser(String userName) {
        // Sending GET request to fetch the user details by username
        Response response = given()
                .pathParam("username", userName)  // Passing the username as a path parameter
        .when()
                .get(routes.get_url);  // Sending GET request to the URL defined in routes

        return response;  // Returning the response object
    }

    // 3 - Update User
    public static Response updateUser(String userName, user payload) {
        // Sending PUT request to update the user details
        Response response = given()
                .contentType(ContentType.JSON)  // Setting Content-Type to JSON
                .accept(ContentType.JSON)      // Expecting a JSON response
                .pathParam("username", userName) // Passing the username as a path parameter
                .body(payload)                  // Adding the updated payload (request body)
        .when()
                .put(routes.update_url);  // Sending PUT request to update user at the URL defined in routes

        return response;  // Returning the response object
    }

    // 4 - Delete User
    public static Response deleteUser(String userName) {
        // Sending DELETE request to delete the user by username
        Response response = given()
                .pathParam("username", userName)  // Passing the username as a path parameter
        .when()
                .delete(routes.delete_url);  // Sending DELETE request to the URL defined in routes

        return response;  // Returning the response object
    }
}
