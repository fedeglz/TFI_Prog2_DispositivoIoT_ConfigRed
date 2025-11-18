package ar.edu.utn.prog2int.Models;

public class DispositivoIoT extends Base {

    private String serial;
    private String modelo;
    private String ubicacion;
    private String firmwareVersion;
    private ConfiguracionRed configuracionRed; //Asociacion 1:1

    public DispositivoIoT() {
        super();
    }

    public DispositivoIoT(String serial, String modelo, String ubicacion, String firmwareVersion, ConfiguracionRed configuracionRed) {
        this.serial = serial;
        this.modelo = modelo;
        this.ubicacion = ubicacion;
        this.firmwareVersion = firmwareVersion;
        this.configuracionRed = configuracionRed;
    }

    public DispositivoIoT(Long id, boolean eliminado, String serial, String modelo, String ubicacion, String firmwareVersion, ConfiguracionRed configuracionRed) {
        super(id, eliminado);
        this.serial = serial;
        this.modelo = modelo;
        this.ubicacion = ubicacion;
        this.firmwareVersion = firmwareVersion;
        this.configuracionRed = configuracionRed;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public ConfiguracionRed getConfiguracionRed() {
        return configuracionRed;
    }

    public void setConfiguracionRed(ConfiguracionRed configuracionRed) {
        this.configuracionRed = configuracionRed;
    }

    @Override
    public String toString() {
        return "DispositivoIoT{"
                + "id=" + getId()
                + ", eliminado=" + isEliminado()
                + ", serial=" + serial
                + ", modelo=" + modelo
                + ", ubicacion=" + ubicacion
                + ", firmwareVersion=" + firmwareVersion
                + ", configuracionRed=" + (configuracionRed != null ? configuracionRed.getIp() : "null") + '}';
    }

}
