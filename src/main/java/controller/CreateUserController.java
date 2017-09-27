package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Urls;
import db.DataBase;
import http.HttpRequest;
import http.HttpResponse;
import model.User;

public class CreateUserController extends AbstractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);
	
	@Override
	public void service(HttpRequest request, HttpResponse response) {
		 User user = new User(	
             	request.getParameter("userId"), 
             	request.getParameter("password"), 
             	request.getParameter("name"),
             	request.getParameter("email"));
             
		 LOGGER.debug("user : {}", user);
         DataBase.addUser(user);
         response.sendRedirect(Urls.Pages.INDEX);
	}

}
