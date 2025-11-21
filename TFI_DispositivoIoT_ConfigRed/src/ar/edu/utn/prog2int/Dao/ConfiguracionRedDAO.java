package ar.edu.utn.prog2int.Dao;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionRedDAO implements GenericDAO<ConfiguracionRed> {

    
    private static final String INSERT = "INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado, eliminado) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE configuracion_red SET ip=?, mascara=?, gateway=?, dns_primario=?, dhcp_habilitado=?, eliminado=? WHERE id=?";
    private static final String DELETE = "UPDATE configuracion_red SET eliminado = true WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM configuracion_red WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM configuracion_red WHERE eliminado = false";

    // --- Método de validación ---
    private void validar(ConfiguracionRed c) throws Exception {
        if (c == null) {
            throw new Exception("La configuración no puede ser nula");
        }
        if (c.getIp() == null || c.getIp().isEmpty()) {
            throw new Exception("El campo IP es obligatorio");
        }
        if (c.getMascara() == null || c.getMascara().isEmpty()) {
            throw new Exception("La máscara de red es obligatoria");
        }
        if (c.getGateway() == null || c.getGateway().isEmpty()) {
            throw new Exception("El gateway es obligatorio");
        }
        if (c.getDnsPrimario() == null || c.getDnsPrimario().isEmpty()) {
            throw new Exception("El DNS primario es obligatorio");
        }
    }

    @Override
    public void insertar(ConfiguracionRed c, Connection con) throws Exception {
        validar(c);
        try (PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, c.getIp());
            ps.setString(2, c.getMascara());
            ps.setString(3, c.getGateway());
            ps.setString(4, c.getDnsPrimario());
            ps.setBoolean(5, c.isDhcpHabilitado());
            ps.setBoolean(6, c.isEliminado());
            ps.executeUpdate();
            System.out.println("Configuración insertada correctamente (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al insertar configuración (DAO): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(ConfiguracionRed c, Connection con) throws Exception {
        validar(c);
        try (PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, c.getIp());
            ps.setString(2, c.getMascara());
            ps.setString(3, c.getGateway());
            ps.setString(4, c.getDnsPrimario());
            ps.setBoolean(5, c.isDhcpHabilitado());
            ps.setBoolean(6, c.isEliminado());
            ps.setLong(7, c.getId());
            ps.executeUpdate();
            System.out.println("Configuración actualizada correctamente (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al actualizar configuración (DAO): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id, Connection con) throws Exception {
        try (PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Configuración marcada como eliminada (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al eliminar configuración (DAO): " + e.getMessage());
        }
    }

    @Override
    public ConfiguracionRed getById(Long id) throws Exception {
        ConfiguracionRed c = null;
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer configuración (DAO): " + e.getMessage());
        }
        return c;
    }

    @Override
    public List<ConfiguracionRed> getAll() throws Exception {
        List<ConfiguracionRed> lista = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL); ResultSet rs = ps.executeQuery()) {

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
            throw new Exception("Error al listar configuraciones (DAO): " + e.getMessage());
        }
        return lista;
    }
}
