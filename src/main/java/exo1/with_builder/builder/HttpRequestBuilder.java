package exo1.with_builder.builder;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {
	private String method;
	private String url;
	private String host;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> queryParams = new HashMap<>();
	private String body;
	private boolean keepAlive = true;
	private boolean followRedirects = false;
	private int timeoutMs = 5000;

	public HttpRequestBuilder method(String method) {
		this.method = method;
		return this;
	}

	public HttpRequestBuilder url(String url) {
		this.url = url;
		return this;
	}

	public HttpRequestBuilder host(String host) {
		this.host = host;
		return this;
	}

	public HttpRequestBuilder header(String key, String value) {
		this.headers.put(key, value);
		return this;
	}

	public HttpRequestBuilder query(String key, String value) {
		this.queryParams.put(key, value);
		return this;
	}

	public HttpRequestBuilder body(String body) {
		this.body = body;
		return this;
	}
	
	public HttpRequestBuilder keepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
		return this;
	}

	public HttpRequestBuilder followRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
		return this;
	}

	public HttpRequestBuilder timeout(int ms) {
		this.timeoutMs=ms;
		return this;
	}

	public HttpRequest build() {
		// Validation 1 : host obligatoire
		if (host == null || host.trim().isEmpty()) {
			throw new IllegalArgumentException("Host est obligatoire");
		}
		
		// Validation 2 : GET sans body
		if ("GET".equals(method) && body != null) {
			throw new IllegalArgumentException("GET ne peut pas avoir de body");
		}
		
		// Validation 3 : keepAlive et followRedirects incompatibles
		if (keepAlive && followRedirects) {
			throw new IllegalArgumentException("keepAlive et followRedirects incompatibles");
		}
		
		return new HttpRequest(
			method,
			url,
			host,
			headers.isEmpty() ? null : new HashMap<>(headers),
			queryParams.isEmpty() ? null : new HashMap<>(queryParams),
			body,
			keepAlive,
			followRedirects,
			timeoutMs
		);
	}
	}
	
