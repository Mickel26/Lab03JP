package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Database;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.init();
        KlientDao dao = new KlientDao();
        SprzetDao daoS = new SprzetDao();
        RezerwacjaDao daoR = new RezerwacjaDao();

        Klient k = new Klient("TEST");
        dao.save(k);

        Sprzet s = new Sprzet("Sprzet testowyyy", 40.0);
        daoS.save(s);

        Rezerwacja r = new Rezerwacja(s.getId(), k.getId(), LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 12), Status.ZALOZONA, false);
        daoR.save(r);
        System.out.println("zapisane: " + r);

        r.setStatus(Status.REALIZOWANA);
        r.setPlatnosc(true);
        daoR.update(r);
        System.out.println("po update: " + daoR.get(r.getId()));

        try {
            dao.delete(k.getId());
        } catch (DaoException e) {
            System.out.println("blokada: " + e.getMessage());
        }

        daoR.delete(r.getId());
        daoS.delete(s.getId());
        dao.delete(k.getId());
        System.out.println("po usuwaniu: " + daoR.getAll());
    }
}
