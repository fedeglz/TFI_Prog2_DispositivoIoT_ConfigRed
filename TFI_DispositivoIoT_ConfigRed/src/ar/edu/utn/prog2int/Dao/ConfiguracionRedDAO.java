package ar.edu.utn.prog2int.Dao;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class ConfiguracionRedDAO implements GenericDAO<ConfiguracionRed> {

    @Override
    public void insertar(ConfiguracionRed c, Connection con) throws Exception {
        String sql = "INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado, eliminado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getIp());
            ps.setString(2, c.getMascara());
            ps.setString(3, c.getGateway());
            ps.setString(4, c.getDnsPrimario());
            ps.setBoolean(5, c.isDhcpHabilitado());
            ps.setBoolean(6, c.isEliminado());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error en la inserción (DAO): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(ConfiguracionRed c, Connection con) throws Exception {
        String sql = "UPDATE configuracion_red SET "
                + "ip = ?, mascara = ?, gateway = ?, dns_primario = ?, dhcp_habilitado = ?, eliminado = ? "
                + "WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getIp());
            ps.setString(2, c.getMascara());
            ps.setString(3, c.getGateway());
            ps.setString(4, c.getDnsPrimario());
            ps.setBoolean(5, c.isDhcpHabilitado());
            ps.setBoolean(6, c.isEliminado());
            ps.setLong(7, c.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error en la actualización (DAO): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id, Connection con) throws Exception {
        String sql = "UPDATE configuracion_red SET eliminado = true WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Configuración marcada como eliminada (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al eliminar configuración (DAO): " + e.getMessage());
        }
    }

    @Override
    public ConfiguracionRed getById(Long id) throws Exception {
        String sql = "SELECT * FROM configuracion_red WHERE id = ?";
        ConfiguracionRed c = null;

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new ConfiguracionRed(
                        rs.getLong("id"),
                        rs.getBoolean("eliminado"),
                        rs.getString("ip"),
                        rs.getString("mascara"),
                        rs.getString("gateway"),
                        rs.getString("dns_primario"),
                        rs.getBoolean("dhcp_habilitado")
                );
            }

        } catch (SQLException e) {
            throw new Exception("Error al leer configuración: " + e.getMessage());
        }

        return c;
    }

    @Override
    public List<ConfiguracionRed> getAll() throws Exception {
        List<ConfiguracionRed> lista = new ArrayList<>();
        String sql = "SELECT * FROM configuracion_red WHERE eliminado = false";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ConfiguracionRed(
                        rs.getLong("id"),
                        rs.getBoolean("eliminado"),
                        rs.getString("ip"),
                        rs.getString("mascara"),
                        rs.getString("gateway"),
                        rs.getString("dns_primario"),
                        rs.getBoolean("dhcp_habilitado")
                ));
            }

        } catch (SQLException e) {
            throw new Exception("Error al listar configuraciones: " + e.getMessage());
        }

        return lista;
    }

}
