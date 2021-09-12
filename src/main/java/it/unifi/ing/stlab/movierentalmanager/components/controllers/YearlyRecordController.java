package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteYearlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.YearlyRecordMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.YearlyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class YearlyRecordController {

    @Inject private YearlyRecordDao yearlyRecordDao;
    @Inject private YearlyRecordMapper yearlyRecordMapper;
    private Gson gson;

    public LiteYearlyRecordDto getYearlyRecordById(Long id) {
        YearlyRecord yr = yearlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Yearly record not found")
        );
        return yearlyRecordMapper.convert(yr);
    }

    public List<LiteYearlyRecordDto> getYearlyRecordsByName(String name) {
        return yearlyRecordDao.retrieveYearlyRecordsByName(name)
                .stream()
                .map(yearlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteYearlyRecordDto> getYearlyRecordsByMovieId(Long id) {
        return yearlyRecordDao.retrieveYearlyRecordsByMovieId(id)
                .stream()
                .map(yearlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteYearlyRecordDto> getYearlyRecordsByMovieTitle(String title) {
        return yearlyRecordDao.retrieveYearlyRecordsByMovieTitle(title)
                .stream()
                .map(yearlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteYearlyRecordDto> getYearlyRecordsBetweenDates(Date start, Date end) {
        return yearlyRecordDao.retrieveYearlyRecordsBetweenDates(start, end)
                .stream()
                .map(yearlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteYearlyRecordDto> getAllYearlyRecords(Integer offset, Integer limit) {
        return yearlyRecordDao.findAll(offset, limit)
                .stream()
                .map(yearlyRecordMapper::convert)
                .collect(Collectors.toList());
    }

    // ADD avviene in automatico

    public void updateYearlyRecordOnDb(String json, Long id) {
        gson = new Gson();
        LiteYearlyRecordDto dto = gson.fromJson(json, LiteYearlyRecordDto.class);
        YearlyRecord oldYearlyRecord = yearlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any yearly record on database")
        );
        yearlyRecordMapper.transfer(dto, oldYearlyRecord);
        yearlyRecordDao.update(oldYearlyRecord);
    }

    public void disableYearlyRecordOnDb(boolean disabled, Long id) {
        YearlyRecord yr = yearlyRecordDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any yearly record on database")
        );
        yr.setDisabled(disabled);
        yearlyRecordDao.save(yr);
    }

}
