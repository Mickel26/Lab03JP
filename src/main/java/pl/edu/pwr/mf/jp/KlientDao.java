package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KlientDao implements Dao<Klient>{
    @Override
    public Optional<Klient> get(int id) throws DaoException {
        String sql = "SELECT id, nazwa FROM klient WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Klient(rs.getInt("id"), rs.getString("nazwa")));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie pobrac klienta o id " + id, e);
        }
    }

    @Override
    public List<Klient> getAll() throws DaoException {
        String sql = "SELECT id, nazwa FROM klient";

        List<Klient> wynik = new ArrayList<>();

        try (Connection conn = Database.connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                wynik.add(new Klient(rs.getInt("id"), rs.getString("nazwa")));
            }

            return wynik;
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie pobrac listy klientow", e);
        }
    }

    @Override
    public void save(Klient klient) throws DaoException {
        String sql = "INSERT INTO klient(nazwa) VALUES (?)";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, klient.getNazwa());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    klient.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zapisac klienta", e);
        }
    }

    @Override
    public void update(Klient klient) throws DaoException {
        String sql = "UPDATE klient SET nazwa = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, klient.getNazwa());
            ps.setInt(2, klient.getId());

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma klienta o id " + klient.getId());
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zaktualizowac klienta", e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM klient WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma klienta o id " + id);
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie usunac klienta", e);
        }
    }
}
