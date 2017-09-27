package http;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.HttpMethod;
import util.HttpRequestUtils;

public class RequestLine {

	private static final Logger LOG = LoggerFactory.getLogger(HttpRequest.class);

	private HttpMethod method;
	private String path;
	private Map<String, String> parameters = new HashMap<String, String>();
	
	public RequestLine(String requestLine) {
		processRequestLine(requestLine);
	}
	
	public void processRequestLine(String requestLine) {
		LOG.debug("request line : {}", requestLine);
		String[] tokens = requestLine.split(" ");

		if( tokens == null || tokens.length < 3 ) {
			throw new IllegalArgumentException(requestLine + "이 형식에 맞지 않습니다.");
		}
		
		method = HttpMethod.valueOf(tokens[0]);

		if (method.equals(HttpMethod.GET)) {
			int index = tokens[1].indexOf("?");

			if (index < 0) {
				this.path = tokens[1];
			} else {
				this.path = tokens[1].substring(0, index);
				parameters = HttpRequestUtils.parseQueryString(tokens[1].substring(index + 1));
			}
		} else if (method.equals(HttpMethod.POST)) {
			this.path = tokens[1];
		}
	}

	public HttpMethod getMethod() {
		return this.method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}
}
