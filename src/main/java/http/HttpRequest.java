package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.HttpHeaders;
import constants.HttpMethod;
import util.HttpRequestUtils;
import util.IOUtils;

public class HttpRequest {
	
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
	
	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, String> parameters = new HashMap<String, String>();
	private RequestLine requestLine;
	
	public HttpRequest( InputStream inputStream ) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        String line = br.readLine();
	        if (line == null) {
	            return;
	        }
	
	        requestLine = new RequestLine(line);
	        
	        line = br.readLine();
	        while (!line.equals("")) {	            
	            log.debug("header : {}", line);
	
	            String [] tokens = line.split(":");
	            if( tokens != null && tokens.length > 1 ) {
	            	headers.put( tokens[0].trim(), tokens[1].trim() );
	            }
	            
	            line = br.readLine();    
	        }
	        
	        if( HttpMethod.POST.equals(requestLine.getMethod()) ) {
	        	String body = IOUtils.readData(br, Integer.parseInt(headers.get(HttpHeaders.CONTENT_LENGTH)));
	        	parameters = HttpRequestUtils.parseQueryString(body);
	        }
	        else {
	        	parameters = requestLine.getParameters();
	        }
		}
		catch( Exception e ) {
			log.error(e.getMessage());
		}
	}

	public String getHeader(String key) {
		return this.headers.get(key);
	}
	
	public String getParameter(String key) {
		return this.parameters.get(key);
	}
	
	public HttpMethod getMethod() {
		return this.requestLine.getMethod();
	}
	
	public String getPath() {
		return this.requestLine.getPath();
	}
}
