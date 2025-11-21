package ar.edu.utn.prog2int.Dao;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Models.ConfiguracionRed;
import ar.edu.utn.prog2int.Models.DispositivoIoT;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DispositivoIoTDAO implements GenericDAO<DispositivoIoT> {

    
    private static final String INSERT = "INSERT INTO dispositivo_iot (eliminado, serial, modelo, ubicacion, firmware_version, id_configuracion) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE dispositivo_iot SET serial=?, modelo=?, ubicacion=?, firmware_version=?, id_configuracion=?, eliminado=? WHERE id=?";
    private static final String DELETE = "UPDATE dispositivo_iot SET eliminado = true WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT d.*, c.ip, c.mascara, c.gateway, c.dns_primario, c.dhcp_habilitado FROM dispositivo_iot d LEFT JOIN configuracion_red c ON d.id_configuracion = c.id WHERE d.id=?";
    private static final String SELECT_ALL = "SELECT d.*, c.ip, c.mascara, c.gateway, c.dns_primario, c.dhcp_habilitado FROM dispositivo_iot d LEFT JOIN configuracion_red c ON d.id_configuracion = c.id WHERE d.eliminado = false";

  
    // Método para validar entrada
    private void validar(DispositivoIoT dispositivo) {
        if (dispositivo == null) {
            throw new IllegalArgumentException("El dispositivo no puede ser nulo.");
        }
        if (dispositivo.getSerial() == null || dispositivo.getSerial().isEmpty()) {
            throw new IllegalArgumentException("El número de serie es obligatorio.");
        }
        if (dispositivo.getModelo() == null || dispositivo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("El modelo es obligatorio.");
        }
    }

    @Override
    public void insertar(DispositivoIoT dispositivo, Connection con) throws Exception {
        validar(dispositivo);

        try (PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, dispositivo.isEliminado());
            ps.setString(2, dispositivo.getSerial());
            ps.setString(3, dispositivo.getModelo());
            ps.setString(4, dispositivo.getUbicacion());
            ps.setString(5, dispositivo.getFirmwareVersion());

            if (dispositivo.getConfiguracionRed() != null && dispositivo.getConfiguracionRed().getId() != null) {
                ps.setLong(6, dispositivo.getConfiguracionRed().getId());
            } else {
                ps.setNull(6, Types.BIGINT);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    dispositivo.setId(rs.getLong(1));
                }
            }

            System.out.println("Dispositivo insertado correctamente (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al insertar DispositivoIoT (DAO): " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(DispositivoIoT dispositivo, Connection con) throws Exception {
        validar(dispositivo);

        try (PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, dispositivo.getSerial());
            ps.setString(2, dispositivo.getModelo());
            ps.setString(3, dispositivo.getUbicacion());
            ps.setString(4, dispositivo.getFirmwareVersion());

            if (dispositivo.getConfiguracionRed() != null && dispositivo.getConfiguracionRed().getId() != null) {
                ps.setLong(5, dispositivo.getConfiguracionRed().getId());
            } else {
                ps.setNull(5, Types.BIGINT);
            }

            ps.setBoolean(6, dispositivo.isEliminado());
            ps.setLong(7, dispositivo.getId());

            ps.executeUpdate();
            System.out.println("Dispositivo actualizado correctamente (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al actualizar DispositivoIoT (DAO): " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Long id, Connection con) throws Exception {
        try (PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Dispositivo marcado como eliminado (DAO).");
        } catch (SQLException e) {
            throw new Exception("Error al eliminar DispositivoIoT (DAO): " + e.getMessage(), e);
        }
    }

    @Override
    public DispositivoIoT getById(Long id) throws Exception {
        DispositivoIoT dispositivo = null;

        try (Connection con = DataBaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ConfiguracionRed conf = new ConfiguracionRed();
                    conf.setId(rs.getLong("id_configuracion"));
                    conf.setIp(rs.getString("ip"));
                    conf.setMascara(rs.getString("mascara"));
                    conf.setGateway(rs.getString("gateway"));
                    conf.setDnsPrimario(rs.getString("dns_primario"));
                    conf.setDhcpHabilitado(rs.getBoolean("dhcp_habilitado"));

                    dispositivo = new DispositivoIoT(
                            rs.getLong("id"),
                            rs.getBoolean("eliminado"),
                            rs.getString("serial"),
                            rs.getString("modelo"),
                            rs.getString("ubicacion"),
                            rs.getString("firmware_version"),
                            conf
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener DispositivoIoT por ID (DAO): " + e.getMessage(), e);
        }

        return dispositivo;
    }

    @Override
    public List<DispositivoIoT> getAll() throws Exception {
        List<DispositivoIoT> lista = new ArrayList<>();

        try (Connection con = DataBaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ConfiguracionRed conf = new ConfiguracionRed();
                conf.setId(rs.getLong("id_configuracion"));
                conf.setIp(rs.getString("ip"));
                conf.setMascara(rs.getString("mascara"));
                conf.setGateway(rs.getString("gateway"));
                conf.setDnsPrimario(rs.getString("dns_primario"));
                conf.setDhcpHabilitado(rs.getBoolean("dhcp_habilitado"));

                DispositivoIoT dispositivo = new DispositivoIoT(
                        rs.getLong("id"),
                        rs.getBoolean("eliminado"),
                        rs.getString("serial"),
                        rs.getString("modelo"),
                        rs.getString("ubicacion"),
                        rs.getString("firmware_version"),
                        conf
                );

                lista.add(dispositivo);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar DispositivosIoT (DAO): " + e.getMessage(), e);
        }

        return lista;
    }
}
