package minor.project;

import android.os.Bundle;

import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sms extends Activity {

   Button sendBtn;
   
   EditText txtphoneNo;
   EditText txtMessage;
   
   CurLocActivity main;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.sms);

      sendBtn = (Button) findViewById(R.id.btnSend);
      
      txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
      txtMessage = (EditText) findViewById(R.id.editTextSMS);

      sendBtn.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
            sendSMSMessage();
            
         }
      });
      
      
   }
   protected void sendSMSMessage() {
      Log.i("Send SMS", "");

      
      
      String phoneNo = txtphoneNo.getText().toString();
      
      String message = "these are the coordinates of my location"+ main.lati + "::"+ main.longi;

      try {
         SmsManager smsManager = SmsManager.getDefault();
         smsManager.sendTextMessage(phoneNo, null, message, null, null);
         Toast.makeText(getApplicationContext(), "SMS sent.",
         Toast.LENGTH_LONG).show();
      } catch (Exception e) {
         Toast.makeText(getApplicationContext(),
         "SMS faild, please try again.",
         Toast.LENGTH_LONG).show();
         e.printStackTrace();
      }
   }
   
   @Override
   
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
}