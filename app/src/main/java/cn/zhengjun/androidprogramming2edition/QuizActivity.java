package cn.zhengjun.androidprogramming2edition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_true;
    private Button btn_false;
    private TextView tv_question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn_true = (Button) findViewById(R.id.btn_true);
        btn_false = (Button) findViewById(R.id.btn_false);
        tv_question_text = (TextView) findViewById(R.id.tv_question_text);
        btn_false.setOnClickListener(this);
        btn_true.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        System.out.println("v = " + v.getId());
        switch (v.getId()) {
            case R.id.btn_false:
                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_true:
                Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
