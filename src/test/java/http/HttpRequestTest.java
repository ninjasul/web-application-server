package http;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import http.HttpRequest;

public class HttpRequestTest {
    
	private static final String TEST_DIRECTORY = "./src/test/resources/";
	private static final String HTTP_REQUEST_GET_TEST_FILE = "Http_GET.txt";
	private static final String HTTP_REQUEST_POST_TEST_FILE = "Http_POST.txt";
	
	@Test 
	public void request_GET() throws Exception { 
		InputStream in = new FileInputStream(new File(TEST_DIRECTORY + HTTP_REQUEST_GET_TEST_FILE)); 
		HttpRequest request = new HttpRequest(in); 
		
		assertEquals("GET", request.getMethod().name()); 		
		assertEquals("/user/create", request.getPath()); 
		assertEquals("keep-alive", request.getHeader("Connection")); 
		assertEquals("javajigi", request.getParameter("userId")); 
	}
    
	@Test 
	public void request_POST() throws Exception { 
		InputStream in = new FileInputStream(new File(TEST_DIRECTORY + HTTP_REQUEST_POST_TEST_FILE)); 
		HttpRequest request = new HttpRequest(in); 
		
		assertEquals("POST", request.getMethod().name()); 
		assertEquals("/user/create", request.getPath()); 
		assertEquals("keep-alive", request.getHeader("Connection")); 
		assertEquals("javajigi", request.getParameter("userId")); }
}
