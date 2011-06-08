package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Usuario;
import com.geekvigarista.services.UsuarioServiceLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedUsuario extends ManagedCadastro implements Serializable {

    
    @EJB
    private UsuarioServiceLocal service;
    private Usuario usuario = new Usuario();
    private Long idSelecionado;

    // CONSTRUTOR
    public ManagedUsuario() {
    }

    // GETTERS E SETTERS
    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // LOGICAS
    /**
     * salva no banco, tanto criação quanto edicao
     */
    public void salvar() {
        try {
            if (usuario.getId() == null) {
                service.create(usuario);
            } else {
                service.edit(usuario);
            }
            showMensagemSalvar(usuario.getNome());
        } catch (Exception e) {
            e.printStackTrace();
            showMensagemErroSalvar(usuario.getNome(), e.getMessage());
        }
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void excluir() {
        try {
            service.delete(usuario);
            showMensagemExcluir(usuario.getNome());
            novo();
        } catch (Exception e) {
            e.printStackTrace();
            showMensagemErroExcluir(usuario.getNome(), e.getMessage());
        }
    }

    public void load() {
        if (idSelecionado != null) {
            usuario = service.find(idSelecionado);
        }
    }

    public void cadastrar() {
        usuario.setGrupo("users");
        if (!usuario.getPassword().equals(usuario.getConfirmacaoSenha())) {
            showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não conferem!", "As senhas não conferem!"));
            return;
        }
        
        salvar();
        
    }
}
