package com.carloshns.change.helper;

import com.carloshns.change.entities.Quedas;

import java.util.List;

public interface IQuedasDAO {

    public boolean salvar(Quedas queda);
    public boolean atualizar(Quedas queda);
    public boolean deletar(Quedas queda);
    public List<Quedas> quedas();


}
