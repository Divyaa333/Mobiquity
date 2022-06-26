package StepDefinitions;
import Utilities.Common_Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

public class Rest_Assured_Steps
{
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String Comments_URL = "https://jsonplaceholder.typicode.com/comments";
    int ID_No;

    @Given("^Retrieve the user details for user “Delphine\"$")
    public void Retrieve()
    {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.get();
        List<String> jsonResponse = response.jsonPath().getList("username");
       for (int i=0;i<jsonResponse.size();i++)
       {
           if(jsonResponse.get(i).equalsIgnoreCase("Delphine"))
           {
               List<Integer> ID = response.jsonPath().getList("id");
               System.out.println("ID:"+ID.get(i));
               ID_No= ID.get(i);
               List<String> name = response.jsonPath().getList("name");
               System.out.println("name:"+name.get(i));
               List<String> username = response.jsonPath().getList("username");
               System.out.println("username:"+username.get(i));
               List<String> email = response.jsonPath().getList("email");
               System.out.println("email:"+email.get(i));
               List<Map<String, String>> address = response.jsonPath().getList("address");
               System.out.println("Street:"+address.get(i).get("street"));
               System.out.println("suite:"+address.get(i).get("suite"));
               System.out.println("city:"+address.get(i).get("city"));
               System.out.println("zipcode:"+address.get(i).get("zipcode"));
               List<Map<String, String>> Geo = response.jsonPath().getList("address.geo");
               System.out.println("lat:"+Geo.get(i).get("lat"));
               System.out.println("lng:"+Geo.get(i).get("lng"));
               List<String> phone = response.jsonPath().getList("phone");
               System.out.println("phone:"+phone.get(i));
               List<String> website = response.jsonPath().getList("website");
               System.out.println("website:"+website.get(i));
               List<Map<String, String>> company = response.jsonPath().getList("company");
               System.out.println("Company Name: "+company.get(i).get("name"));
               System.out.println("catchPhrase: "+company.get(i).get("catchPhrase"));
               System.out.println("bs: "+company.get(i).get("bs"));
           }
       }
    }

    @Then("^Use the details fetched to make a search for the posts written by the user$")
    public void Search()
    {
        RestAssured.baseURI = POST_URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.get();
        List<Integer> jsonResponse = response.jsonPath().getList("id");
        for (int i=0;i<jsonResponse.size();i++)
        {
            if(jsonResponse.get(i).equals(ID_No))
            {
                List<String> title = response.jsonPath().getList("title");
                System.out.println("title:"+title.get(i));
                List<String> body = response.jsonPath().getList("body");
                System.out.println("body:"+body.get(i));
            }
        }
    }

    @Then("^fetch the comments and validate if the emails in the comment section are in the proper format$")
    public void Comments()
    {
        RestAssured.baseURI = Comments_URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.get();
        List<Integer> jsonResponse = response.jsonPath().getList("id");
        for (int i=0;i<jsonResponse.size();i++)
        {
                List<String> email = response.jsonPath().getList("email");
                System.out.println("email:"+email.get(i));
                String Emailformat=Common_Utils.Email_Format_Validator(email.get(i));
                System.out.println("Emailformat: "+Emailformat);
        }
    }
}

