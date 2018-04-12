package cn.zhengjun.androidprogramming2edition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import timber.log.Timber;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CheatActivity";
    public static final String ANSWER_IS_TRUE = "cn.zhengjun.androidprogramming2edition.ANSWER_IS_TRUE";
    public static final String EXTRA_ANSWER_SHOWN = "extra_answer_shown";
    private boolean mBooleanExtra;
    private TextView tv_cheat_answer;
    private TextView tv_cheat_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mBooleanExtra = getIntent().getBooleanExtra(ANSWER_IS_TRUE, false);
        tv_cheat_answer = findViewById(R.id.tv_cheat_answer);
        tv_cheat_confirm = findViewById(R.id.tv_cheat_confirm);
        tv_cheat_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_cheat_answer.setText(mBooleanExtra?"TRUE":"FALSE");
                setResult(RESULT_OK,new Intent().putExtra(EXTRA_ANSWER_SHOWN,true));
                if (!tv_cheat_answer.isShown()) {
                    tv_cheat_answer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Timber.tag(TAG).i("CheatActivity.onNewIntent()  " + "intent = [" + intent + "]");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 将启动一个Activity所需的信息封装到该Activity的一个静态公有方法中,体现了封装复用原则
     * Don't repeat yourself!
     * @author Zheng Jun
     */
    public static Intent newIntent(Context pContext,boolean isAnswerTrue){
        Intent vIntent = new Intent(pContext, CheatActivity.class);
        vIntent.putExtra(ANSWER_IS_TRUE,isAnswerTrue);
        return vIntent;
    }
}
