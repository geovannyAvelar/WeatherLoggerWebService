package br.com.avelar.wlws.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.avelar.wlws.data.WeatherData;
import br.com.avelar.wlws.data.WeatherDataService;
import br.com.avelar.wlws.data.WeatherDataValidator;

@RestController
@RequestMapping("/weather")
public class WeatherDataController {
    
    private WeatherDataService weatherDataService;
    
    @Autowired
    private WeatherDataValidator weatherDataValidator;
    
    @Autowired
    public WeatherDataController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(weatherDataValidator);
    }
    
	@CrossOrigin
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Void> saveData(@Valid WeatherData data, Errors errors) {
        
        if(errors.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        
        weatherDataService.save(data);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/retrieve/last", method = RequestMethod.GET)
    public ResponseEntity<WeatherData> retrieveData() {
        WeatherData lastData = weatherDataService.findLast();
        
        if(lastData == null) {
            return new ResponseEntity<WeatherData>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<WeatherData>(lastData, HttpStatus.OK);
        
    }
    
    @CrossOrigin
    @RequestMapping(value = "/retrieve/{day}", method = RequestMethod.GET)
    public ResponseEntity<List<WeatherData>> retrieveData(@DateTimeFormat(pattern="yyyy-MM-dd") 
                                                            @PathVariable Date day) {
        List<WeatherData> data = weatherDataService.findByDay(day);
        return new ResponseEntity<List<WeatherData>>(data, HttpStatus.OK);
    }
    
    
    @CrossOrigin
    @RequestMapping(value = "/retrieve/{from}/{to}", method = RequestMethod.GET)
    public ResponseEntity<List<WeatherData>> retrieveData(@DateTimeFormat(pattern="yyyy-MM-dd") 
                                                            @PathVariable  Date from,
                                                           @DateTimeFormat(pattern="yyyy-MM-dd")
                                                            @PathVariable  Date to) {
        List<WeatherData> weatherDataList = weatherDataService.findByRange(from, to);
        return new ResponseEntity<List<WeatherData>>(weatherDataList, HttpStatus.OK);
    }
    
}
