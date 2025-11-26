package exo1.with_builder;
import exo1.with_builder.builder.HttpRequest;
import exo1.with_builder.builder.HttpRequestBuilder;
public class Main2 {

	public static void main(String[] args) {
		// Requête 1 : GET /users?limit=10
		HttpRequest req1 = new HttpRequestBuilder()
			.method("GET")
			.url("/users")
			.host("api.example.com")
			.query("limit", "10")
			.timeout(5000)
			.build();
		System.out.println("Requête 1 : GET /users?limit=10");
		System.out.println(req1);
		System.out.println(";");
		
		HttpRequest req2 = new HttpRequestBuilder()
			.method("POST")
			.url("/login")
			.host("api.example.com")
			.header("Content-Type", "application/json")
			.body("{\"username\": \"john\", \"password\": \"secret123\"}")
			.timeout(5000)
			.build();
		System.out.println("Requête 2 : POST /login");
		System.out.println(req2);
		System.out.println();
		
		//Requête plus complexe
		HttpRequest req3 = new HttpRequestBuilder()
		.method("POST")
		.url("/api/users")
		.host("api.example.com")
		.header("Authorization", "Bearer token123")
		.header("Content-Type", "application/json")
		.query("verbose", "true")
		.query("format", "json")
		.body("{\"name\": \"Alice\", \"email\": \"alice@example.com\"}")
		.keepAlive(true)
		.followRedirects(false)
		.timeout(10000)
		.build();	
		System.out.println("Requête 3 : POST complexe");
		System.out.println(req3);
				
			
}

}
