package org.app.monopatin.entities.DTO;

public class MonopatinDTO {
    private Long id;
    private boolean enMantenimiento;

    public MonopatinDTO(Long id, boolean enMantenimiento) {
        this.id = id;
        this.enMantenimiento = enMantenimiento;
    }

    public MonopatinDTO() {

    }

    public Long getId() {
        return id;
    }


    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}
