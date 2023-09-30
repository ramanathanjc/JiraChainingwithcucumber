package stepdefinition;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class setp {
	
	public RequestSpecification Input;
	public static Response response;
	public static String Issue_Id;
	
	
	@Given("Set the Endpoint")
	public void set_the_endpoint() {
		
		RestAssured.baseURI="https://ramjiratesting.atlassian.net/rest/api/2/issue/";
	    
	}

	@And("set the auth")
	public void set_the_auth() {
		
		RestAssured.authentication=RestAssured.preemptive().basic("ramanathan.1106@gmail.com", 
				"ATATT3xFfGF00ck38eYZJ8yNhx-HcE1DxaBJWAb9f8-8KZ801HipEQoJuMvTG9uSLtp7bVZi9WS5i1lcjMbBxjrbHKEiiYx5hAbJshF1_IW3tiLr9K6SHS8fK0za5KfTg-4wBH4nz7VFv1VsYe_Dn4IakG9Lz6EV02-HnAmAeIrE804gFmxTaic=F4478C49");
			    
	}

	
	@When("Create new ticket with file {string}")
	public void create_new_ticket_with_file(String filename) {
	   
		File createfile = new File("./src/test/resources/"+filename);
		 Input = RestAssured.given().contentType("application/json").when().body(createfile);
		 
		  response = Input.post();
		  response.prettyPrint();
		  Issue_Id = response.jsonPath().get("id");
		  
		  System.out.println("Created new Issue ID is ===" +Issue_Id);
		
	}
	
	@When("update the ticket with string body {string}")
	public void update_the_ticket_with_string_body(String updateBody) {
	    
		Input =RestAssured.given().contentType("application/json").when().body(updateBody);
		 response = Input.put("/"+Issue_Id);
		
		 response.prettyPrint();
		 System.out.println("update the body in ticket");
	}
	
	@When("get the ticket")
	public void get_the_ticket() {
	   
	Input  = RestAssured.given();
	response = Input.get("/"+Issue_Id);
	
	response.prettyPrint();
	
	}
	
	@When("delete the ticket")
	public void delete_the_ticket() {
	
		Input = RestAssured.given();
		response = Input.delete("/"+Issue_Id);
		
		System.out.println("This Ticket got deleted === :" +Issue_Id );
	}
	
	@Then("Vaildate the status code as {int}")
	public void vaildate_the_status_code_as(Integer statusCode) {
		
		response.then().assertThat().statusCode(statusCode);
	    
	}
	



}
