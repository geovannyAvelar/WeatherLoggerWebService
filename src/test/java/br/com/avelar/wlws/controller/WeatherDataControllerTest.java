package br.com.avelar.wlws.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import br.com.avelar.wlws.data.TemperatureUnit;
import br.com.avelar.wlws.data.WeatherData;
import br.com.avelar.wlws.data.WeatherDataService;

public class WeatherDataControllerTest {

    private Date date;
    
    private Date from;
    
    private Date to;
    
    @Before
    public void setUpBefore() {
        Calendar c = new GregorianCalendar();
        
        c.set(2016, 11, 5, 0, 0);
        date = c.getTime();
        
        c.set(2016, 11, 1, 0, 0);
        from = c.getTime();
        
        c.set(2016, 11, 11, 0, 0);
        to = c.getTime();
    }

    @Test
    public void testSaveData() throws Exception {

        WeatherDataService service = mock(WeatherDataService.class);
        
        WeatherData notSaved = new WeatherData();
        notSaved.setDate(date);
        notSaved.setTemperature(24.0);
        notSaved.setRelativeHumidity(49.0);
        notSaved.setTemperatureUnit(TemperatureUnit.CELSIUS);
        
        WeatherData saved = new WeatherData();
        saved.setId(1l);
        saved.setDate(date);
        saved.setTemperature(24.0);
        saved.setRelativeHumidity(49.0);
        saved.setTemperatureUnit(TemperatureUnit.CELSIUS);
        
        when(service.save(notSaved)).thenReturn(saved);
        
        WeatherDataController controller = new WeatherDataController(service);
        
        MockMvc mock = standaloneSetup(controller).build();
        
        mock.perform(post("/weather/save")
                          .param("date", "2016-11-5T00:00:00")
                          .param("temperature", "24")
                          .param("relativeHumidity", "57")
                          .param("temperatureUnit", "CELSIUS")
                          .param("dewPoint", "15"))
            .andExpect(status().isOk());
    }

    @Test
    public void testRetrieveData() throws Exception {
        
        WeatherData data = new WeatherData();
        data.setId(1l);
        data.setDate(date);
        data.setTemperature(24.0);
        data.setRelativeHumidity(57.0);
        data.setTemperatureUnit(TemperatureUnit.CELSIUS);
        data.setDewPoint(15.0);
        
        List<WeatherData> list = new ArrayList<WeatherData>();
        list.add(data);
        
        WeatherDataService service = mock(WeatherDataService.class);
        when(service.findByRange(from, to)).thenReturn(list);
        
        WeatherDataController controller = new WeatherDataController(service);
        
        MockMvc mock = standaloneSetup(controller).build();
        
        mock.perform(get("/weather/retrieve/2016-11-01/2016-11-11/"))
                .andExpect(status().isOk());
        
    }
    
    @Test
    public void testRetrieveLastData() throws Exception {
        WeatherData data = new WeatherData();
        data.setId(1l);
        data.setDate(date);
        data.setTemperature(24.0);
        data.setRelativeHumidity(57.0);
        data.setTemperatureUnit(TemperatureUnit.CELSIUS);
        data.setDewPoint(15.0);
        
        WeatherDataService service = mock(WeatherDataService.class);
        when(service.findLast()).thenReturn(data);
        
        WeatherDataController controller = new WeatherDataController(service);
        
        MockMvc mock = standaloneSetup(controller).build();
        
        mock.perform(get("/weather/retrieve/last/"))
                .andExpect(status().isOk());
    }
    
}
