package com.application.chamada.fragment;

import java.util.Calendar;

import org.joda.time.DateTime;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

public class DateDialog extends android.support.v4.app.DialogFragment {

	public static final String DATA = "data";
	private DateTime dataEscolhida;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		final int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
		final int minuteOfHour = now.get(Calendar.MINUTE);

		OnDateSetListener onDateSetListener = new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

				setDateTime(year, monthOfYear, dayOfMonth, hourOfDay,
						minuteOfHour);
								
			}
		};

		return new DatePickerDialog(getActivity(), onDateSetListener, year,
				month, day);
	}

	private void setDateTime(int year, int monthOfYear, int dayOfMonth,
			int hourOfDay, int minuteOfHour) {
		this.dataEscolhida = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay,
				minuteOfHour);
	}

	public DateTime getDataEscolhida() {
		return dataEscolhida;
	}

	public void setDataEscolhida(DateTime dataEscolhida) {
		this.dataEscolhida = dataEscolhida;
	}

}
