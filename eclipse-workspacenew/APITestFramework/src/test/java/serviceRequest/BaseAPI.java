package serviceRequest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import util.ConfigUtil;

public class BaseAPI {

	protected static final String BASE_URL = ConfigUtil.getProperty("base.url");
	protected static final String WEATHER_ENDPOINT = ConfigUtil.getProperty("weather.endpoint");
    protected static final String FORECAST_ENDPOINT = ConfigUtil.getProperty("forecast.endpoint");
	protected static final String LATITUDE = ConfigUtil.getProperty("latitude");
	protected static final String LONGITUDE = ConfigUtil.getProperty("longitude");
	protected static final String APPID = ConfigUtil.getProperty("appid");
	protected static final String INVALID_LATITUDE = ConfigUtil.getProperty("invalid_latitude");
	protected static final String INVALID_LONGITUDE = ConfigUtil.getProperty("invalid_longitude");
	protected static final String INVALID_APPID = ConfigUtil.getProperty("invalid_appid");
	
	private ExtentReports extentReports;
    private ExtentTest extentTest;
	
	static {
		RestAssured.baseURI = BASE_URL;
		
	}
	
	
}
