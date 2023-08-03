package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.stereotype.Service;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static String cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha){
        M_Pessoa m_pessoa = new M_Pessoa();
        m_pessoa.setNome(nome);
        m_pessoa.setEmail(email);
        m_pessoa.setSenha(senha);
        m_pessoa.setCpf(Long.valueOf(cpf));
        m_pessoa.setTelefone(Long.valueOf(telefone));
        r_pessoa.save(m_pessoa);

        /////designer patters do cpf e do email


       return "Pessoa Cadastrada Com Sucesso";
    }
}
