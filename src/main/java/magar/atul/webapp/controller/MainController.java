package magar.atul.webapp.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import magar.atul.webapp.entity.User;
import magar.atul.webapp.service.UserService;


@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
    String currID = null;
    
    @GetMapping(value="/")
    public String showIndexPage(ModelMap model, 
   		 @RequestParam(value="name", required=false, defaultValue="World") String name){
	     model.addAttribute("name", name);    
		 return "index";
    }
    
//    public boolean isNumber(String s)
//	 {
//		 if(s == null)
//			 return false;
//		 try
//		 {
//			 double db = Double.parseDouble(s);
//		 }
//		 catch(NumberFormatException e)
//		 {
//			 return false;
//		 }
//		 return true;
//	 }
//	 
	 @PostMapping("/update")                     
	 public String saveDetails(@RequestParam("id") String id, ModelMap modelMap) {
	        
		try 
		{
			User user = userService.GetUserById(Integer.valueOf(id));
			ArrayList<User> userList = new ArrayList<User>();
			if(user != null)
			{
				userList.add(user);
				Iterable<User> users = userList;
				currID = id;
				modelMap.put("user", users);
			}
			else
				return "userError";
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		modelMap.put("ID", id);
       return "update";           
	 }
	 
	 @PostMapping("/update2")                     
	 public String updateDetails(@RequestParam("nameedit") String nameedit, @RequestParam("emailedit") String emailedit, @RequestParam("passwordedit") String passwordedit, ModelMap modelMap) {
		 ArrayList<User> userList = new ArrayList<User>();
		 try
		 {
			 User u = userService.GetUserById(Integer.valueOf(currID));
			 userService.setUser(u, nameedit, emailedit, passwordedit);
			 userList.add(u);
			 Iterable<User> users = userList;
			 modelMap.put("user", users);
		 }
		 catch (NumberFormatException e)
		 {
			e.printStackTrace();
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 modelMap.put("IDedit", currID);
		 
		 return "update2";
	 }
}
