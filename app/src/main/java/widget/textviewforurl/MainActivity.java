package widget.textviewforurl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 大灯泡 on 2015/11/4.
 */
public class MainActivity extends Activity implements OnClickListener{
    private HttpTextView mHttpTextView;
    private EditText mEditText;
    private Button mButton;
    private Button mButtonWithSpan;

    private String testStr="大灯泡说。。。:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHttpTextView= (HttpTextView) findViewById(R.id.tx_test);
        mEditText= (EditText) findViewById(R.id.ed_test);
        mButton= (Button) findViewById(R.id.btn_test);
        mButtonWithSpan=(Button) findViewById(R.id.btn_test2);

        mButton.setOnClickListener(this);
        mButtonWithSpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test:
                test1();
                break;
            case R.id.btn_test2:
                test2();
                break;
            default:break;
        }

    }

    private void test1() {
        if (mEditText.getText().toString().equals("")){
            mHttpTextView.setUrlText(mHttpTextView.testText);
        }else {
            mHttpTextView.setUrlText(mEditText.getText().toString());
        }
    }

    private void test2() {
        SpannableStringBuilderAllVer spannableStringBuilderAllVer=new SpannableStringBuilderAllVer();
        spannableStringBuilderAllVer.append(testStr+"1",new TestClick(testStr+"1"),0);
        spannableStringBuilderAllVer.append(testStr+"2",new TestClick(testStr+"2"),0);
        spannableStringBuilderAllVer.append(testStr+"3",new TestClick(testStr+"3"),0);
        spannableStringBuilderAllVer.append('\n');
        spannableStringBuilderAllVer.append(mHttpTextView.testText);
        mHttpTextView.setUrlText(spannableStringBuilderAllVer);

    }
    //=====================测试用的clickspan========================================
    class TestClick extends ClickableSpan{
        private String text;

        public TestClick(String text) {
            this.text = text;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(widget.getContext(), text, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.RED);
            ds.setUnderlineText(false);
        }
    }
}
