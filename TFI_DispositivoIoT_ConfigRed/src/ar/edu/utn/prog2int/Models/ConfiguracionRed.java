
package ar.edu.utn.prog2int.Models;


public class ConfiguracionRed extends Base {
    
    private String ip;
    private String mascara;
    private String gateway;
    private String dnsPrimario;
    private boolean dhcpHabilitado;
    
    

    public ConfiguracionRed() {
        super();
    }

    public ConfiguracionRed(String ip, String mascara, String gateway, String dnsPrimario, boolean dhcpHabilitado) {
        this.ip = ip;
        this.mascara = mascara;
        this.gateway = gateway;
        this.dnsPrimario = dnsPrimario;
        this.dhcpHabilitado = dhcpHabilitado;
    }

    public ConfiguracionRed(Long id, boolean eliminado ,String ip, String mascara, String gateway, String dnsPrimario, boolean dhcpHabilitado) {
        super(id, eliminado);
        this.ip = ip;
        this.mascara = mascara;
        this.gateway = gateway;
        this.dnsPrimario = dnsPrimario;
        this.dhcpHabilitado = dhcpHabilitado;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDnsPrimario() {
        return dnsPrimario;
    }

    public void setDnsPrimario(String dnsPrimario) {
        this.dnsPrimario = dnsPrimario;
    }

    public boolean isDhcpHabilitado() {
        return dhcpHabilitado;
    }

    public void setDhcpHabilitado(boolean dhcpHabilitado) {
        this.dhcpHabilitado = dhcpHabilitado;
    }

    @Override
    public String toString() {
        return "ConfiguracionRed{" + 
                "id=" + getId() +
                ", eliminado=" + isEliminado() +
                "ip=" + ip + 
                ", mascara=" + mascara + 
                ", gateway=" + gateway + 
                ", dnsPrimario=" + dnsPrimario + 
                ", dhcpHabilitado=" + dhcpHabilitado + '}';
    }

      
    
}
