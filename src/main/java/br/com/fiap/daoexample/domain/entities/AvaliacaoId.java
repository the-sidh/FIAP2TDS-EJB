package br.com.fiap.daoexample.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class AvaliacaoId implements Serializable {

    private int usuario;

    private int estabelecimento;

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoId that = (AvaliacaoId) o;
        return usuario == that.usuario &&
                estabelecimento == that.estabelecimento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, estabelecimento);
    }
}
