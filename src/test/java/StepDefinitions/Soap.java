package StepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Soap
{
    @Given("^Retrieve Soap request using Rest Assured Framework$")
    public void Retrieve() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(".\\SoapRequest\\SoapRequestFile.xml"));
        RestAssured.baseURI="http://currencyconverter.kowabunga.net";

        Response response=
                 given()
                .header("Content-Type", "text/xml")
                .and()
                .body(IOUtils.toString(fileInputStream,"UTF-8"))
                .when()
                .post("/converter.asmx")
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();

        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
        String rate=jsXpath.getString("GetConversionRateResult");
        System.out.println("rate returned is: " +  rate);
    }


}
