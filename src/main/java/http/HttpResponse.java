package http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.HttpHeaders;

public class HttpResponse {

	private Map<String, String> headers = new HashMap<String, String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponse.class);
	private DataOutputStream dos = null;

	private static final String CSS_FILE_EXTENSION = ".css";
	private static final String JS_FILE_EXTENSION = ".js";

	public HttpResponse(OutputStream os) {
		dos = new DataOutputStream(os);
	}

	public void forward(String url) {
		try {
			byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());

			if (url.endsWith(JS_FILE_EXTENSION)) {
				headers.put(HttpHeaders.CONTENT_TYPE, "application/javascript");
			} else if (url.endsWith(CSS_FILE_EXTENSION)) {
				headers.put(HttpHeaders.CONTENT_TYPE, "text/css");
			} else {
				headers.put(HttpHeaders.CONTENT_TYPE, "text/html;charset=utf-8");
			}
			headers.put(HttpHeaders.CONTENT_LENGTH, body.length + "");
			response200Header();
			responseBody(body);
		} catch (Exception e) {

		}
	}

	public void forwardBody(String body) {
		byte[] contents = body.getBytes();
		headers.put(HttpHeaders.CONTENT_TYPE, "text/html;charset=utf-8");
		headers.put(HttpHeaders.CONTENT_LENGTH, contents.length + "");
		response200Header();
		responseBody(contents);
	}
	
	public void sendRedirect(String url) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			processHeaders();
			dos.writeBytes(HttpHeaders.LOCATION  + ": " + url + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	private void response200Header() {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			processHeaders();
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void processHeaders() {
		try {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				dos.writeBytes(entry.getKey() + ": " + entry.getValue() + "\r\n");
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void responseBody(byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
