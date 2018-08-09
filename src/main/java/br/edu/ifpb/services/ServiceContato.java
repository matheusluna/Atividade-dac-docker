/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.services;

import br.edu.ifpb.daos.DaoContato;
import br.edu.ifpb.interfaces.ServiceContatoInterface;
import br.ifpb.edu.entidades.Contato;
import java.util.List;

/**
 *
 * @author mathe
 */
public class ServiceContato implements ServiceContatoInterface{
    
    private DaoContato dao = new DaoContato();
    
    @Override
    public boolean add(Contato contato) {
        return dao.create(contato);
    }

    @Override
    public Contato search(String email) {
        return dao.read(email);
    }

    @Override
    public boolean set(Contato contato) {
        return dao.update(contato);
    }

    @Override
    public boolean remove(Contato contato) {
        return dao.delete(contato);
    }

    @Override
    public List<Contato> list() {
        return dao.list();
    }

    @Override
    public List<Contato> list(String nome) {
        return dao.list(nome);
    }

    @Override
    public List<Contato> listInit(String letra) {
        return dao.listInitial(letra);
    }
    
}
