package village_gaulois;

import objets.Equipement;
import objets.Trophee;
import personnages.Gaulois;

public class Musee {
	private Trophee[] trophees = new Trophee[200];
	private int nbTrophees = 0;
	
	public void donnerTrophees (Gaulois gaulois, Equipement equipement) {
		Trophee nouvTrophee = new Trophee(gaulois, equipement);
		trophees[nbTrophees] = nouvTrophee;
		nbTrophees++;		
	}
	
	public String extraireInstructionsOcaml() {
	    StringBuilder texte = new StringBuilder("let musee = [\n");
	    for (int i = 0; i < nbTrophees; i++) {
	        texte.append("\t\"");
	        texte.append(trophees[i].donnerNom());
	        texte.append("\", \"");
	        texte.append(trophees[i].getEquipement().toString());
	        texte.append("\"");
	        if (i < nbTrophees - 1) {
	            texte.append(";\n");
	        }
	    }
	    texte.append("\n]");
	    return texte.toString();
	}
}
