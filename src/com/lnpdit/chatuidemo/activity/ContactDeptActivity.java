package com.lnpdit.chatuidemo.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import lnpdit.stategrid.informatization.adapter.ContactDeptAdapter;
import lnpdit.stategrid.informatization.adapter.JobPlanAdapter;
import lnpdit.stategrid.informatization.data.MessengerService;
import lnpdit.stategrid.informatization.data.ToDoDB;
import lnpdit.stategrid.informatization.tools.MyLetterListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.lnpdit.chatuidemo.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ContactDeptActivity extends Activity {

	Context context;

	ListView listview;
	Button return_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_dept);

		context = this;

		viewInit();
		
		mGetContactDataThread runnable = new mGetContactDataThread();
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private void viewInit() {
		listview = (ListView) this.findViewById(R.id.listview);
		return_bt = (Button) this.findViewById(R.id.return_bt);

		return_bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	Handler threadMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			try {
				ArrayList<HashMap<String, Object>> remoteWindowItem = (ArrayList<HashMap<String, Object>>) msg.obj;
				int i = remoteWindowItem.size();
				ContactDeptAdapter topicAdapter = new ContactDeptAdapter(
						context, remoteWindowItem, R.layout.list_in_weather,
						new String[] { "Id", "Grade", "Class", "Remark" },
						new int[] { R.id.temp_tv, R.id.temp_tv, R.id.temp_tv,
								R.id.temp_tv });
				listview.setAdapter(topicAdapter);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};

	private class mGetContactDataThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ToDoDB tdd = new ToDoDB(context);
			// tdd.clearcontact();
			String curl = MessengerService.URL_WITHOUT_WSDL;
			String cmethodname = MessengerService.METHOD_GetDeptList;
			String cnamespace = MessengerService.NAMESPACE;
			String csoapaction = cnamespace + "/" + cmethodname;

			SoapObject rpc = new SoapObject(cnamespace, cmethodname);
			// rpc.addProperty("userid", userId);
			// rpc.addProperty("pagesize", 1000);
			// rpc.addProperty("pageindex", 1);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			HttpTransportSE ht = new HttpTransportSE(curl);
			ht.debug = true;

			try {
				ht.call(csoapaction, envelope);
				SoapObject contactlist = (SoapObject) envelope.bodyIn;
				ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < contactlist.getPropertyCount(); i++) {
					SoapObject soapchilds = (SoapObject) contactlist
							.getProperty(i);
					for (int j = 0; j < soapchilds.getPropertyCount(); j++) {
						SoapObject soapchildsson = (SoapObject) soapchilds
								.getProperty(j);

						String Id = soapchildsson.getProperty("Id").toString();
						String Grade = soapchildsson.getProperty("Grade")
								.toString();
						String Class = soapchildsson.getProperty("Class")
								.toString();
						String Remark = soapchildsson.getProperty("Remark")
								.toString();

						HashMap<String, Object> mapdevinfo = new HashMap<String, Object>();
						mapdevinfo.put("Id", Id);
						mapdevinfo.put("Grade", Grade);
						mapdevinfo.put("Class", Class);
						mapdevinfo.put("Remark", Remark);
						remoteWindowItem.add(mapdevinfo);

					}
				}

				Message msg = new Message();
				msg.arg1 = 0;
				msg.obj = remoteWindowItem;
				threadMessageHandler.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
