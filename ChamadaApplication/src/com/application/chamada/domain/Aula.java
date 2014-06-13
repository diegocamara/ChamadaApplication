package com.application.chamada.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "AULA")
public class Aula {

	@DatabaseField(id = true)
	private Disciplina disciplina;

	@DatabaseField(dataType = DataType.DATE_TIME)
	private DateTime data;

	@DatabaseField(dataType = DataType.DATE_TIME)
	private DateTime horaInicio;

	@DatabaseField(dataType = DataType.DATE_TIME)
	private DateTime horaFim;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
		this.data = data;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.disciplina).append(this.data)
				.append(this.horaInicio).append(this.horaFim).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Aula) {
			final Aula aula = (Aula) obj;
			return new EqualsBuilder()
					.append(this.disciplina, aula.getDisciplina())
					.append(this.data, aula.getData())
					.append(this.horaInicio, aula.getHoraInicio())
					.append(this.horaFim, aula.getHoraFim()).isEquals();
		} else {
			return false;
		}

	}
}
