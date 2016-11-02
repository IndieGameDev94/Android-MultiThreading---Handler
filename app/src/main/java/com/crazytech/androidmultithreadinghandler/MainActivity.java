package com.crazytech.androidmultithreadinghandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Thread mThread;
    private Handler mHandler;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.main_activity_progressbar);

        mThread = new Thread(new MyThread());
        mThread.start();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mProgressBar.setProgress(msg.arg1);
            }
        };
    }


    public class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Message mMessage = Message.obtain();
                mMessage.arg1 = i;
                mHandler.sendMessage(mMessage);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
