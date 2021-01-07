package Caisse;

public class JtableModel {
	//produit
	private	int code_p;
	private String nom_p;
	private double prix_p;
	private int quantite_p;
	public JtableModel(int code,String nom,double prix,int quantite) {
		code_p=code;
		nom_p=nom;
		prix_p=prix;
		quantite_p=quantite;
	}
	public int getCode_p() {
		return code_p;
	}
	public void setCode_p(int code_p) {
		this.code_p = code_p;
	}
	public String getNom_p() {
		return nom_p;
	}
	public void setNom_p(String nom_p) {
		this.nom_p = nom_p;
	}
	public double getPrix_p() {
		return prix_p;
	}
	public void setPrix_p(double prix_p) {
		this.prix_p = prix_p;
	}
	public int getQuantite_p() {
		return quantite_p;
	}
	public void setQuantite_p(int quantite_p) {
		this.quantite_p = quantite_p;
	}
	// client
	private int code_c;
	private String nom_c;
	private String prenom_c;
	private int tele_c;
	private String adresse_c;
	private int note;
	public int getCode_c() {
		return code_c;
	}
	public JtableModel(int code_c, String nom_c, String prenom_c, int tele_c, String adresse_c, int node) {
		super();
		this.code_c = code_c;
		this.nom_c = nom_c;
		this.prenom_c = prenom_c;
		this.tele_c = tele_c;
		this.adresse_c = adresse_c;
		this.note = note;
	}
	public void setCode_c(int code_c) {
		this.code_c = code_c;
	}
	public String getNom_c() {
		return nom_c;
	}
	public void setNom_c(String nom_c) {
		this.nom_c = nom_c;
	}
	public String getPrenom_c() {
		return prenom_c;
	}
	public void setPrenom_c(String prenom_c) {
		this.prenom_c = prenom_c;
	}
	public int getTele_c() {
		return tele_c;
	}
	public void setTele_c(int tele_c) {
		this.tele_c = tele_c;
	}
	public String getAdresse_c() {
		return adresse_c;
	}
	public void setAdresse_c(String adresse_c) {
		this.adresse_c = adresse_c;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	//commande
	private int code_cmd;
	private String nom_cmd;
	private int qt_p_cmd;
	private double prix_cmd;
	public JtableModel( String nom_cmd,int code_cmd, int qt_p_cmd, double prix_cmd) {
		super();
		this.code_cmd = code_cmd;
		this.nom_cmd = nom_cmd;
		this.qt_p_cmd = qt_p_cmd;
		this.prix_cmd = prix_cmd;
	}
	public int getCode_cmd() {
		return code_cmd;
	}
	public void setCode_cmd(int code_cmd) {
		this.code_cmd = code_cmd;
	}
	public String getNom_cmd() {
		return nom_cmd;
	}
	public void setNom_cmd(String nom_cmd) {
		this.nom_cmd = nom_cmd;
	}
	public int getQt_p_cmd() {
		return qt_p_cmd;
	}
	public void setQt_p_cmd(int qt_p_cmd) {
		this.qt_p_cmd = qt_p_cmd;
	}
	public double getPrix_cmd() {
		return prix_cmd;
	}
	public void setPrix_cmd(double prix_cmd) {
		this.prix_cmd = prix_cmd;
	}
	

}
