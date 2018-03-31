package josevillarreal.printedfirm.Modelos;

/**
 * Created by Umi on 31/03/2018.
 */

public class Producto
{
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private String imagenProducto;

    public Producto(int idProducto, String nombreProducto, String descripcionProducto, double precioProducto, String imagenProducto)
    {
        this.idProducto=idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.descripcionProducto = descripcionProducto;
        this.imagenProducto = imagenProducto;
    }







}
