package exo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListEtudiantBuilder implements ListEtudiantBuilder {
	
	List<Etudiant> etudiants = new ArrayList<>();
	
	@Override
	public ListEtudiantBuilder reset() {
		etudiants.clear();
		return this;
	}
	
	@Override
	public ListEtudiantBuilder traiterLigne(String l) {
		// Format : numéro:nom:prénom:note1:note2:note3:...
		
		if (l == null || l.trim().isEmpty()) {
			return this;
		}
		
		String[] parts = l.split(":");
		
		if (parts.length < 3) {
			return this;
		}
		
		try {
			int numero = Integer.parseInt(parts[0].trim());
			String nom = parts[1].trim();
			String prenom = parts[2].trim();
			
			Etudiant etudiant = new Etudiant(numero, nom, prenom);
			
			// Traiter les notes (à partir de l'index 3)
			for (int i = 3; i < parts.length; i++) {
				try {
					double note = Double.parseDouble(parts[i].trim());
					etudiant.addNote(note);
				} catch (NumberFormatException e) {
					// Ignorer les notes invalides
				}
			}
			
			etudiants.add(etudiant);
		} catch (NumberFormatException e) {
			// Ignorer les lignes avec numéro invalide
		}
		
		return this;
	}
	
	@Override
	public List<String> getNomsEtudiants() {
		return etudiants.stream()
			.map(e -> e.getNom())
			.collect(Collectors.toList());
	}
	
	@Override
	public List<Etudiant> getResults() {
		return new ArrayList<>(etudiants);
	}

}