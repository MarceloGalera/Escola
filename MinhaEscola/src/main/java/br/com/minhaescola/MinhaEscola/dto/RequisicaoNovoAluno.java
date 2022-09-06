package br.com.minhaescola.MinhaEscola.dto;

import javax.validation.constraints.NotBlank;

import br.com.minhaescola.MinhaEscola.model.Aluno;

public class RequisicaoNovoAluno {

	private Long idade;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotBlank
	private String imagem;
	private int numeroDeSala;

	public int getNumeroDeSala() {
		return numeroDeSala;
	}

	public void setNumeroDeSala(int numeroDeSala) {
		this.numeroDeSala = numeroDeSala;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Aluno toAluno() {
		Aluno aluno = new Aluno();
		aluno.setDescricao(descricao);
		aluno.setNome(nome);
		aluno.setIdade(idade);
		aluno.setImagem(imagem);
		aluno.setNumeroDeSala(numeroDeSala);
		return aluno;
	}

}
