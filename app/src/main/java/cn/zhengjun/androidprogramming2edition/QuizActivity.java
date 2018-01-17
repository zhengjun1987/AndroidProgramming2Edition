package cn.zhengjun.androidprogramming2edition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.zhengjun.androidprogramming2edition.bean.Question;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_true;
    private Button btn_false;
    private TextView tv_question_text;
    private ArrayList<Question> mQuestions;
    private int mQuestionIndex = 0;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn_true = (Button) findViewById(R.id.btn_true);
        btn_false = (Button) findViewById(R.id.btn_false);
        btn_next = (Button) findViewById(R.id.btn_next);
        tv_question_text = (TextView) findViewById(R.id.tv_question_text);
        btn_false.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        mQuestions = new ArrayList<>();
        mQuestions.add(new Question(R.string.questions_ocean, true));
        mQuestions.add(new Question(R.string.questions_mideast, false));
        mQuestions.add(new Question(R.string.questions_africa, false));
        mQuestions.add(new Question(R.string.questions_america, true));
        mQuestions.add(new Question(R.string.questions_asia, true));
        updateQuestion();

    }

    @Override
    public void onClick(View v) {
        System.out.println("v = " + v.getId());
        switch (v.getId()) {
            case R.id.btn_false:
                checkAnswer(false);
                break;
            case R.id.btn_true:
                checkAnswer(true);
                break;
            case R.id.btn_next:
                mQuestionIndex = (mQuestionIndex + 1) % mQuestions.size();
                updateQuestion();
                break;
        }
    }

    private void checkAnswer(boolean b) {
        Toast.makeText(this, (b == mQuestions.get(mQuestionIndex).isAnswer()) ? R.string.correct : R.string.incorrect, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        System.out.println("mQuestionIndex = " + mQuestions.get(mQuestionIndex).toString());
        int questionTextResId = mQuestions.get(mQuestionIndex).getQuestionTextResId();
        tv_question_text.setText(questionTextResId);
    }
}
