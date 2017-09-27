package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.HttpHeaders;
import constants.Urls;
import db.DataBase;
import http.HttpRequest;
import http.HttpResponse;
import model.User;

public class LoginController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Override
	public void service(HttpRequest request, HttpResponse response) {
		User user = DataBase.findUserById(request.getParameter("userId"));
		if (user != null) {
			if (user.login(request.getParameter("password"))) {
				response.addHeader(HttpHeaders.SET_COOKIE, "logined=true");
				response.sendRedirect(Urls.Pages.INDEX);
			} else {
				response.sendRedirect(Urls.Pages.LOGIN_FAILED);
			}
		} else {
			response.sendRedirect(Urls.Pages.LOGIN_FAILED);
		}
	}

}