package exo2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class TestArrayListEtudiantBuilder {

	// Test 1 : Fichier avec 3 étudiants
	@Test
	public void testFichierAvec3Etudiants() throws IOException {
		String data = "1:Turing:Alan:10:12:13:7:15\n" +
		              "2:Lovelace:Ada:18:15:19\n" +
		              "3:Wirth:Niklaus:12:10:13:13\n";

		ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
		LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
		List<Etudiant> etudiants = lecteur.lireFichier(new StringReader(data));

		assertEquals(3, etudiants.size());
	}

	// Test 2 : Étudiant sans notes
	@Test
	public void testEtudiantSansNotes() throws IOException {
		String data = "1:Dupont:Jean";

		ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
		LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
		List<Etudiant> etudiants = lecteur.lireFichier(new StringReader(data));

		assertEquals(1, etudiants.size());
		assertEquals(0, etudiants.get(0).getNotes().size());
	}

	// Test 3 : Étudiant avec espaces dans le nom
	@Test
	public void testEspacesDansNom() throws IOException {
		String data = "1:Dupont Martin:Jean Pierre:15:16";

		ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
		LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
		List<Etudiant> etudiants = lecteur.lireFichier(new StringReader(data));

		assertEquals(1, etudiants.size());
		assertEquals("Dupont Martin", etudiants.get(0).getNom());
	}

	// Test 4 : Étudiant avec beaucoup de notes
	@Test
	public void testBeaucoupDeNotes() throws IOException {
		String data = "1:Dupont:Jean:15:16:14:17:18:16";

		ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
		LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
		List<Etudiant> etudiants = lecteur.lireFichier(new StringReader(data));

		assertEquals(1, etudiants.size());
		assertEquals(6, etudiants.get(0).getNotes().size());
	}
}