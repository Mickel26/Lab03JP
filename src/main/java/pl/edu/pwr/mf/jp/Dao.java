package pl.edu.pwr.mf.jp;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);

    List<T> getAll();

    //void update(T t, String[] params);

    void delete(Klient user);

    void save(Klient user);
}