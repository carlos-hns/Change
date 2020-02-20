package com.carloshns.change.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quedas {

    private Long id;
    private String descricao;
    private String data;

    public Quedas(){
    }

    public Quedas(String descricao) {
        this.descricao = descricao;

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date data = new Date();

        this.data = fmt.format(data);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
