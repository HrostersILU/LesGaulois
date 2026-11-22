package personnages;

import village_gaulois.Musee;
import village_gaulois.Village;
import objets.Equipement;

public class Gaulois {

	private static final String LE_GAULOIS = "Le gaulois ";
	private String nom;
	private int force;
	private int nbTrophees = 0;
	private Equipement[] trophees = new Equipement[100];
	private int effetPotion = 1;
	private Village village = null;

	public Gaulois(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}
	
	private String prendreParole() {
		return LE_GAULOIS + nom + " : ";
		}
	

	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de "
		+ romain.getNom());
		Equipement[] tropheesRecus = romain.recevoirCoup((force / 2) * effetPotion);
		effetPotion--;
		if (effetPotion < 1) {
			effetPotion = 1;
		}
		for (int i = 0; tropheesRecus != null && i < tropheesRecus.length; i++,
			nbTrophees++) {
			this.trophees[nbTrophees] = tropheesRecus[i];
		}
		}
	
	public void boirePotion(int forcePotion) {
        effetPotion = forcePotion;
    }
	
	public void setVillage(Village new_village) {
		village = new_village;
	}
	
	public static void main() {
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(asterix);
	}
	
	public void sePresenter() {
		String bonjour = " : \"Bonjour, je m'appelle ";
		if (village == null) {
			System.out.println(LE_GAULOIS + nom + bonjour +
		nom + " . Je voyage de villages en villages.");
		} else {
			Gaulois chefVillage = village.getChef();
			if (!chefVillage.equals(null)) {
				String nomChef = chefVillage.getNom();
				String nomVillage = village.getNom();
				if (nomChef.equals(nom)) {
					System.out.println(LE_GAULOIS + nom + bonjour +
							nom + " . Je suis le chef du village \"" + nomVillage + "\"");
				} else {
					System.out.println(LE_GAULOIS + nom + bonjour +
							nom + " . J'habite le village \"" + nomVillage + "\"");
				}
			}
		}
	}
	
	public void faireUneDonnation(Musee musee) {
		if (nbTrophees > 0) {
			String texte = "Je donne au musée mes trophées : ";
			for (int i = 0; i < nbTrophees; i++) {
				musee.donnerTrophees(this, trophees[i]);
				texte = texte + "\n	-" + trophees[i].toString();
				trophees[i] = null;
			}
			nbTrophees = 0;
			parler(texte);
		}
	}
}
