package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteOrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LitePhysicalMovieItemDto;
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

        dto.setCustomer( customerMapper.convert(o.getCustomer()) );
        dto.setPaymentProfile( paymentProfileMapper.convert(o.getPaymentProfile()) );
        dto.setOrderStatus(o.getOrderStatus());
        dto.setRentalType(o.getRentalType());
        dto.setTotal(o.getFullTotal());
        dto.setDelivery(o.getDelivery());
        serializeDigitalMovieItems(dto, o.getItems());
        serializePhysicalMovieItems(dto, o.getItems());

        return dto;
    }

    public void transfer(LiteOrderDto dto, Order o) {
        if(dto == null)
            throw new MapperTransferException("The order DTO is NULL");
        if(o == null)
            throw new MapperTransferException("The order Entity is NULL");

        if(dto.getCustomer() != null) {
            if( customerDao.retrieveCustomersByName( dto.getCustomer().getName() ).size() != 0 )
                System.out.println("Customers with similar names do exist in database. Do you want to check them out?");
            else {
                Customer customer = ModelFactory.initCustomer();
                customerMapper.transfer(dto.getCustomer(), customer);
                customerDao.add(customer);
                o.setCustomer(customer);
            }
        }
        if(dto.getPaymentProfile() != null) {
            if( paymentProfileDao.findAll(0, 25).size() != 0) {
                System.out.println("Do you want to check out if this payment profile already exists inside the database");
            }
            else {
                PaymentProfile pp = ModelFactory.initPaymentProfile();
                paymentProfileMapper.transfer(dto.getPaymentProfile(), pp);
                paymentProfileDao.add(pp);
                o.setPaymentProfile(pp);
            }
        }
        if(dto.getOrderStatus() != null)
            o.setOrderStatus(dto.getOrderStatus());
        if(dto.getRentalType() != null)
            o.setRentalType(dto.getRentalType());
        if(dto.getDelivery() != null)
            o.setDelivery(dto.getDelivery());
        if(dto.getDigitalItems() != null)
            deSerializeDigitalMovieItems(o, dto.getDigitalItems());
        if(dto.getPhysicalItems() != null)
            deSerializePhysicalMovieItems(o, dto.getPhysicalItems());
        o.computeDiscountedTotal();
    }

    private void serializeDigitalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
        if(items != null && items.size() > 0) {
            for (MovieItem mi : items)
                if (mi instanceof DigitalMovieItem)
                    dto.getDigitalItems().add( digitalMovieItemMapper.convert( (DigitalMovieItem) mi ) );
        }
    }

    private void serializePhysicalMovieItems(LiteOrderDto dto, List<MovieItem> items) {
        if(items != null && items.size() > 0)
            for (MovieItem mi : items)
                if (mi instanceof PhysicalMovieItem)
                    dto.getPhysicalItems().add( physicalMovieItemMapper.convert( (PhysicalMovieItem) mi ) );
    }

    private void deSerializeDigitalMovieItems(Order o, List<LiteDigitalMovieItemDto> items) {
        o.getItems().clear();

        if(items != null && items.size() > 0) {
            for (LiteDigitalMovieItemDto liteDMI : items) {
                if( digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle( liteDMI.getMovie().getTitle() ) != null )
                    System.out.println("Digital items of movies with similar names do exist in database. Do you want to check them out?");
                else {
                    DigitalMovieItem dmi = ModelFactory.initDigitalMovieItem();
                    digitalMovieItemMapper.transfer(liteDMI, dmi);
                    o.getItems().add(dmi);
                }
            }
        }
    }

    private void deSerializePhysicalMovieItems(Order o, List<LitePhysicalMovieItemDto> items) {
        o.getItems().clear();

        if(items != null && items.size() > 0) {
            for (LitePhysicalMovieItemDto litePMI : items) {
                if( physicalMovieItemDao.retrievePhysicalMovieItemsByMovieTitle( litePMI.getMovie().getTitle() ) != null )
                    System.out.println("Physical items of movies with similar names do exist in database. Do you want to check them out?");
                else {
                    PhysicalMovieItem pmi = ModelFactory.initPhysicalMovieItem();
                    physicalMovieItemMapper.transfer(litePMI, pmi);
                    o.getItems().add(pmi);
                }
            }
        }

    }

}
