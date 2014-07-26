package com.application.chamada.fragment;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class HourDialog extends DialogFragment implements OnTimeSetListener {

	public static final String HORA = "hora";
	public static final String MINUTOS = "minutos";
	public static final String POSITION = "position";
	public static final String HORA_TAG = "hora_tag";
	public static final int HORA_INICIO_REQUEST_CODE = 1;
	public static final int HORA_FIM_REQUEST_CODE = 2;

	private int hora;
	private int minutos;
	private int position;

	public static HourDialog newInstance(int position) {

		HourDialog hourDialog = new HourDialog();
		hourDialog.setPosition(position);

		return hourDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hora, minutos,
				DateFormat.is24HourFormat(getActivity()));
	}

	private void sendResult(int resultCode) {

		if (getTargetFragment() == null) {
			return;
		}

		Intent intent = new Intent();

		intent.putExtra(HORA, getHora());
		intent.putExtra(MINUTOS, getMinutos());
		intent.putExtra(POSITION, getPosition());

		getTargetFragment().onActivityResult(getTargetRequestCode(),
				resultCode, intent);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		setHora(hourOfDay);
		setMinutos(minute);
		sendResult(Activity.RESULT_OK);
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
