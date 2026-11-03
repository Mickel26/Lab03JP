package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Database;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.init();
        KlientDao dao = new KlientDao();

        Klient k = new Klient("Add Wee");
        dao.save(k);
        System.out.println(k);

        k.setNazwa("Abb Dwa");
        dao.update(k);
        System.out.println("po update: " + dao.get(k.getId()));

        System.out.println("wszyscy: " + dao.getAll());

        dao.delete(k.getId());
        System.out.println("po delete: " + dao.getAll());
    }
}
