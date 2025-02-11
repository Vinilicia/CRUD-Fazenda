package com.tpbancodedados.Controller;

import java.util.List;

import com.tpbancodedados.Model.Funcionario;
import com.tpbancodedados.Persistence.FuncionarioDAO;

public class FuncionarioController {
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	public List<Funcionario> listarFuncionarios() {
		return funcionarioDAO.listarFuncionarios();
	}

	public Funcionario buscarPorNome(String nome) {
		return funcionarioDAO.buscarFuncionarioPorNome(nome);
	}

	public Funcionario buscarPorCPF(String CPF) {
		return funcionarioDAO.buscarFuncionarioPorCPF(CPF);
	}

	
}
