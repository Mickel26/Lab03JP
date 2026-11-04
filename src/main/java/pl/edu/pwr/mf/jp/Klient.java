package pl.edu.pwr.mf.jp;

public class Klient {
    private int id;
    private String nazwa;

    public Klient(String nazwa) {
        this.nazwa = nazwa;
    }

    public Klient(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Klient{id=" + id + ", nazwa=" + nazwa + "}";
    }
}
