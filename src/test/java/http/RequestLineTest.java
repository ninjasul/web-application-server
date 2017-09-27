package webserver;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;

import http.HttpRequest;
import http.RequestLine;

public class RequestLineTest {
	
	@Test 
	public void createMethod() throws Exception { 
		RequestLine line = new RequestLine("GET /index.html HTTP/1.1");
		assertEquals("GET", line.getMethod().name());
		assertEquals("/index.html", line.getPath());
		
		line = new RequestLine("POST /index.html HTTP/1.1");
		assertEquals("POST", line.getMethod().name());
		assertEquals("/index.html", line.getPath());
	}
	
	@Test 
	public void createPathAndParameters() throws Exception { 
		RequestLine line = new RequestLine("GET /user/create?userId=javajigi&password=pass HTTP/1.1");
		
		assertEquals("GET", line.getMethod().name());
		assertEquals("/user/create", line.getPath());
		
		Map<String, String> params = line.getParameters();
		assertEquals(2, params.size());
		assertEquals("javajigi", params.get("userId"));
		assertEquals("pass", params.get("password"));
	}
}
