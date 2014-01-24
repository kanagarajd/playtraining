package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class User extends Controller
{
	private static Form<models.User> userForm = Form.form(models.User.class);
	public static Result all() {
		List<models.User> users = models.User.all();
		return ok(views.html.userlist.render(users));
	}

	public static Result create() {
		return ok(views.html.registration.render(userForm));
	}

	public static Result save() {
		Form<models.User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors())
		{
			return badRequest(views.html.registration.render(filledForm));
		}
		else
		{
			models.User.save(filledForm.get());
			return redirect(routes.User.all());
		}
	}
	public static Result delete(Long id){
		models.User.delete(id);
		return redirect(routes.User.all());
	}
	public static Result read(Long id){
		models.User user = models.User.read(id);
		System.out.println("pwd:"+user.getPassword());
		return ok(views.html.userupdate.render(userForm.fill(user)));
	}
	public static Result update()
	{
		System.out.println("Inside update.");
		Form<models.User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors())
		{
			System.out.println("Inside update.hasErrors.");
			return badRequest(views.html.userupdate.render(filledForm));
		}
		else
		{
			models.User.update(filledForm.get());
			return redirect(routes.User.all());
		}
	}
}
