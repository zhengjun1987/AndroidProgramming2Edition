package cn.zhengjun.androidprogramming2edition.bean;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2018/1/11 16:26
 * Summary : null
 */

public class Question {
    public static final int sLantent = 1902;
    private int mQuestionTextResId;
    private boolean mAnswer;

    public static int getLantent() {
        return sLantent;
    }

    public Question(int questionTextResId, boolean answer) {
        mQuestionTextResId = questionTextResId;
        mAnswer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestionTextResId=" + mQuestionTextResId +
                ", mAnswer=" + mAnswer +
                '}';
    }

    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }
}
