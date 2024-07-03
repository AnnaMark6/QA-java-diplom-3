package usermodel;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class UserCreation {

    private static String jsonString;
    public static String message;
    public static boolean success;
    public static String accessToken;
    public static String refreshToken;
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    public static final String REGISTER = "/auth/register";
    public static final String UPDATE = "/auth/user";



    public UserCreation(boolean success, String message, String accessToken, String refreshToken) {
        this.success = success;
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    static ObjectMapper mapper = new ObjectMapper();

    @Step("Создание учетной записи пользователя")
    public static UserCreation getResponseCreateUser(User user, int statusCode) throws JsonProcessingException {
        jsonString = mapper.writeValueAsString(user);
        Response response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URL)
                .body(jsonString)
                .when()
                .post(REGISTER)
                .then().log().all()
                .statusCode(statusCode)
                .extract()
                .response();
        success = response.path("success");
        message = response.path("message");
        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");
        return new UserCreation(success, message, accessToken, refreshToken);
    }


    @Step("Удаление пользователя")
    public static ValidatableResponse getResponseUserDeleted(String userAccessToken, int statusCode) {
        return RestAssured.given().log().all()
                .header("Authorization", userAccessToken)
                .baseUri(BASE_URL)
                .when()
                .delete(UPDATE)
                .then().log().all()
                .statusCode(statusCode);
    }

}
