package ch.globaz.searchenginetest;

import java.util.UUID;

public class Personne {

	private String nom;
	private String prenom;
	private String rue;
	private String localite;
	private String telFixe;
	private String telMobile;
	private String id;




	public static Personne from(String nom, String prenom, String rue, String localite, String telFixe, String
			telMobile) {
		Personne p = new Personne();
		p.nom = nom;
		p.prenom = prenom;
		p.rue = rue;
		p.localite = localite;
		p.telFixe = telFixe;
		p.telMobile = telMobile;
		p.id = UUID.randomUUID().toString();
		return p;
	}

	public String id() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Personne{" +
				"nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", rue='" + rue + '\'' +
				", localite='" + localite + '\'' +
				", telFixe='" + telFixe + '\'' +
				", telMobile='" + telMobile + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	public String nom() {
		return this.nom;
	}

	public String prenom() {
		return this.prenom;
	}
}
