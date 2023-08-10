package com.MundoSenaiTDSN.ListaParticipantes.Service;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Pessoa;
import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Resposta;
import com.MundoSenaiTDSN.ListaParticipantes.Repository.R_Pessoa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Resposta cadastrarPessoa(String nome, String cpf, String email, String telefone, String senha, String conf_Senha) {
        String mensagem = "";
        boolean podeSalvar = false;

        if (!senha.equals(conf_Senha) || senha == null || senha.trim().equals("")) {
            mensagem += " Senha e confirmação de senha devem ser iguais, e a senha deve ser informada <br/> ";
            podeSalvar = false;
        }
        if (!S_ValidadorCPF.validateCPF(cpf)) {
            mensagem += " CPF invalido <br/> " ;
            podeSalvar = false;
        }
        if (nome == null || nome.trim().equals("")) {
            mensagem += " O nome precisa ser informado<br/> " ;
            podeSalvar = false;

        }
        if ((email == null || email.trim().equals("")) && (telefone == null || telefone.trim().equals(""))) {
            mensagem += " Email ou telefone precisam ser informados <br/>";
            podeSalvar = false;
        }


        if (podeSalvar) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setEmail(email);
            m_pessoa.setSenha(senha);
            telefone = S_LimpaNumero.limpar(telefone);
            m_pessoa.setCpf(Long.valueOf(S_LimpaNumero.limpar(cpf)));
            if (telefone == "") {
                m_pessoa.setTelefone(null);
            } else {
                m_pessoa.setTelefone(Long.valueOf(telefone));
            }
            try {
                r_pessoa.save(m_pessoa);
                mensagem += "Pessoa Cadastrada Com Sucesso ";
            } catch (DataIntegrityViolationException e) {
                mensagem += e.getMessage();
                podeSalvar = false;
            }
        }
            M_Resposta m_resposta = new M_Resposta(podeSalvar, mensagem);


            return m_resposta;
        }
    }
