package com.application.chamada.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "HORARIO")
public class Horario {

	public static final String HORA_INICIO = "HORA_INICIO";
	public static final String HORA_FIM = "HORA_FIM";

	@DatabaseField(generatedId = true)
	private int codigo;

	@DatabaseField(canBeNull = false, dataType = DataType.DATE_TIME, columnName = HORA_INICIO)
	private DateTime horaInicio;

	@DatabaseField(canBeNull = false, dataType = DataType.DATE_TIME, columnName = HORA_FIM)
	private DateTime horaFim;

	@DatabaseField(canBeNull = false)
	private DiaSemanaEnum dia;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Disciplina disciplina;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public DateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(DateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public DateTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(DateTime horaFim) {
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.dia).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Horario) {
			final Horario horario = (Horario) obj;
			return new EqualsBuilder().append(this.dia, horario.getDia())
					.isEquals();
		} else {
			return false;
		}

	}

}
