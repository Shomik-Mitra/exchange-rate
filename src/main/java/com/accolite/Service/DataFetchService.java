package com.accolite.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.accolite.Entity.AuditInfo;
import com.accolite.Entity.RatesData;
import com.accolite.Excel.Excel;
import com.accolite.Repository.AuditInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataFetchService
{
	@Autowired
	private AuditInfoRepo repo;
	String arr[]= {"AED","CAD","EUR","INR","JPY"};
	public List<AuditInfo> getApiData(String date)
	{
		FutureTask<RatesData>[] ft = new FutureTask[5];
		AuditInfo[] info=new AuditInfo[5];

		try {
			for(int i=0;i< arr.length;i++)
			{
				String str="https://api.apilayer.com/exchangerates_data/"+date+"?symbols=USD&base="+arr[i];

				Callable<RatesData> c = new DataFetchApi(str);
				ft[i] = new FutureTask<RatesData>(c);

				AuditInfo infor=new AuditInfo();
				infor.setRequest(str);
				infor.setCreate_ts(new Timestamp(new Date().getTime()));
				infor.setStatus("Request Sent");
				info[i]=repo.save(infor);

			    Thread t = new Thread(ft[i]);
			    t.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		for (int j = 0; j < arr.length; j++)
	    {
			try
			{
				AuditInfo ai=info[j];
				ai.setResponse(ft[j].get().toString());
				ai.setUpdate_ts(ft[j].get().getTimestamp());
				ai.setStatus("Response Recieved");
				new Excel(ft[j].get());
				repo.save(ai);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	    }
//		return info;
		List<AuditInfo> li=new ArrayList<>();
		for(AuditInfo i:info){
			li.add(i);
		}
		return li;
	}
}
