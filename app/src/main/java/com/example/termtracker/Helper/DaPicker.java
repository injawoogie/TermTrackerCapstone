package com.example.termtracker.Helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

public class DaPicker {

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private final Calendar calendar;
    private final SimpleDateFormat dateFormat;
    private final Context context;
    private final EditText editText;

    public DaPicker(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;
        this.calendar = Calendar.getInstance();
        this.dateFormat = Utility.SIMPLE_DATE_FORMAT;
    }

    public void activate() {
        makeDatePicker();
        updateText();
    }

    private void makeDatePicker() {

        this.editText.setOnClickListener(v -> {

            String info = this.editText.getText().toString();

            if (info.equals("")) info = LocalDate.now().format(DateTimeFormatter.ofPattern(Utility.DATE_FORMATTER));

            try {
                calendar.setTime(Objects.requireNonNull(dateFormat.parse(info)));
            } catch (ParseException exception) {
                exception.printStackTrace();
            }

            new DatePickerDialog(context, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

    }

    private void updateText() {
        dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            this.updateDate(this.editText);
        };
    }

    private void updateDate(EditText ev) {
        ev.setText(dateFormat.format(calendar.getTime()));
    }


}
