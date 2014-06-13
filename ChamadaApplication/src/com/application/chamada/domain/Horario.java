package com.application.chamada.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "HORARIO")
public class Horario {

	@DatabaseField(generatedId = true)
	private int codigo;

	@DatabaseField(canBeNull = true)
	private int horaInicio;

	@DatabaseField(canBeNull = true)
	private int horaFim;

	@DatabaseField(canBeNull = false)
	private DiaSemanaEnum dia;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Disciplina disciplina;

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}

	public DiaSemanaEnum getDia() {
		return dia;
	}

	public void setDia(DiaSemanaEnum dia) {
		this.dia = dia;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.codigo).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Horario) {
			final Horario horario = (Horario) obj;
			return new EqualsBuilder().append(this.codigo,
					horario.getCodigo()).isEquals();
		} else {
			return false;
		}

	}

}
