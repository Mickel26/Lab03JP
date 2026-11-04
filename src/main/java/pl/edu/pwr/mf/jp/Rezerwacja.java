package pl.edu.pwr.mf.jp;

import java.time.LocalDate;

public class Rezerwacja {
    private int id;
    private int idSprzetu;
    private int idKlienta;
    private LocalDate dataPoczatkowa;
    private LocalDate dataKoncowa;
    private Status status;
    private boolean platnosc;

    public Rezerwacja(int idSprzetu, int idKlienta, LocalDate dataPoczatkowa, LocalDate dataKoncowa, Status status, boolean platnosc) {
        this.idSprzetu = idSprzetu;
        this.idKlienta = idKlienta;
        this.dataPoczatkowa = dataPoczatkowa;
        this.dataKoncowa = dataKoncowa;
        this.status = status;
        this.platnosc = platnosc;
    }

    public Rezerwacja(int id, int idSprzetu, int idKlienta, LocalDate dataPoczatkowa, LocalDate dataKoncowa, Status status, boolean platnosc) {
        this.id = id;
        this.idSprzetu = idSprzetu;
        this.idKlienta = idKlienta;
        this.dataPoczatkowa = dataPoczatkowa;
        this.dataKoncowa = dataKoncowa;
        this.status = status;
        this.platnosc = platnosc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSprzetu() {
        return idSprzetu;
    }

    public void setIdSprzetu(int idSprzetu) {
        this.idSprzetu = idSprzetu;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public LocalDate getDataPoczatkowa() {
        return dataPoczatkowa;
    }

    public void setDataPoczatkowa(LocalDate dataPoczatkowa) {
        this.dataPoczatkowa = dataPoczatkowa;
    }

    public LocalDate getDataKoncowa() {
        return dataKoncowa;
    }

    public void setDataKoncowa(LocalDate dataKoncowa) {
        this.dataKoncowa = dataKoncowa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(boolean platnosc) {
        this.platnosc = platnosc;
    }

    @Override
    public String toString() {
        return "Rezerwacja{" +
                "id=" + id +
                ", idSprzetu=" + idSprzetu +
                ", idKlienta=" + idKlienta +
                ", dataPoczatkowa=" + dataPoczatkowa +
                ", dataKoncowa=" + dataKoncowa +
                ", status=" + status +
                ", platnosc=" + platnosc +
                '}';
    }
}
