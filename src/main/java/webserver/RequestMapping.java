package webserver;

import java.util.HashMap;
import java.util.Map;

import constants.Urls;
import controller.Controller;
import controller.CreateUserController;
import controller.ListUserController;
import controller.LoginController;

public class RequestMapping {
	
	private static Map<String, Controller> controllers = new HashMap<String, Controller>();
	
	static {
		controllers.put(Urls.CREATE_USER, new CreateUserController());
		controllers.put(Urls.LOGIN, new LoginController());
		controllers.put(Urls.LIST_USER, new ListUserController());
	}
	
	public static Controller getController(String requestUrl) {
		return controllers.get(requestUrl);
	}
}
