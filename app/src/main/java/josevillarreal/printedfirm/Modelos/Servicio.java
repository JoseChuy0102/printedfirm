package josevillarreal.printedfirm.Modelos;

/**
 * Created by Umi on 05/04/2018.
 */

public class Servicio
{
    private int idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private String imagenServicio;

    public Servicio(int idServicio, String nombreServicio, String descripcionServicio, String imagenServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.imagenServicio = imagenServicio;
    }


    public int getIdServicio() {
        return idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public String getImagenServicio() {
        return imagenServicio;
    }






}
