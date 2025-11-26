package exo1.without_builder;

import java.util.HashMap;
import java.util.Map;

public class Main1 {

	public static void main(String[] args) {
		
		// Requête 1 : GET /users?limit=10
		Map<String, String> queryParams1 = new HashMap<>();
		queryParams1.put("limit", "10");
		
		HttpRequest request1 = new HttpRequest(
			"GET", "/users", "api.example.com", null, queryParams1, null, true, false, 5000
		);
		
		validerRequete(request1);
		System.out.println("Requête 1 : GET /users?limit=10");
		System.out.println(request1);
		System.out.println();
		
		// Requête 2 : POST /login avec body JSON
		Map<String, String> headers2 = new HashMap<>();
		headers2.put("Content-Type", "application/json");
		String jsonBody = "{\"username\": \"john\", \"password\": \"secret123\"}";
		
		HttpRequest request2 = new HttpRequest(
			"POST", "/login", "api.example.com", headers2, null, jsonBody, true, false, 5000
		);
		
		validerRequete(request2);
		System.out.println("Requête 2 : POST /login");
		System.out.println(request2);
	}
	
	private static void validerRequete(HttpRequest request) {
		// Règle 1 : GET sans body
		if ("GET".equals(request.getMethod()) && request.getBody() != null) {
			throw new IllegalArgumentException("GET ne peut pas avoir de body");
		}
		
		// Règle 2 : host obligatoire
		if (request.getHost() == null || request.getHost().trim().isEmpty()) {
			throw new IllegalArgumentException("Host est obligatoire");
		}
		
		// Règle 3 : keepAlive et followRedirects incompatibles
		if (request.isKeepAlive() && request.isFollowRedirects()) {
			throw new IllegalArgumentException("keepAlive et followRedirects incompatibles");
		}
		
		System.out.println("Valide");
	}

}