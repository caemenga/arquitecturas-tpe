package org.app.administrador.Entities;

public class Parada extends Entity{
    private long id;
    private long altitud;
    private long longitud;

    public Parada(long id, long altitud, long longitud) {
        this.id = id;
        this.altitud = altitud;
        this.longitud = longitud;
    }

    public long getId() {
        return id;
    }

    public long getAltitud() {
        return altitud;
    }

    public long getLongitud() {
        return longitud;
    }
}
