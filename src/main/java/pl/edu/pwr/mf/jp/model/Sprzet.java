package pl.edu.pwr.mf.jp.model;

public class Sprzet {
    private int id;
    private String nazwa;
    private Double cena;

    public Sprzet(String nazwa, Double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public Sprzet(int id, String nazwa, Double cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Sprzet{id = " + id + ", nazwa = " + nazwa + ", cena = " + cena + "}";
    }
}
