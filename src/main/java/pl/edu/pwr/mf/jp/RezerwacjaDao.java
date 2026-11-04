package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RezerwacjaDao implements Dao<Rezerwacja>{

    private Rezerwacja map(ResultSet rs) throws SQLException {
        return new Rezerwacja(
                rs.getInt("id"),
                rs.getInt("id_sprzetu"),
                rs.getInt("id_klienta"),
                LocalDate.parse(rs.getString("data_poczatkowa")),
                LocalDate.parse(rs.getString("data_koncowa")),
                Status.valueOf(rs.getString("status")),
                rs.getInt("platnosc") == 1
        );
    }

    @Override
    public Optional<Rezerwacja> get(int id) throws DaoException {
        String sql = "SELECT * FROM rezerwacja WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }

                return Optional.empty();
            }
        } catch(SQLException e) {
            throw new DaoException("Nie udalo sie pobrac rezerwacji o id " + id);
        }
    }

    @Override
    public List<Rezerwacja> getAll() throws DaoException {
        String sql = "SELECT * FROM rezerwacja";
        List<Rezerwacja> wynik = new ArrayList<>();

        try (Connection conn = Database.connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                wynik.add(map(rs));
            }

            return wynik;
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie pobrac listy rezerwacji", e);
        }
    }

    @Override
    public void update(Rezerwacja rezerwacja) throws DaoException {
        String sql = "UPDATE rezerwacja SET id_sprzetu = ?, id_klienta = ?, data_poczatkowa = ?, data_koncowa = ?, status = ?, platnosc = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rezerwacja.getIdSprzetu());
            ps.setInt(2, rezerwacja.getIdKlienta());
            ps.setString(3, rezerwacja.getDataPoczatkowa().toString());
            ps.setString(4, rezerwacja.getDataKoncowa().toString());
            ps.setString(5, rezerwacja.getStatus().name());
            ps.setInt(6, rezerwacja.isPlatnosc() ? 1 : 0);
            ps.setInt(7, rezerwacja.getId());

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma rezerwacji o id " + rezerwacja.getId());
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zaktualizowac rezerwacji", e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM rezerwacja WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            if(ps.executeUpdate() == 0) {
                throw new DaoException("Nie ma rezerwacji o id " + id);
            }
        } catch(SQLException e) {
            throw new DaoException("Nie udalo sie usunac rezerwacji", e);
        }
    }

    @Override
    public void save(Rezerwacja rezerwacja) throws DaoException {
        String sql = "INSERT INTO rezerwacja(id_sprzetu, id_klienta, data_poczatkowa, data_koncowa, status, platnosc) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rezerwacja.getIdSprzetu());
            ps.setInt(2, rezerwacja.getIdKlienta());
            ps.setString(3, rezerwacja.getDataPoczatkowa().toString());
            ps.setString(4, rezerwacja.getDataKoncowa().toString());
            ps.setString(5, rezerwacja.getStatus().name());
            ps.setInt(6, rezerwacja.isPlatnosc() ? 1 : 0);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    rezerwacja.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Nie udalo sie zapisac rezerwacji", e);
        }
    }
}
