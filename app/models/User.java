package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model
{
	@Required
	private String fullName;
	private String company;
	@Email
	private String email;
	@Required
	private String password;
	
	private static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
	@Id
	private Long id;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public static void save(User user)
	{
		user.save();
	}
	public static List<User> all()
	{
		return find.all();
	}
	public static void delete(Long id)
	{
		User user = find.byId(id);
		user.delete();
	}
	public static User read(Long id)
	{
		return find.byId(id);
	}
	public static void update(User user)
	{
		System.out.println("pwd:"+user.getPassword());
		user.update();
	}
}
