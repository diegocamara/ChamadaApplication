package com.application.chamada.fragment;

import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.application.chamada.R;

public class DateDialog extends android.support.v4.app.DialogFragment {

	public static final String DATA = "data";
	private DateTime dataEscolhida;

	public static DateDialog newInstance(DateTime datetime) {		
		Bundle bundle = new Bundle();
		bundle.putSerializable(DATA, datetime);		
		DateDialog dateDialog = new DateDialog();	
		dateDialog.setArguments(bundle);
		return dateDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {			
		
		DateTime date = (DateTime)getArguments().getSerializable(DATA);
		
		int year = date.getYear();
		int month = date.getMonthOfYear();
		int day = date.getDayOfMonth();
				
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);				
				
		OnDateChangedListener onDateChangedListener = new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {	
										
				Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();				
				
				setDateTime(new DateTime(date));
			}
		};

		DatePicker datePicker = (DatePicker) view
				.findViewById(R.id.dialog_date);
		datePicker.init(year, --month, day, onDateChangedListener);
		
		

		return new AlertDialog.Builder(getActivity()).setView(view)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				}).create();
	}

	private void sendResult(int resultCode) {

		if (getTargetFragment() == null) {
			return;
		}

		Intent intent = new Intent();

		intent.putExtra(DATA, getArguments().getSerializable(DATA));

		getTargetFragment().onActivityResult(getTargetRequestCode(),
				resultCode, intent);
	}

	private void setDateTime(DateTime dateTime) {
		this.dataEscolhida = dateTime;
		getArguments().putSerializable(DATA, this.dataEscolhida);
	}

	public DateTime getDataEscolhida() {
		return dataEscolhida;
	}

	public void setDataEscolhida(DateTime dataEscolhida) {
		this.dataEscolhida = dataEscolhida;
	}

}
