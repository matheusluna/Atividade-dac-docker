/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.interfaces;

import br.ifpb.edu.entidades.Contato;
import java.util.List;

/**
 *
 * @author mathe
 */
public interface ServiceContatoInterface {
    public boolean add(Contato contato);
    public Contato search(String email);
    public boolean set(Contato contato);
    public boolean remove(Contato contato);
    public List<Contato> list();
    public List<Contato> list(String nome);
    public List<Contato> listInit(String letra);
}
