package http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

public class HttpResponseTest {
	private static final String TEST_DIRECTORY = "./src/test/resources/"; 
	private static final String INDEX = "/index.html";
	
	private static final String FORWARD_TEST_FILE = "Http_Forward.txt";
	private static final String REDIRECT_TEST_FILE = "Http_Redirect.txt";
	private static final String COOKIE_TEST_FILE = "Http_Cookie.txt";
	
	@Test 
	public void responseForward() throws Exception { 
		// Http_Forward.txt 결과는 응답 body에 index.html이 포함되어 있어야 한다. 
		HttpResponse response = new HttpResponse(createOutputStream(FORWARD_TEST_FILE)); 
		response.forward(INDEX); 
	} 
	
	@Test 
	public void responseRedirect() throws Exception { 
		// Http_Redirect.txt 결과는 응답 headere에 Location 정보가 /index.html로 포함되어 있어야 한다. 
		HttpResponse response = new HttpResponse(createOutputStream(REDIRECT_TEST_FILE)); 
		response.sendRedirect(INDEX); 
	} 
	
	@Test 
	public void responseCookies() throws Exception { 
		// Http_Cookie.txt 결과는 응답 header에 Set-Cookie 값으로 logined=true 값이 포함되어 있어야 한다. 
		HttpResponse response = new HttpResponse(createOutputStream(COOKIE_TEST_FILE)); 
		response.addHeader("Set-Cookie", "logined=true"); 
		response.sendRedirect(INDEX); 
	} 
	
	private OutputStream createOutputStream(String filename) throws FileNotFoundException { 
		return new FileOutputStream(new File(TEST_DIRECTORY + filename)); 
	}
}
