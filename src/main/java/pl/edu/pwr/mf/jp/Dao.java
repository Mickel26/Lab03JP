package pl.edu.pwr.mf.jp;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void update(T t) throws DaoException;

    void delete(int id) throws DaoException;

    void save(T t) throws DaoException;
}