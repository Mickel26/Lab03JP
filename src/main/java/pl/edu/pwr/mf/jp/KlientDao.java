package pl.edu.pwr.mf.jp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KlientDao implements Dao{
    public List<Klient> clients = new ArrayList<>();

    public KlientDao() {
        clients.add(new Klient(clients.size() + 1, ""));
    }

    @Override
    public Optional<Klient> get(int id) {
        return Optional.ofNullable(clients.get((int) id));
    }

    @Override
    public List<Klient> getAll() {
        return clients;
    }

    @Override
    public void save(Klient user) {
        clients.add(user);
    }

    @Override
    public void delete(Klient user) {
        clients.remove(user);
    }
}
