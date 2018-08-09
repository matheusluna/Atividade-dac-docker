/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.controladores;

import br.edu.ifpb.services.ServiceContato;
import br.ifpb.edu.entidades.Contato;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathe
 */
@Named
@SessionScoped
public class ControladorContato implements Serializable{
    
    private String busca;
    private String inicial;
    private String username;
    private String password;
    private Contato contato;
    private ServiceContato service;
    private List<Contato> contatos;
    private List<Contato> contatosLetra;
    private boolean loggado;
    private boolean modoEditando;
    
    @PostConstruct
    public void construct(){
        this.contato = new Contato();
        this.service = new ServiceContato();
        this.contatos = new ArrayList<>();
        this.contatosLetra = new ArrayList<>();
        this.loggado = false;
        this.modoEditando = false;
    }
    
    public String salvar() {
        if (loggado) {
            this.service.add(this.contato);
            this.contato = new Contato();
            return "";
        } else {
            return "index.xhtml";
        }
    }
    
    public String remover(Contato contato) {
        if(loggado) {
            this.service.remove(contato);
            return "";
        } else {
            return "index.xhtml";
        }
    }
    
    public String atualizar() {
        if (loggado) {
            if(this.service.set(contato)) {
                this.modoEditando = false;
                this.contato = new Contato();
            }
            return "";
        } else {
            return "index.xhtml";
        }
    }
    
    public List<Contato> listarTodos() {
        if (loggado) {
            return service.list();
        } else {
            return new ArrayList<>();
        }
    }
    
    public List<Contato> listarPorNome() {
        if (loggado) {
            contatos = this.service.list(busca);
            return contatos;
        } else {
            return new ArrayList<>();
        }
    }
    
    public List<Contato> listarPorIncial() {
        if (loggado) {
            contatosLetra = this.service.listInit(inicial);
            return contatosLetra;
        } else {
            return new ArrayList<>();
        }
    }
    
    public String login() {
        if (this.username.equals("admin") && this.password.equals("admin")) {
            this.loggado = true;
            return "principal.xhtml";
        } else {
            this.loggado = false;
            return "index.xhtml";
        }
    }
    
    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();

        try {
            externalContext.redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControladorContato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ServiceContato getService() {
        return service;
    }

    public void setService(ServiceContato service) {
        this.service = service;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatosLetra() {
        return contatosLetra;
    }

    public void setContatosLetra(List<Contato> contatosLetra) {
        this.contatosLetra = contatosLetra;
    }

    public boolean isLoggado() {
        return loggado;
    }

    public void setLoggado(boolean loggado) {
        this.loggado = loggado;
    }

    public boolean isModoEditando() {
        return modoEditando;
    }

    public void setModoEditando(boolean modoEditando) {
        this.modoEditando = modoEditando;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.busca);
        hash = 53 * hash + Objects.hashCode(this.inicial);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.contato);
        hash = 53 * hash + Objects.hashCode(this.service);
        hash = 53 * hash + Objects.hashCode(this.contatos);
        hash = 53 * hash + Objects.hashCode(this.contatosLetra);
        hash = 53 * hash + (this.loggado ? 1 : 0);
        hash = 53 * hash + (this.modoEditando ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControladorContato other = (ControladorContato) obj;
        if (this.loggado != other.loggado) {
            return false;
        }
        if (this.modoEditando != other.modoEditando) {
            return false;
        }
        if (!Objects.equals(this.busca, other.busca)) {
            return false;
        }
        if (!Objects.equals(this.inicial, other.inicial)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.contato, other.contato)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        if (!Objects.equals(this.contatos, other.contatos)) {
            return false;
        }
        if (!Objects.equals(this.contatosLetra, other.contatosLetra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ControladorContato{" + "busca=" + busca + ", inicial=" + inicial + ", username=" + username + ", password=" + password + ", contato=" + contato + ", service=" + service + ", contatos=" + contatos + ", contatosLetra=" + contatosLetra + ", loggado=" + loggado + ", modoEditando=" + modoEditando + '}';
    }
    
    
}
