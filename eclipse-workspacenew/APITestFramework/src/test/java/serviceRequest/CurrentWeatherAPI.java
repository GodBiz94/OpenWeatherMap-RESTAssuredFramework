package serviceRequest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CurrentWeatherAPI extends BaseAPI{

	@Test
	public void testCurrentWeather() {
		Response response = RestAssured.given()
				.queryParam("lat", LATITUDE)
				.queryParam("lon", LONGITUDE)
				.queryParam("appid", APPID)
				.when()
				.get(WEATHER_ENDPOINT)
				.then()
				.statusCode(200)
				.body("coord.lon", equalTo(Float.parseFloat(LONGITUDE)))
				.body("coord.lat", equalTo(Float.parseFloat(LATITUDE)))
				.body("weather[0].id", equalTo(721))
				.body("weather[0].main", equalTo("Haze"))
				.body("weather[0].description", equalTo("haze"))
				.body("weather[0].icon", equalTo("50n"))
				.body("base", equalTo("stations"))
				.body("sys.type", equalTo(1))
				.body("sys.id", equalTo(9114))
				.body("sys.country", equalTo("IN"))
				.body("sys.sunrise", equalTo(1716161069))
				.body("sys.sunset", equalTo(1716208917))
				.body("timezone", equalTo(19800))
				.body("name", equalTo("Kolkata"))
				.body("cod", equalTo(200))
				.extract()
				.response();

		System.out.println(response.asString());

	}

	@Test
	public void testFiveDayForecast() {
		Response response = RestAssured.given()
				.queryParam("lat", LATITUDE)
				.queryParam("lon", LONGITUDE)
				.queryParam("appid", APPID)
				.when()
				.get(FORECAST_ENDPOINT)
				.then()
				.statusCode(200)
				.body("cnt", equalTo(40))
				.body("list.size()", equalTo(40))
				.body("list[0].dt", notNullValue())
				.body("list[0].main.temp", notNullValue())
				.body("list[0].main.feels_like", notNullValue())
				.body("list[0].main.temp_min", notNullValue())
				.body("list[0].main.temp_max", notNullValue())
				.body("list[0].main.pressure", notNullValue())
				.body("list[0].main.humidity", notNullValue())
				.body("list[0].weather[0].id", notNullValue())
				.extract()
				.response();

		System.out.println(response.asString());		         
	}
	
	@Test
	public void testInvalidCityForWeather() {
		Response response = RestAssured.given()
				.queryParam("lat", INVALID_LATITUDE)
				.queryParam("lon", INVALID_LONGITUDE)
				.queryParam("appid", APPID)
				.when()
				.get(WEATHER_ENDPOINT)
				.then()
				.statusCode(400)
				.body("message", equalTo("wrong longitude"))
				.extract()
				.response();
	}
	
	@Test
	public void testInvalidCityForForecast() {
		Response response = RestAssured.given()
				.queryParam("lat", INVALID_LATITUDE)
				.queryParam("lon", INVALID_LONGITUDE)
				.queryParam("appid", APPID)
				.when()
				.get(FORECAST_ENDPOINT)
				.then()
				.statusCode(400)
				.body("message", equalTo("wrong longitude"))
				.extract()
				.response();
	}
	
	@Test
	public void testInvalidAppIdForWeather() {
		Response response = RestAssured.given()
				.queryParam("lat", LATITUDE)
				.queryParam("lon", LONGITUDE)
				.queryParam("appid", INVALID_APPID)
				.when()
				.get(WEATHER_ENDPOINT)
				.then()
				.statusCode(401)
				.body("message", equalTo("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."))
				.extract()
				.response();
	}
	
	@Test
	public void testInvalidAppIdForForecast() {
		Response response = RestAssured.given()
				.queryParam("lat", LATITUDE)
				.queryParam("lon", LONGITUDE)
				.queryParam("appid", INVALID_APPID)
				.when()
				.get(FORECAST_ENDPOINT)
				.then()
				.statusCode(401)
				.body("message", equalTo("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."))
				.extract()
				.response();
	}



}
