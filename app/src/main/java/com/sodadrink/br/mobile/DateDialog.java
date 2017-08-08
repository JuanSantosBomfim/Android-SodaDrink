package com.sodadrink.br.mobile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

/**
 * Created by Amon on 18/05/2017.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    TextView txtMostrarData;

    public DateDialog(TextView view){

        txtMostrarData = (TextView)view;

    }

    public Dialog onCreateDialog(Bundle bundle){

        final Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, ano, mes, dia);

    }


    @Override
    public void onDateSet(DatePicker view, int ano, int mes, int dia) {

        String data = dia+"/"+(mes+1)+"/"+ano;
        txtMostrarData.setText(data);

    }

}
