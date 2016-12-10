package br.com.avelar.wlws.data;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WeatherDataDao extends JpaRepository<WeatherData, Long> {
    public List<WeatherData> findByDateBetweenOrderByDateAsc(Date from, Date to);
    public List<WeatherData> findByDateBetweenOrderByDateDesc(Date from, Date to);
    public List<WeatherData> findByDateOrderByDateAsc(Date date);
    public List<WeatherData> findByDateOrderByDateDesc(Date date);
}
