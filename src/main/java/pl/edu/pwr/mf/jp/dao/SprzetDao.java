package pl.edu.pwr.mf.jp.dao;

import pl.edu.pwr.mf.jp.Dao;
import pl.edu.pwr.mf.jp.exception.DaoException;
import pl.edu.pwr.mf.jp.data.Database;
import pl.edu.pwr.mf.jp.model.Sprzet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SprzetDao implements Dao<Sprzet> {
    @Override
    public Optional<Sprzet> get(int id) throws DaoException {
        String sql = "SELECT id, nazwa, cena FROM sprzet WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Sprzet(rs.getInt("id"), rs.getString("nazwa"), rs.getDouble("cena")));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie pobrac sprzetu o id " + id, e);
        }
    }

    @Override
    public List<Sprzet> getAll() throws DaoException {
        String sql = "SELECT id, nazwa, cena FROM sprzet";

        List<Sprzet> wynik = new ArrayList<>();

        try (Connection conn = Database.connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                wynik.add(new Sprzet(rs.getInt("id"), rs.getString("nazwa"), rs.getDouble("cena")));
            }

            return wynik;
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie pobrac listy sprzetu", e);
        }
    }

    @Override
    public void save(Sprzet sprzet) throws DaoException {
        String sql = "INSERT INTO SPRZET(nazwa, cena) VALUES (?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, sprzet.getNazwa());
            ps.setDouble(2, sprzet.getCena());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    sprzet.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zapisac sprzetu", e);
        }
    }

    @Override
    public void update(Sprzet sprzet) throws DaoException {
        String sql = "UPDATE sprzet SET nazwa = ?, cena = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sprzet.getNazwa());
            ps.setDouble(2, sprzet.getCena());
            ps.setInt(3, sprzet.getId());

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma sprzetu o id " + sprzet.getId());
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zaktualizowac sprzetu", e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM sprzet WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma sprzetu o id " + id);
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie usunac sprzetu", e);
        }
    }
}
