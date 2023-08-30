package com.accolite;

import com.accolite.Entity.RatesData;
import com.accolite.Service.DataFetchApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ExchangeRatesApplicationTests
{
	@Test
	void check1()
	{
		String str="https://api.apilayer.com/exchangerates_data/2020-08-10?symbols=USD&base=AED";
		DataFetchApi ob= new DataFetchApi(str);
		try
		{
			RatesData data=ob.FetchApi();
			Float f= data.getRates().getUSD();
			Assertions.assertEquals(0.272242f,f);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	@Test
	void check2()
	{
		String str="https://api.apilayer.com/exchangerates_data/2022-08-10?symbols=USD&base=CAD";
		DataFetchApi ob= new DataFetchApi(str);
		try
		{
			RatesData data=ob.FetchApi();
			Float f= data.getRates().getUSD();
			Assertions.assertEquals(0.782687f,f);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void check3()
	{
		String str="https://api.apilayer.com/exchangerates_data/2018-01-20?symbols=USD&base=EUR";
		DataFetchApi ob= new DataFetchApi(str);
		try
		{
			RatesData data=ob.FetchApi();
			Float f= data.getRates().getUSD();
			Assertions.assertEquals(1.222637f,f);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void check4()
	{
		String str="https://api.apilayer.com/exchangerates_data/2020-09-26?symbols=USD&base=INR";
		DataFetchApi ob= new DataFetchApi(str);
		try
		{
			RatesData data=ob.FetchApi();
			Float f= data.getRates().getUSD();
			Assertions.assertEquals(0.013569f,f);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void check5()
	{
		String str="https://api.apilayer.com/exchangerates_data/2021-03-11?symbols=USD&base=JPY";
		DataFetchApi ob= new DataFetchApi(str);
		try
		{
			RatesData data=ob.FetchApi();
			Float f= data.getRates().getUSD();
			Assertions.assertEquals(0.009212f,f);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}