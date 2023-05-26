package TestClassPackage;

//import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Commonfunctionpackage.API_Common_Function;
import Commonfunctionpackage.Utility_commonfunction;
import RequestRepositarypackage.post_req_repository;
//import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class post_Tc_1 {
@Test	
	
public static void execute () throws IOException {
for(int i=0;i<5;i++) {
	String requestBody=post_req_repository.post_req_Tc_1();
	int res_status_Code=API_Common_Function.response_statusCode(post_req_repository.base_URI(),post_req_repository.post_resource(), post_req_repository.post_req_Tc_1());
	
	if( res_status_Code==201) 
	  {
		String responseBody=API_Common_Function.response_Body(post_req_repository.base_URI(),post_req_repository.post_resource() ,post_req_repository.post_req_Tc_1());
		System.out.println(responseBody);
		post_Tc_1.validator(responseBody, res_status_Code,requestBody);
		Utility_commonfunction.evidencefilecreater("post_Tc_1",post_req_repository.post_req_Tc_1() , responseBody);
		break;
	   }
		else 
		{
			System.out.println("correct status code is not found hence retrying the API");
	     }
         }
          }
	public static void validator(String responseBody, int res_status_Code,String requestBody) throws IOException {
		//step3: parse the response body
		JsonPath jsp = new JsonPath(responseBody);		
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_id = jsp.getString("id");
        String res_createdAt = jsp.getString("createdAt");
        res_createdAt = res_createdAt.substring(0,10); 	
	
	//step2:fetch request body parameter values
	JsonPath jsprequest=new JsonPath (requestBody);
	String req_name =jsprequest.getString("name");
	String req_job=jsprequest.getString("job");
	
	//generate date in format as shown and received in response
	LocalDateTime Date=LocalDateTime.now();
	String New_date=Date.toString().substring(0,10);
	        //step7:Validate response body parameters
	        Assert.assertEquals(res_status_Code,201);
	        Assert.assertEquals(res_name,req_name);
	        Assert.assertEquals(res_job,req_job);
	        Assert.assertNotNull(res_id);
	        Assert.assertEquals(res_createdAt,New_date);
	       
	        
	


	}
}
