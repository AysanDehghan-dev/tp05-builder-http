package exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import exo1.with_builder.builder.HttpRequest;
import exo1.with_builder.builder.HttpRequestBuilder;

public class HttpRequestBuilderTest {
	
	// Test 1 : Construction valide simple
	@Test
	public void testConstructionValideSimple() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.build();
		
		assertNotNull(request);
		assertEquals("GET", request.getMethod());
		assertEquals("/users", request.getUrl());
		assertEquals("api.example.com", request.getHost());
	}
	
	// Test 2 : Host obligatoire - null
	@Test
	public void testHostObligatoireNull() {
		try {
			new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.build();
			assertEquals("Host obligatoire", "Erreur attendue");
		} catch (IllegalArgumentException e) {
			assertEquals("Host est obligatoire", e.getMessage());
		}
	}
	
	// Test 3 : Host obligatoire - vide
	@Test
	public void testHostObligatoireVide() {
		try {
			new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.host("")
				.build();
			assertEquals("Host vide", "Erreur attendue");
		} catch (IllegalArgumentException e) {
			assertEquals("Host est obligatoire", e.getMessage());
		}
	}
	
	// Test 4 : Body interdit sur GET
	@Test
	public void testBodyInterditSurGET() {
		try {
			new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.host("api.example.com")
				.body("du contenu")
				.build();
			assertEquals("Body interdit", "Erreur attendue");
		} catch (IllegalArgumentException e) {
			assertEquals("GET ne peut pas avoir de body", e.getMessage());
		}
	}
	
	// Test 5 : Body autorisé sur POST
	@Test
	public void testBodyAutoriseSurPOST() {
		HttpRequest request = new HttpRequestBuilder()
			.method("POST")
			.url("/login")
			.host("api.example.com")
			.body("{\"username\": \"john\"}")
			.build();
		
		assertNotNull(request);
		assertEquals("{\"username\": \"john\"}", request.getBody());
	}
	
	// Test 6 : keepAlive et followRedirects incompatibles
	@Test
	public void testKeepAliveEtFollowRedirectsIncompatibles() {
		try {
			new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.host("api.example.com")
				.keepAlive(true)
				.followRedirects(true)
				.build();
			assertEquals("Incompatibles", "Erreur attendue");
		} catch (IllegalArgumentException e) {
			assertEquals("keepAlive et followRedirects incompatibles", e.getMessage());
		}
	}
	
	// Test 7 : keepAlive seul OK
	@Test
	public void testKeepAliveSeulOK() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.keepAlive(true)
			.followRedirects(false)
			.build();
		
		assertNotNull(request);
		assertTrue(request.isKeepAlive());
		assertFalse(request.isFollowRedirects());
	}
	
	// Test 8 : followRedirects seul OK
	@Test
	public void testFollowRedirectsSeulOK() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.keepAlive(false)
			.followRedirects(true)
			.build();
		
		assertNotNull(request);
		assertFalse(request.isKeepAlive());
		assertTrue(request.isFollowRedirects());
	}
	
	// Test 9 : Headers multiples
	@Test
	public void testHeadersMultiples() {
		HttpRequest request = new HttpRequestBuilder()
			.method("POST")
			.url("/api/users")
			.host("api.example.com")
			.header("Authorization", "Bearer token")
			.header("Content-Type", "application/json")
			.body("{\"data\": \"test\"}")
			.build();
		
		assertNotNull(request);
		assertNotNull(request.getHeaders());
		assertEquals("Bearer token", request.getHeaders().get("Authorization"));
	}
	
	// Test 10 : Query params multiples
	@Test
	public void testQueryParamsMultiples() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.query("limit", "10")
			.query("offset", "0")
			.build();
		
		assertNotNull(request);
		assertNotNull(request.getQueryParams());
		assertEquals("10", request.getQueryParams().get("limit"));
	}
	
	// Test 11 : Timeout par défaut
	@Test
	public void testTimeoutParDefaut() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.build();
		
		assertEquals(5000, request.getTimeoutMs());
	}
	
	// Test 12 : Valeurs par défaut correctes
	@Test
	public void testValeursParDefaut() {
		HttpRequest request = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.build();
		
		assertTrue(request.isKeepAlive());
		assertFalse(request.isFollowRedirects());
		assertEquals(5000, request.getTimeoutMs());
	}
}