package br.com.avelar.wlws.data;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WeatherDataService {
    
    @Autowired
    private WeatherDataDao dao;
    
    public WeatherData save(WeatherData data) {
        return dao.save(data);
    }
    
    public WeatherData findOne(Long id) {
        return dao.findOne(id);
    }
    
    public List<WeatherData> findByDay(Date date) {
        return dao.findByDateOrderByDateAsc(date);
    }
    
    public List<WeatherData> findByRange(Date from, Date to) {
        return dao.findByDateBetweenOrderByDateAsc(from, to);
    }
    
    public WeatherData findLast() {
        List<WeatherData> all = dao.findAll();
        return all.get(all.size() - 1);
    }
    
    public List<WeatherData> findAll() {
        return dao.findAll();
    }
    
    public void delete(WeatherData data) {
        dao.delete(data);
    }
    
}
