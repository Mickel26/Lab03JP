package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Database;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.init();
        KlientDao dao = new KlientDao();
        SprzetDao daoS = new SprzetDao();

        Klient k = new Klient("Add Wee");
        Sprzet s = new Sprzet("Test", 2.0);
        dao.save(k);
        daoS.save(s);
        System.out.println(k);
        System.out.println(s);

        s.setCena(3.5);
        daoS.update(s);
        System.out.println("po update: " + dao.get(k.getId()));
        System.out.println("po update: " + daoS.get(s.getId()));

        System.out.println("wszyscy: " + dao.getAll());
        System.out.println("wszyscy: " + daoS.getAll());

        dao.delete(k.getId());
        daoS.delete(s.getId());
        System.out.println("po delete: " + dao.getAll());
        System.out.println("po delete: " + daoS.getAll());
    }
}
