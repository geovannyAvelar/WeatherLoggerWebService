package br.com.avelar.wlws.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avelar.wlws.data.WeatherData;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WeatherStatistics {
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date firstDate;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date lastDate;
	
	private double minimumTemperature;
	
	private double maximumTemperature;
	
	private double minimumHumidity;
	
	private double maximumHumidity;
	
	private double minimumDewPoint;
	
	private double maximumDewPoint;
	
	private List<WeatherData> data;
	
	public Date getFirstDate() {
		return firstDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public double getMinimumTemperature() {
		return minimumTemperature;
	}

	public double getMaximumTemperature() {
		return maximumTemperature;
	}

	public double getMinimumHumidity() {
		return minimumHumidity;
	}

	public double getMaximumHumidity() {
		return maximumHumidity;
	}

	public List<WeatherData> getData() {
		return data;
	}
	
	public double getMinimumDewPoint() {
		return minimumDewPoint;
	}


	public double getMaximumDewPoint() {
		return maximumDewPoint;
	}

	//TODO This method need a refactor
	public void calculateStatistics(List<WeatherData> weatherDataList) {
		if(weatherDataList == null) { 
			throw new IllegalArgumentException("Cannot calculate statistics. Null list");
		}
		
		data = weatherDataList;
		List<Date> dates = new ArrayList<Date>();
		List<Double> temperatures = new ArrayList<Double>();
		List<Double> humidities = new ArrayList<Double>();
		List<Double> dewPoints = new ArrayList<Double>();
		
		for(WeatherData weatherData : weatherDataList) {
			dates.add(weatherData.getDate());
			temperatures.add(weatherData.getTemperature());
			humidities.add(weatherData.getRelativeHumidity());
			dewPoints.add(weatherData.getDewPoint());
		}
		
		if(dates.size() > 0) {
			firstDate = Collections.min(dates);
			lastDate = Collections.max(dates);
		}
		
		if(temperatures.size() > 0) {
			minimumTemperature = Collections.min(temperatures);
			maximumTemperature = Collections.max(temperatures);
		}
		
		if(humidities.size() > 0) {
			minimumHumidity = Collections.min(humidities);
			maximumHumidity = Collections.max(humidities);
		}

		if(dewPoints.size() > 0) {
			minimumDewPoint = Collections.min(dewPoints);
			maximumDewPoint = Collections.max(dewPoints);
		}

	}
	
}
