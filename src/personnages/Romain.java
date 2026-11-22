package personnages;

import objets.Equipement;

public class Romain {

	private static final String LE_SOLDAT = "Le soldat ";
	private String nom;
	private int force;
	private Equipement[] equipements = new Equipement[2];
	private int nbEquipement = 0;
	
	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		assert isInvariantVerrified();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}
	
	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}
	
//	public void recevoirCoup(int forceCoup) {
//		assert forceCoup > 0;
//		int forceAuDepart = force;
//		force = force - forceCoup;
//		if (force > 0) {
//            parler("Aïe");
//        } else {
//            parler("J'abandonne !");
//        }
//		assert isInvariantVerrified();
//		assert forceAuDepart > force;
//	}
	
	private int calculResistanceForce(int forceCoup) {
		String texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipement != 0) {
			for (int i = 0; i < nbEquipement; i++) {
				if ((equipements[i] != null &&
						equipements[i].equals(Equipement.BOUCLIER))) {
					resistanceEquipement += 6;
				} else {
					resistanceEquipement += 3;
				}
		}
		}
		forceCoup -= resistanceEquipement;
		if (forceCoup < 0) {
			forceCoup = 0;
		}
		if (forceCoup > 0) {
			texte += "\nMais heureusement, grace à mon équipement sa force est diminué de ";
			texte += resistanceEquipement + "!";
		} else {
			texte += "\nMais heureusement, grace à mon équipement sa force a été complètement absorbée.";
		}
		parler(texte);
		return forceCoup;
		}
	
	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		forceCoup = calculResistanceForce(forceCoup);
		force -= forceCoup;
		if (force <= 0) {
			parler("Aïe");
			equipementEjecte = ejecterEquipement();
			parler("J'abandonne...");
		} else {
			parler("Aïe");
		}
		return equipementEjecte;
	}
	
	private Equipement[] ejecterEquipement() {
		Equipement[] equipementEjecte = new Equipement[nbEquipement];
		System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipement; i++) {
			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		return equipementEjecte;
	}
	
	private boolean isInvariantVerrified() {
		return force > 0;
	}
	
	public void sEquiper(Equipement equipement) {
		switch (nbEquipement) {
		case 0: {
			ajoutEquipement(equipement);
			break;
		}
		case 1: {
			if (equipements[0].equals(equipement)) {
				System.out.println(LE_SOLDAT + nom + " pocède déjà un " + equipement.toString());
				break;
			} else {
				ajoutEquipement(equipement);
				break;
			}
		}
		default:
			System.out.println(LE_SOLDAT + nom + " est déjà bien protégé!");
			break;
		}
	}

	private void ajoutEquipement(Equipement equipement) {
		equipements[nbEquipement] = equipement;
		nbEquipement++;
		System.out.println(LE_SOLDAT + nom + " s'equipe avec un " + equipement.toString());
	}
	
	public static void main() {
		Romain minus = new Romain("Minus", 6);
		System.out.println(Equipement.CASQUE);
		System.out.println(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.CASQUE);
	}

	public int getForce() {
		return force;
	}
}
