package pe.edu.ulima.idic.quinua.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Sesion implements Serializable {
    private static final long serialVersionUID = -222864131214757024L;
    @DatabaseField(generatedId = true, columnName = "id")
    public int id;
    @DatabaseField(columnName = "llave")
    public String llave;
    @DatabaseField(columnName = "valor")
    public String valor;

    public Sesion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Sesion(String llave, String valor) {
        this.llave = llave;
        this.valor = valor;
    }
}

//http://www.androidbegin.com/tutorial/android-ormlite-with-sqlite-database-tutorial/