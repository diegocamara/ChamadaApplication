package com.application.chamada.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DISCIPLINA")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CODIGO = "CODIGO";
	public static final String NOME = "NOME";
	public static final String DATA_INICIO = "DATA_INICIO";
	public static final String DATA_FIM = "DATA_FIM";
	public static final String HORARIOS = "HORARIOS";

	@DatabaseField(generatedId = true, columnName = CODIGO)
	private int codigo;

	@DatabaseField(canBeNull = false, columnName = NOME)
	private String nome;

	@DatabaseField(canBeNull = false, dataType = DataType.DATE_TIME, columnName = DATA_INICIO)
	private DateTime dataInicio;

	@DatabaseField(canBeNull = false, dataType = DataType.DATE_TIME, columnName = DATA_FIM)
	private DateTime dataFim;

	// @ForeignCollectionField
	// private ForeignCollection<Aluno> alunos;

	@ForeignCollectionField
	private ForeignCollection<Horario> horarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public DateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(DateTime dataFim) {
		this.dataFim = dataFim;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ForeignCollection<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(ForeignCollection<Horario> horarios) {
		this.horarios = horarios;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.codigo).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Disciplina) {
			final Disciplina disciplina = (Disciplina) obj;
			return new EqualsBuilder().append(this.codigo,
					disciplina.getCodigo()).isEquals();
		} else {
			return false;
		}

	}

}
