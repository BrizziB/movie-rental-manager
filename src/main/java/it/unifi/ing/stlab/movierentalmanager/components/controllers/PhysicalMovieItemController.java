package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.PhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LitePhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.PhysicalMovieItemMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.PhysicalMovieItemDao;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PhysicalMovieItemController {

    @Inject private PhysicalMovieItemDao physicalMovieItemDao;
    @Inject private PhysicalMovieItemMapper physicalMovieItemMapper;
    private Gson gson;

    public LitePhysicalMovieItemDto getPhysicalMovieItemById(Long id) {
        PhysicalMovieItem pmi = physicalMovieItemDao.findById(id)
                                                    .orElseThrow(
                                                            () -> new IllegalArgumentException("Physical movie item not found")
                                                    );
        return physicalMovieItemMapper.convert(pmi);

    }

    public List<LitePhysicalMovieItemDto> getPhysicalMovieItemsByMovieTitle(String title) {
        return physicalMovieItemDao.retrievePhysicalMovieItemsByMovieTitle(title)
                                   .stream()
                                   .map(physicalMovieItemMapper::convert)
                                   .collect(Collectors.toList());
    }

    public List<LitePhysicalMovieItemDto> getPhysicalMovieItemsByMovieId(Long id) {
        return physicalMovieItemDao.retrievePhysicalMovieItemsByMovieId(id)
                .stream()
                .map(physicalMovieItemMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LitePhysicalMovieItemDto> getAllPhysicalMovieItems(Integer offset, Integer limit) {
        return physicalMovieItemDao.findAll(offset, limit)
                .stream()
                .map(physicalMovieItemMapper::convert)
                .collect(Collectors.toList());
    }

    public void addPhysicalMovieItemToDb(String json) {
        gson = new Gson();
        PhysicalMovieItemDto dto = gson.fromJson(json, PhysicalMovieItemDto.class);
        PhysicalMovieItem pmi = ModelFactory.initPhysicalMovieItem();
        physicalMovieItemMapper.transfer(dto, pmi);
        physicalMovieItemDao.add(pmi);
    }

    public void updatePhysicalMovieItemOnDb(String json, Long id) {
        gson = new Gson();
        PhysicalMovieItemDto dto = gson.fromJson(json, PhysicalMovieItemDto.class);
        PhysicalMovieItem oldPMI = physicalMovieItemDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any physical movie item on database")
        );
    }

    public void disablePhysicalMovieItemOnDb(boolean disabled, Long id) {
        PhysicalMovieItem pmi = physicalMovieItemDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any physical movie item on database")
        );
        pmi.setDisabled(disabled);
        physicalMovieItemDao.save(pmi);
    }

}
