package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.MonthlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteMonthlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.MonthlyRecordMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.MonthlyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MonthlyRecordController {

    @Inject private MonthlyRecordDao monthlyRecordDao;
    @Inject private MonthlyRecordMapper monthlyRecordMapper;
    private Gson gson;

    public LiteMonthlyRecordDto getMonthlyRecordById(Long id) {
        MonthlyRecord mr = monthlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Monthly record not found")
        );
        return monthlyRecordMapper.convert(mr);
    }

    public List<LiteMonthlyRecordDto> getMonthlyRecordsByName(String name) {
        return monthlyRecordDao.retrieveMonthlyRecordsByName(name)
                .stream()
                .map(monthlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteMonthlyRecordDto> getMonthlyRecordsByMovieId(Long id) {
        return monthlyRecordDao.retrieveMonthlyRecordsByMovieId(id)
                .stream()
                .map(monthlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteMonthlyRecordDto> getMonthlyRecordsByMovieTitle(String title) {
        return monthlyRecordDao.retrieveMonthlyRecordsByName(title)
                .stream()
                .map(monthlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteMonthlyRecordDto> getMonthlyRecordsBetweenDates(Date start, Date end) {
        return monthlyRecordDao.retrieveMonthlyRecordsBetweenDates(start, end)
                .stream()
                .map(monthlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteMonthlyRecordDto> getAllMonthlyRecords(Integer offset, Integer limit) {
        return monthlyRecordDao.findAll(offset, limit)
                .stream()
                .map(monthlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    // ADD avviene in automatico

    public void updateMonthlyRecordOnDb(String json, Long id) {
        gson = new Gson();
        MonthlyRecordDto dto = gson.fromJson(json, MonthlyRecordDto.class);
        MonthlyRecord oldMonthlyRecord = monthlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any monthly record on database")
        );
        monthlyRecordMapper.transfer(dto, oldMonthlyRecord);
        monthlyRecordDao.update(oldMonthlyRecord);
    }

    public void disableMonthlyRecordOnDb(boolean disabled, Long id) {
        MonthlyRecord mr = monthlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any monthly record on database")
        );
        mr.setDisabled(disabled);
        monthlyRecordDao.save(mr);
    }

}
