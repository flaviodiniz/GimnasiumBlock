package com.flsystem.pds.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Requerimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotEmpty(message = "O campo descrição é obrigatoria!")
	@Size(max = 100, message = "Só é permitido no maximo 100 caracteres!")
	private String descricao;

	@NotEmpty(message = "O campo nome é obrigatorio!")
	@Size(max = 60, message = "Só é permitido no maximo 60 caracteres!")
	private String nome;

	@NotEmpty(message = "O campo do email é obrigatorio!")
	@Size(max = 30, message = "Só é permitido no maximo 30 caracteres!")
	private String email;

	@NotEmpty(message = "O campo do cpf é obrigatorio!")
	@Size(max = 14, message = "Só é permitido no maximo 11 caracteres!")
	private String cpf;

	@NotEmpty(message = "O campo do telefone é obrigatorio!")
	@Size(max = 30, message = "Só é permitido no maximo 30 caracteres!")
	private String telefone;

	@NotEmpty(message = "O campo do rg é obrigatorio!")
	@Size(max = 10, message = "Só é permitido no maximo 30 caracteres!")
	private String rg;

	@NotEmpty(message = "O campo do curso é obrigatorio!")
	@Size(max = 30, message = "Só é permitido no maximo 30 caracteres!")
	private String curso;

	@NotNull(message = "O campo da Data do cadastro é obrigatória!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private StatusRequerimento status;

	@Enumerated(EnumType.STRING)
	private ModalidadeRequerida modalidade;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH,
			CascadeType.REMOVE }, mappedBy = "requerimento")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private List<Participante> participantes;

	@OneToOne(mappedBy="requerimento", cascade = CascadeType.REMOVE)
	private Horario horario;

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public ModalidadeRequerida getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeRequerida modalidade) {
		this.modalidade = modalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public StatusRequerimento getStatus() {
		return status;
	}

	public void setStatus(StatusRequerimento status) {
		this.status = status;
	}

	public boolean isIndeferido() {
		return StatusRequerimento.INDEFERIDO.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((participantes == null) ? 0 : participantes.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requerimento other = (Requerimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (modalidade != other.modalidade)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (participantes == null) {
			if (other.participantes != null)
				return false;
		} else if (!participantes.equals(other.participantes))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (status != other.status)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
