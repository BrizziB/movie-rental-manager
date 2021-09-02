package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteOrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LitePhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class OrderMapper {

    @Inject private CustomerMapper customerMapper;
    @Inject private PhysicalMovieItemMapper physicalMovieItemMapper;
    @Inject private DigitalMovieItemMapper digitalMovieItemMapper;
    @Inject private PaymentProfileMapper paymentProfileMapper;

    public LiteOrderDto convert(Order o) {
        if(o == null)
            throw new MapperConversionException("The order is NULL");

        LiteOrderDto dto = new LiteOrderDto();

        dto.setCustomer( customerMapper.convert(o.getCustomer()) );
        serializePhysicalMovieItems(dto, o.getItems());
        serializeDigitalMovieItems(dto, o.getItems());
        dto.setPaymentProfile( paymentProfileMapper.convert(o.getPaymentProfile()) );
        dto.setPayment(o.getPayment());
        dto.setOrderStatus(o.getOrderStatus());
        dto.setRentalType(o.getRentalType());
        dto.setTotal(o.getTotal());
        dto.setDelivery(o.getDelivery());

        return dto;
    }

    public void transfer(LiteOrderDto dto, Order o) {
        if(dto == null)
            throw new MapperTransferException("The order DTO is NULL");
        if(o == null)
            throw new MapperTransferException("The order Entity is NULL");

        if(dto.getCustomer() != null) {
            Customer customer = ModelFactory.initCustomer();
            customerMapper.transfer(dto.getCustomer(), customer);
            o.setCustomer(customer);
        }
        deSerializePhysicalMovieItems(o, dto.getPhysicalItems());
        deSerializeDigitalMovieItems(o, dto.getDigitalItems());
        if(dto.getPaymentProfile() != null) {
            PaymentProfile pp = ModelFactory.initPaymentProfile();
            paymentProfileMapper.transfer(dto.getPaymentProfile(), pp);
            o.setPaymentProfile(pp);
        }
        if(dto.getPayment() != null)
            o.setPayment(dto.getPayment());
        if(dto.getOrderStatus() != null)
            o.setOrderStatus(dto.getOrderStatus());
        if(dto.getRentalType() != null)
            o.setRentalType(dto.getRentalType());
        if(dto.getDelivery() != null)
            o.setDelivery(dto.getDelivery());
    }

    private void serializePhysicalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
        if(items != null || items.size() > 0) {
            for (MovieItem mi : items) {
                if (mi instanceof PhysicalMovieItem) {
                    dto.getPhysicalItems()
                            .add(physicalMovieItemMapper.convert((PhysicalMovieItem) mi));
                }
            }
        }
    }

    private void serializeDigitalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
        if(items != null || items.size() > 0) {
            for (MovieItem mi : items) {
                if (mi instanceof DigitalMovieItem) {
                    dto.getDigitalItems()
                            .add(digitalMovieItemMapper.convert((DigitalMovieItem) mi));
                }
            }
        }
    }

    private void deSerializePhysicalMovieItems(Order o, List<LitePhysicalMovieItemDto> items) {
        o.getItems().clear();

        if(items != null || items.size() > 0) {
            for (LitePhysicalMovieItemDto litePMI : items) {
                // TODO in realtà qui non lo creo da zero, o sì?
                PhysicalMovieItem pmi = ModelFactory.initPhysicalMovieItem();
                physicalMovieItemMapper.transfer(litePMI, pmi);
                o.getItems().add(pmi);
            }
        }

    }

    private void deSerializeDigitalMovieItems(Order o, List<LiteDigitalMovieItemDto> items) {
        o.getItems().clear();

        if(items != null || items.size() > 0) {
            for (LiteDigitalMovieItemDto liteDMI : items) {
                // TODO in realtà qui non lo creo da zero, o sì?
                DigitalMovieItem dmi = ModelFactory.initDigitalMovieItem();
                digitalMovieItemMapper.transfer(liteDMI, dmi);
                o.getItems().add(dmi);
            }
        }
    }

}
