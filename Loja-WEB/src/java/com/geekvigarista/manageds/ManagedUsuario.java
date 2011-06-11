package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Usuario;
import com.geekvigarista.services.UsuarioServiceLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private Usuario usuario = null;
    private Long idSelecionado;
    private boolean admin;
    
    // injetando o managedLogin aqui!
    @ManagedProperty(value="#{managedLogin}")
    private ManagedLogin managedLogin;

    // CONSTRUTOR
    public ManagedUsuario() {
    }

    // GETTERS E SETTERS

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public ManagedLogin getManagedLogin() {
        return managedLogin;
    }
    
    // se nao tiver pelo o menos o setter, nao funciona.
    public void setManagedLogin(ManagedLogin managedLogin) {
        this.managedLogin = managedLogin;
    }
    
    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Usuario getUsuario() {
        if(managedLogin.getLogado() != null)
        {
            usuario = managedLogin.getLogado();
        } else {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = service.find(usuario.getId());
        setAdmin(usuario.getGrupo().equals("admins"));
    }

    // LOGICAS
    /**
     * salva no banco, tanto criação quanto edicao
     */
    public void salvar() {
        try {
            if(managedLogin.isAdmin())
            {
                if(isAdmin()){
                    usuario.setGrupo("admins");
                } else {
                    usuario.setGrupo("users");
                }
            }
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

    public String cadastrar() {
        usuario.setGrupo("users");
        if (!usuario.getPassword().equals(usuario.getConfirmacaoSenha())) {
            showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não conferem!", "As senhas não conferem!"));
            return "erro";
        }
        try {
            salvar();
            managedLogin.setLogado(usuario);
            return "sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "erro";
        }
    }
}
