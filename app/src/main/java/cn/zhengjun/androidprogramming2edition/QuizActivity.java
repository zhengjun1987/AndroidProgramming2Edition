package cn.zhengjun.androidprogramming2edition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import cn.zhengjun.androidprogramming2edition.bean.Question;
import timber.log.Timber;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_INDEX;
    public static final int REQUEST_CODE = 237;

    static {
        KEY_INDEX = "key_index";
    }

    private TextView tv_question_text;
    private ArrayList<Question> mQuestions;
    private int mQuestionIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        Timber.tag(TAG).i(String.format("QuizActivity.onCreate()  savedInstanceState = [%s]", savedInstanceState));
        setContentView(R.layout.activity_quiz);
        Button vBtn_true = findViewById(R.id.btn_true);
        Button vBtn_false = findViewById(R.id.btn_false);
        Button vBtn_next = findViewById(R.id.btn_next);
        Button vBtn_previous = findViewById(R.id.btn_previous);
        Button vButton_cheat = findViewById(R.id.cheat_button);
        tv_question_text = findViewById(R.id.tv_question_text);
        vBtn_false.setOnClickListener(this);
        vBtn_true.setOnClickListener(this);
        vBtn_next.setOnClickListener(this);
        vBtn_previous.setOnClickListener(this);
        vButton_cheat.setOnClickListener(this);

        mQuestions = new ArrayList<>();
        mQuestions.add(new Question(R.string.questions_ocean, true));
        mQuestions.add(new Question(R.string.questions_mideast, false));
        mQuestions.add(new Question(R.string.questions_africa, false));
        mQuestions.add(new Question(R.string.questions_america, true));
        mQuestions.add(new Question(R.string.questions_asia, true));

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_INDEX)) {
            mQuestionIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();

    }

    private static final String TAG = "QuizActivity";

    @Override
    public void onClick(View v) {
        Timber.tag(TAG).i("QuizActivity.onClick()  " + "v = [" + v + "]");
        switch (v.getId()) {
            case R.id.btn_false:
                checkAnswer(false);
                break;
            case R.id.btn_true:
                checkAnswer(true);
                break;
            case R.id.btn_next:
                mQuestionIndex = (mQuestionIndex + 1) % mQuestions.size();
                mIsCheater = false;
                updateQuestion();
                break;
            case R.id.cheat_button:
                // 2018/4/12 start cheatActivity
                startActivityForResult(CheatActivity.newIntent(this,mQuestions.get(mQuestionIndex).isAnswer()), REQUEST_CODE);
                break;
            case R.id.btn_previous:
                if (mQuestionIndex == 0) {
                    mQuestionIndex = mQuestions.size();
                }
                mQuestionIndex = (mQuestionIndex - 1) % mQuestions.size();
                updateQuestion();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.tag(TAG).i("QuizActivity.onActivityResult()  " + "requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mIsCheater = data != null && data.hasExtra(CheatActivity.EXTRA_ANSWER_SHOWN);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.tag(TAG).i("QuizActivity.onSaveInstanceState()  " + "outState = [" + outState + "]");
        outState.putInt(KEY_INDEX,mQuestionIndex);
    }

    private void checkAnswer(boolean b) {
        if (!mIsCheater) {
            Toast.makeText(this, (b == mQuestions.get(mQuestionIndex).isAnswer()) ? R.string.correct : R.string.incorrect, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "YOU BIG CHEATER!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion() {
//        Log.d(TAG, "onClick: Updating question #"+mQuestionIndex,new Exception());//法记录并输出整个栈跟踪信息
//        Timber.tag(TAG).d(new Exception(),"QuizActivity.updateQuestion()  " + "");
        int questionTextResId = mQuestions.get(mQuestionIndex).getQuestionTextResId();
        tv_question_text.setText(questionTextResId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.tag(TAG).i("QuizActivity.onDestroy()  " + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.tag(TAG).i("QuizActivity.onStart()  " + "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.tag(TAG).i("QuizActivity.onStop()  " + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.tag(TAG).i("QuizActivity.onResume()  " + "");
        String vProperty = System.getProperty("user.dir");
        File vFile = new File(vProperty);
        System.out.println("System.getProperty(\"user.dir\") = " + vFile.getAbsolutePath());
        traverseFile(vFile);
    }

    private void traverseFile(File pFile) {
        for (File vFile : pFile.listFiles()) {
            System.out.println(vFile.getName() + " : "+vFile.getAbsolutePath());
        }

//        if (!pFile.isDirectory()) {
//            System.out.println(pFile.getName() + " : "+pFile.getAbsolutePath());
//        } else {
//            File[] vFiles = pFile.listFiles();
//            if (vFiles != null && vFiles.length != 0) {
//                for (File vFile : vFiles) {
//                    traverseFile(vFile);
//                }
//            }
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.tag(TAG).i("QuizActivity.onPause()  " + "");
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
