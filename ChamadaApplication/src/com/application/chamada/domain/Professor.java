package com.application.chamada.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PROFESSOR")
public class Professor {

	@DatabaseField(generatedId = true)
	private int codigo;

	@DatabaseField(canBeNull = false, width = 300)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.nome).append(this.codigo).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Professor) {
			final Professor professor = (Professor) obj;
			return new EqualsBuilder().append(this.nome, professor.getNome())
					.append(this.codigo, professor.getCodigo()).isEquals();
		} else {
			return false;
		}

	}

}
