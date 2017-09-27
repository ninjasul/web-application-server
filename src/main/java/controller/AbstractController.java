package controller;

import constants.HttpMethod;
import http.HttpRequest;
import http.HttpResponse;

public abstract class AbstractController implements Controller {

	@Override
	public void service(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		HttpMethod method = request.getMethod();
		
		if( method.isPost() ) {
			doPost(request, response);
		}
		else {
			doGet(request, response);
		}
	}

	protected void doPost(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void doGet(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		
	}

}
