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

    public static String cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha, String conf_Senha) {
        String mensagem = "";
        boolean podeSalvar = false;

        if (!senha.equals(conf_Senha) || senha == null || senha.trim().equals("")) {
            mensagem += "Senha e confirmação de senha devem ser iguais, e a senha deve ser informada";
            podeSalvar = false;
        }
        if (!S_ValidadorCPF.validateCPF(cpf)) {
            mensagem += "CPF invalido";
            podeSalvar = false;
        }
        if (nome == null || nome.trim().equals("")) {
            mensagem += "O nome precisa ser informado";
            podeSalvar = false;

        }
        if ((email == null || email.trim().equals("")) && (telefone == null || telefone.trim().equals(""))) {
            mensagem += "Email ou telefone precisam ser informados";
            podeSalvar = false;
        }


        M_Pessoa m_pessoa = new M_Pessoa();
        m_pessoa.setNome(nome);
        m_pessoa.setEmail(email);
        m_pessoa.setSenha(senha);
        m_pessoa.setCpf(Long.valueOf(S_LimpaNumero.limpar(cpf)));
        m_pessoa.setTelefone(Long.valueOf(S_LimpaNumero.limpar(telefone)));
        r_pessoa.save(m_pessoa);
        mensagem += "Pessoa Cadastrada Com Sucesso";

        /////designer patters do cpf e do email


        return mensagem;
    }
}
