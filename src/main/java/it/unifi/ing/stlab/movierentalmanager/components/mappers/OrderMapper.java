package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.DigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.OrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.PhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteOrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LitePhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.dao.DigitalMovieItemDao;
import it.unifi.ing.stlab.movierentalmanager.dao.PaymentProfileDao;
import it.unifi.ing.stlab.movierentalmanager.dao.PhysicalMovieItemDao;
import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.items.MovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class OrderMapper {

    @Inject private CustomerMapper customerMapper;
    @Inject private DigitalMovieItemMapper digitalMovieItemMapper;
    @Inject private PaymentProfileMapper paymentProfileMapper;
    @Inject private PhysicalMovieItemMapper physicalMovieItemMapper;

    @Inject private CustomerDao customerDao;
    @Inject private DigitalMovieItemDao digitalMovieItemDao;
    @Inject private PaymentProfileDao paymentProfileDao;
    @Inject private PhysicalMovieItemDao physicalMovieItemDao;

    public LiteOrderDto convert(Order o) {
        if(o == null)
            throw new MapperConversionException("The order is NULL");

        LiteOrderDto dto = new LiteOrderDto();

        if(o.getCustomer() != null)
            dto.setCustomer( customerMapper.convert(o.getCustomer()) );
        else
            System.out.println("Customer missing");
        if(o.getPaymentProfile() != null)
            dto.setPaymentProfile( paymentProfileMapper.convert(o.getPaymentProfile()) );
        else if(o.getCustomer() != null)
            System.out.println("Check if customer has any payment profile assigned");
        dto.setOrderStatus(o.getOrderStatus());
        dto.setRentalType(o.getRentalType());
        dto.setTotal(o.getFullTotal());
        dto.setDelivery(o.getDelivery());
//        serializeDigitalMovieItems(dto, o.getItems());
//        serializePhysicalMovieItems(dto, o.getItems());

        return dto;
    }

    public void transfer(OrderDto dto, Order o) {
        if(dto == null)
            throw new MapperTransferException("The order DTO is NULL");
        if(o == null)
            throw new MapperTransferException("The order Entity is NULL");

//        o.setCustomer(
//                    customerDao.findById( dto.getCustomerID() )
//                               .orElseThrow( () -> new IllegalArgumentException("Customer not found"))
//        );

        o.setPaymentProfile(
                paymentProfileDao.findById( dto.getPaymentProfileID() )
                        .orElseThrow( () -> new IllegalArgumentException("Payment profile not found"))
        );

        if(dto.getOrderStatus() != null)
            o.setOrderStatus(dto.getOrderStatus());
        if(dto.getRentalType() != null)
            o.setRentalType(dto.getRentalType());
        if(dto.getDelivery() != null)
            o.setDelivery(dto.getDelivery());
        if(dto.getDigitalItemsIDs() != null)
            deSerializeDigitalMovieItems(o, dto.getDigitalItemsIDs());
        if(dto.getPhysicalItemsIDs() != null)
            deSerializePhysicalMovieItems(o, dto.getPhysicalItemsIDs());
        o.computeDiscountedTotal();
    }

//    private void serializeDigitalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
//        if(items != null && items.size() > 0) {
//            for (MovieItem mi : items)
//                if (mi instanceof DigitalMovieItem)
//                    dto.getDigitalItems().add( digitalMovieItemMapper.convert( (DigitalMovieItem) mi ) );
//        }
//    }
//
//    private void serializePhysicalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
//        if(items != null && items.size() > 0)
//            for (MovieItem mi : items)
//                if (mi instanceof PhysicalMovieItem)
//                    dto.getPhysicalItems().add( physicalMovieItemMapper.convert( (PhysicalMovieItem) mi ) );
//    }

    private void deSerializeDigitalMovieItems(Order o, List<Long> digitalMovieItemIDs) {
        o.getItems().clear();

        if(digitalMovieItemIDs != null && digitalMovieItemIDs.size() > 0) {
            for (Long ID : digitalMovieItemIDs) {
                o.getItems().add(
                        digitalMovieItemDao.findById(ID)
                                           .orElseThrow( () -> new IllegalArgumentException("Digital item not found") )
                );
            }
        }
    }

    private void deSerializePhysicalMovieItems(Order o, List<Long> physicalMovieItemIDs) {
        o.getItems().clear();

        if(physicalMovieItemIDs != null && physicalMovieItemIDs.size() > 0) {
            for (Long ID : physicalMovieItemIDs) {
                o.getItems().add(
                        physicalMovieItemDao.findById(ID)
                                .orElseThrow( () -> new IllegalArgumentException("Physical item not found") )
                );
            }
        }

    }

}
