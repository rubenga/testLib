package allClasses;

import allClasses.Livre;

public class Evaluation {
    private int idEvaluation;
    private String ISBN;
    private int idClient;
    private Livre livre;
    private String review;
    private int note;

    public Evaluation() {
    }

    public Evaluation(String ISBN, int idClient) {
        this.ISBN = ISBN;
        this.idClient = idClient;
    }

    public Evaluation(int idEvaluation, String ISBN, int idClient, Livre livre, String review, int note) {
        this.idEvaluation = idEvaluation;
        this.ISBN = ISBN;
        this.idClient = idClient;
        this.livre = livre;
        this.review = review;
        this.note = note;
    }

    public Evaluation(String ISBN, int idClient, Livre livre, String review, int note) {
        this.ISBN = ISBN;
        this.idClient = idClient;
        this.livre = livre;
        this.review = review;
        this.note = note;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return livre + " (note : " + note+")"; //+ review + 
    }

    
    
}
