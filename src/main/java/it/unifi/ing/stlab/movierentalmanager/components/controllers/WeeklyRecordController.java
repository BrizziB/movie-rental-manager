package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.WeeklyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteWeeklyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.WeeklyRecordMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.WeeklyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class WeeklyRecordController {

    @Inject private WeeklyRecordDao weeklyRecordDao;
    @Inject private WeeklyRecordMapper weeklyRecordMapper;
    private Gson gson;

    public LiteWeeklyRecordDto getWeeklyRecordById(Long id) {
        WeeklyRecord wr = weeklyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Weekly record not found")
        );
        return weeklyRecordMapper.convert(wr);
    }

    public List<LiteWeeklyRecordDto> getWeeklyRecordsByName(String name) {
        return weeklyRecordDao.retrieveWeeklyRecordsByName(name)
                .stream()
                .map(weeklyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteWeeklyRecordDto> getWeeklyRecordsByMovieId(Long id) {
        return weeklyRecordDao.retrieveWeeklyRecordsByMovieId(id)
                .stream()
                .map(weeklyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteWeeklyRecordDto> getWeeklyRecordsByMovieTitle(String title) {
        return weeklyRecordDao.retrieveWeeklyRecordsByMovieTitle(title)
                .stream()
                .map(weeklyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteWeeklyRecordDto> getWeeklyRecordsBetweenDates(Date start, Date end) {
        return weeklyRecordDao.retrieveWeeklyRecordsBetweenDates(start, end)
                              .stream()
                              .map(weeklyRecordMapper::convert)
                              .collect(Collectors.toList());
    }

    public List<LiteWeeklyRecordDto> getAllWeeklyRecords(Integer offset, Integer limit) {
        return weeklyRecordDao.findAll(offset, limit)
                              .stream()
                              .map(weeklyRecordMapper::convert)
                              .collect(Collectors.toList());
    }

    // ADD avviene in automatico

    public void updateWeeklyRecordOnDb(String json, Long id) {
        gson = new Gson();
        WeeklyRecordDto dto = gson.fromJson(json, WeeklyRecordDto.class);
        WeeklyRecord oldWeeklyRecord = weeklyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any weekly record on database")
        );
        weeklyRecordMapper.transfer(dto, oldWeeklyRecord);
        weeklyRecordDao.update(oldWeeklyRecord);
    }

    public void disableWeeklyRecordOnDb(boolean disabled, Long id) {
        WeeklyRecord wr = weeklyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any weekly record on database")
        );
        wr.setDisabled(disabled);
        weeklyRecordDao.save(wr);
    }

}
