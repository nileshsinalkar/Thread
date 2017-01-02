package com.example.nilesh.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
    private Handler handler;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<=10;i++){
                        try{
                            Thread.sleep(1000);
                            sendmessagetohandler("" + i);
                            }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        }
                    }
                });
                thread.start();
            }
        });

         handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle =msg.getData();
                String data=null;
                if (bundle!=null)
                   data= bundle.getString("msg");


                if (data!= null)
                    textView.setText(data);



            }
        };


    }

    private void sendmessagetohandler(String message){
            if(message!=null){
            Message msg=handler.obtainMessage();

                Bundle bundle=new Bundle();
                bundle.putString("msg",message);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

    }
}
