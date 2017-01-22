package br.com.avelar.wlws.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Entity
@Table(name = "WEATHER_DATA")
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WeatherData {
    
    @Id
    @GeneratedValue
    @Column(name = "DATA_ID")
    private Long id;
    
    @Column(name = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private Date date;
    
    @Column(name  = "TEMPERATURE")
    @NotNull
    private Double temperature;
    
    @Column(name = "TEMPERATURE_UNIT")
    @NotNull
    private TemperatureUnit temperatureUnit;
    
    @Column(name = "RELATIVE_HUMIDITY")
    @Min(0)
    @Max(100)
    private Double relativeHumidity;
    
    @Column(name = "DEW_POINT")
    @NotNull
    private Double dewPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    
    public TemperatureUnit getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public boolean equals(Object object) {
        if(!(object instanceof WeatherData)) return false;
        
        WeatherData weatherData = (WeatherData) object;
        
        if(!weatherData.getDate().equals(this.date)) return false;
        return true;
    }
    
    public String toString() {
        return date.toString();
    }
    
}
