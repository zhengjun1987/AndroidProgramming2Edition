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
    private Button btn_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("QuizActivity.onCreate");
        setContentView(R.layout.activity_quiz);
        btn_true = (Button) findViewById(R.id.btn_true);
        btn_false = (Button) findViewById(R.id.btn_false);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_previous = (Button) findViewById(R.id.btn_previous);
        tv_question_text = (TextView) findViewById(R.id.tv_question_text);
        btn_false.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_previous.setOnClickListener(this);

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
            case R.id.btn_previous:
                if (mQuestionIndex==0) {
                    mQuestionIndex=mQuestions.size();
                }
                mQuestionIndex = (mQuestionIndex - 1) % mQuestions.size();
                updateQuestion();
                break;
        }
    }

    private void checkAnswer(boolean b) {
        Toast.makeText(this, (b == mQuestions.get(mQuestionIndex).isAnswer()) ? R.string.correct : R.string.incorrect, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        int questionTextResId = mQuestions.get(mQuestionIndex).getQuestionTextResId();
        tv_question_text.setText(questionTextResId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("QuizActivity.onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("QuizActivity.onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("QuizActivity.onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("QuizActivity.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("QuizActivity.onPause");
    }
}
////打开页面
//01-17 15:13:49.616 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onCreate
//01-17 15:13:49.741 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStart
//01-17 15:13:49.756 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onResume
//
////单击菜单键
//01-17 15:15:09.311 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onPause
//01-17 15:15:09.848 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStop
//
////重新点开APP
//01-17 15:16:31.535 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStart
//01-17 15:16:31.536 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onResume
//
////退出APP
//01-17 15:17:03.338 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onPause
//01-17 15:17:03.910 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStop
//01-17 15:17:03.910 18257-18257/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onDestroy






////正常启动APP
//01-17 15:24:29.186 24551-24551/? I/System.out: QuizActivity.onCreate
//01-17 15:24:29.290 24551-24551/? I/System.out: QuizActivity.onStart
//01-17 15:24:29.296 24551-24551/? I/System.out: QuizActivity.onResume
//
////旋转页面
//01-17 15:25:13.612 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onPause
//01-17 15:25:13.613 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStop
//01-17 15:25:13.613 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onDestroy
//01-17 15:25:13.655 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onCreate
//01-17 15:25:13.692 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStart
//01-17 15:25:13.694 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onResume
//
////再旋转回来
//01-17 15:25:46.087 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onPause
//01-17 15:25:46.088 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStop
//01-17 15:25:46.088 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onDestroy
//01-17 15:25:46.127 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onCreate
//01-17 15:25:46.165 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onStart
//01-17 15:25:46.167 24551-24551/cn.zhengjun.androidprogramming2edition I/System.out: QuizActivity.onResume
