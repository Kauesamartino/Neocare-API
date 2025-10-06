package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Endereco;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.EnderecoOutputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioResumoOutputDTO;

public final class UsuarioMapper {
    private UsuarioMapper() {
        super();
    }

    public static Usuario toModel(UsuarioInputDTO dto) {
        Endereco endereco = toEnderecoModel(
                dto.endereco().logradouro(),
                dto.endereco().bairro(),
                dto.endereco().cep(),
                dto.endereco().numero(),
                dto.endereco().complemento(),
                dto.endereco().cidade(),
                dto.endereco().uf()
        );

        return new Usuario(
                dto.nome(),
                dto.sobrenome(),
                dto.cpf(),
                dto.email(),
                dto.telefone(),
                dto.dataNascimento(),
                dto.sexo(),
                dto.altura(),
                dto.peso(),
                endereco
        );
    }

    public static UsuarioOutputDTO toOutputDTO(Usuario usuario) {
        EnderecoOutputDTO enderecoOutputDTO = new EnderecoOutputDTO(
                usuario.getEndereco().getLogradouro(),
                usuario.getEndereco().getBairro(),
                usuario.getEndereco().getCep(),
                usuario.getEndereco().getNumero(),
                usuario.getEndereco().getComplemento(),
                usuario.getEndereco().getCidade(),
                usuario.getEndereco().getUf()
        );

        return new UsuarioOutputDTO(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDataNascimento(),
                usuario.getSexo(),
                usuario.getAltura(),
                usuario.getPeso(),
                enderecoOutputDTO,
                usuario.isAtivo()
        );
    }

    public static Endereco toEnderecoModel(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String uf) {
        return new Endereco(
                logradouro, bairro, cep, numero, complemento, cidade, uf
        );
    }

    public static JpaUsuarioEntity toJpa(Usuario usuario) {
        Endereco endereco = toEnderecoModel(
                usuario.getEndereco().getLogradouro(),
                usuario.getEndereco().getBairro(),
                usuario.getEndereco().getCep(),
                usuario.getEndereco().getNumero(),
                usuario.getEndereco().getComplemento(),
                usuario.getEndereco().getCidade(),
                usuario.getEndereco().getUf()
        );

        return new JpaUsuarioEntity(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDataNascimento(),
                usuario.getSexo(),
                usuario.getAltura(),
                usuario.getPeso(),
                endereco,
                usuario.isAtivo()
        );
    }

    public static Usuario entityToDomain(JpaUsuarioEntity savedEntity) {
        return new Usuario(
                savedEntity.getNome(),
                savedEntity.getSobrenome(),
                savedEntity.getCpf(),
                savedEntity.getEmail(),
                savedEntity.getTelefone(),
                savedEntity.getDataNascimento(),
                savedEntity.getSexo(),
                savedEntity.getAltura(),
                savedEntity.getPeso(),
                savedEntity.getEndereco(),
                savedEntity.getAtivo()
        );
    }

    public static UsuarioResumoOutputDTO toResumoOutputDTO(Usuario usuario) {
        return new UsuarioResumoOutputDTO(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }
}
