package exo1.without_builder;

import java.util.HashMap;
import java.util.Map;

public class Main1 {

	public static void main(String[] args) {
		
		// Requête 1 : GET /users?limit=10
		Map<String, String> queryParams1 = new HashMap<>();
		queryParams1.put("limit", "10");
		
		HttpRequest request1 = new HttpRequest(
			"GET",                    // method
			"/users",                 // url
			"api.example.com",        // host
			null,                     // headers
			queryParams1,             // queryParams
			null,                     // body
			true,                     // keepAlive
			false,                    // followRedirects
			5000                      // timeoutMs
		);
		
		System.out.println("=== Requête 1 : GET /users?limit=10 ===");
		System.out.println(request1);
		System.out.println();
		
		// Requête 2 : POST /login avec body JSON
		Map<String, String> headers2 = new HashMap<>();
		headers2.put("Content-Type", "application/json");
		
		String jsonBody = "{\"username\": \"john\", \"password\": \"secret123\"}";
		
		HttpRequest request2 = new HttpRequest(
			"POST",                   // method
			"/login",                 // url
			"api.example.com",        // host
			headers2,                 // headers
			null,                     // queryParams
			jsonBody,                 // body
			true,                     // keepAlive
			false,                    // followRedirects
			5000                      // timeoutMs
		);
		
		System.out.println("=== Requête 2 : POST /login ===");
		System.out.println(request2);
		
		/*
		 * COMMENTAIRES SUR LA LISIBILITÉ ET LES ERREURS POSSIBLES :
		 * 
		 * Cette approche est très peu lisible. Avec 9 paramètres positionnels, c'est difficile
		 * de savoir ce que chaque valeur représente. On doit constamment vérifier la signature
		 * du constructeur pour comprendre l'ordre des paramètres.
		 * 
		 * Les erreurs sont faciles à faire :
		 * - On peut inverser accidentellement deux paramètres (url et host par exemple)
		 * - Les booléens sont dangereux : facile de confondre keepAlive et followRedirects
		 * - Les null rendent le code moins clair
		 * 
		 * De plus, si on veut ajouter un nouveau paramètre, ça casse la signature du
		 * constructeur et force à modifier tous les appels existants.
		 * 
		 * C'est pour ça que le Builder Pattern est plus intéressant : il rend la construction
		 * plus fluide et lisible.
		 */
	}

}