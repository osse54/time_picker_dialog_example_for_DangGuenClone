package com.example.time_picker_dialog_example;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 현재 시간 가져오기, TimePicker에 사용
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // 예제의 목적인 MaterialTimePicker, 24시간으로 적용, 현재 시간으로 초기화
        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(hour)
                .setMinute(minute)
                .setTitleText("시간 선택") // Dialog 제목
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD) // 생성되면 키보드로 입력하도록 설정
                .build();

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 완료 버튼 클릭 시
                Log.i(TAG, "onClick: " + materialTimePicker.getHour() + "시 " + materialTimePicker.getMinute() + "분");
            }
        });

        // Dialog 출력
        materialTimePicker.show(getSupportFragmentManager(), TAG);

        /*
        이슈 발생:
        InputMode를 MaterialTimePicker.INPUT_MODE_KEYBOARD로 설정
        Dialog가 출력되면 Hour의 입력 창에 포커스가 맞춰지고 키보드가 출력됨
        Builder()에서 setHour()로 설정했던 시간이 사라짐.

        동일한 문제로 github에 이슈가 등록되어 있음.
        1.6.1 버전에서 1.7.0-alpha02 버전으로 업데이트하면서 발생한 문제라고 함.
        Author가 원인으로 추측하는 코드
        https://github.com/material-components/material-components-android/commit/72abed042f7577a6e24219742d0a5ac860adcb56

        하지만 수정은 1.9.0-alpha01 버전에서 이루어짐.
        나는 1.6.1 버전을 사용하겠음.

        이슈 링크:
        https://github.com/material-components/material-components-android/issues/2862
         */

        /*
        색상 변경
        themes.xml에서 colorPrimary를 purple_500으로 가지고 있다.
        해당 변수의 색상을 변경하면 Dialog의 테마 색이 변경되는 것을 확인할 수 있다.
         */
    }
}