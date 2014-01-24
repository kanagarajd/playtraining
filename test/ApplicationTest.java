import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import models.User;

import org.fluentlenium.adapter.FluentTest;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.WS;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest{

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        //Content html = views.html.index.render("Your new application is ready.");
        //assertThat(contentType(html)).isEqualTo("text/html");
        //assertThat(contentAsString(html)).contains("Your new application is ready.");
    	
    	User user = new User();
    	user.setFullName("Juhomi");
    	
    	Form<models.User> userForm = Form.form(models.User.class);
    	userForm = userForm.fill(user);
    	Content html = views.html.userupdate.render(userForm);
    	assertThat(contentType(html)).isEqualTo("text/html");
    	assertThat(contentAsString(html)).contains("Juhomi");
    }
    
    @Test
    public void callRegistration()
    {
    	Result result = callAction(controllers.routes.ref.User.create());
    	assertThat(status(result)).isEqualTo(OK);
    	assertThat(contentType(result)).isEqualTo("text/html");
    	assertThat(charset(result)).isEqualTo("utf-8");
    }
    
    public void callDelete()
    {
    	Result result = callAction(controllers.routes.ref.User.delete(1));
    	assertThat(status(result)).isEqualTo(OK);
    	assertThat(contentType(result)).isEqualTo("text/html");
    	assertThat(charset(result)).isEqualTo("utf-8");
    }
   
    @Test
    public void badRoute() {
      Result result = routeAndCall(fakeRequest(GET, "/regist/xx"));
      assertThat(result).isNull();
    }
    
    @Test
    public void rightRoute() {
      Result result = routeAndCall(fakeRequest(GET, "/registration"));
      assertThat(result).isNotNull();
    }
    
    @Test
    public void testInServer(){
    	running(testServer(3333), new Runnable() {
    	      public void run() {
    	         assertThat(
    	           WS.url("http://localhost:3333/registration").get().get().getStatus()
    	         ).isEqualTo(OK);
    	         assertThat(
    	    	           WS.url("http://localhost:3333/registration").get().get().getBody()
    	    	         ).contains("Full Name");
    	      }
    	  });
    }
    
    @Test
    public void runInBrowser() {
        running(testServer(3333), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
               browser.goTo("http://localhost:3333/registration"); 
               assertThat(browser.$("#title").getTexts().get(0)).contains("Gati Signup");
               //assertThat(browser.$("#body").getText()).contains("This field is required");
               browser.fill("#fullName").with("Ravi");
               browser.fill("#password").with("Ravi");
               browser.$("#create").click();
               assertThat(browser.url()).isEqualTo("http://localhost:3333/users");
               assertThat(browser.$("#list").getText()).contains("Ravi");
            }
        });
    }
}
