package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.DigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.DigitalMovieItemMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.DigitalMovieItemDao;
import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class DigitalMovieItemController {

    @Inject private DigitalMovieItemDao digitalMovieItemDao;
    @Inject private DigitalMovieItemMapper digitalMovieItemMapper;
    private Gson gson;

    public LiteDigitalMovieItemDto getDigitalMovieItemById(Long id) {
        DigitalMovieItem dmi = digitalMovieItemDao.findById(id)
                                                  .orElseThrow(
                                                          () -> new IllegalArgumentException("Digital movie item not found")
                                                  );
        return digitalMovieItemMapper.convert(dmi);
    }

    public List<LiteDigitalMovieItemDto> getDigitalMovieItemsByMovieTitle(String title) {
        return digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle(title)
                .stream()
                .map(digitalMovieItemMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteDigitalMovieItemDto> getDigitalMovieItemsByMovieId(Long id) {
        return digitalMovieItemDao.retrieveDigitalMovieItemsByMovieId(id)
                .stream()
                .map(digitalMovieItemMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteDigitalMovieItemDto> getAllDigitalMovieItems(Integer offset, Integer limit, List<String> orderBy) {
        return digitalMovieItemDao.findAll(offset, limit, orderBy)
                .stream()
                .map(digitalMovieItemMapper::convert)
                .collect(Collectors.toList());
    }

    public void addDigitalMovieItemToDb(String json) {
        gson = new Gson();
        DigitalMovieItemDto dto = gson.fromJson(json, DigitalMovieItemDto.class);
        DigitalMovieItem dmi = ModelFactory.initDigitalMovieItem();
        digitalMovieItemMapper.transfer(dto, dmi);
        digitalMovieItemDao.add(dmi);
    }

    public void updateDigitalMovieItemOnDb(String json, Long id) {
        gson = new Gson();
        DigitalMovieItemDto dto = gson.fromJson(json, DigitalMovieItemDto.class);
        DigitalMovieItem oldPMI = digitalMovieItemDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any digital movie item on database")
        );
    }

    public void disableDigitalMovieItemOnDb(boolean disabled, Long id) {
        DigitalMovieItem pmi = digitalMovieItemDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any digital movie item on database")
        );
        pmi.setDisabled(disabled);
        digitalMovieItemDao.save(pmi);
    }

}
