package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.PaymentProfileDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LitePaymentProfileDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.PaymentProfileMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.dao.PaymentProfileDao;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PaymentProfileController {

    @Inject private PaymentProfileDao paymentProfileDao;
    @Inject private PaymentProfileMapper paymentProfileMapper;
    @Inject private CustomerDao customerDao;
    private Gson gson;

    public LitePaymentProfileDto getPaymentProfileById(Long id) {
        PaymentProfile pp = paymentProfileDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Payment profile not found")
        );
        return paymentProfileMapper.convert(pp);
    }

    public List<LitePaymentProfileDto> getPaymentProfilesByCustomerId(Long id) {
        Customer customer = customerDao.fetchCustomerWithPaymentProfiles(id);
        return customer.getPaymentProfiles()
                       .stream()
                       .map(paymentProfileMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LitePaymentProfileDto> getAllPaymentProfiles(Integer offset, Integer limit, List<String> orderBy) {
        return paymentProfileDao.findAll(offset, limit, orderBy)
                .stream()
                .map(paymentProfileMapper::convert)
                .collect(Collectors.toList());
    }

    public void addPaymentProfileToDb(String json) {
        gson = new Gson();
        PaymentProfileDto dto = gson.fromJson(json, PaymentProfileDto.class);
        PaymentProfile pp = ModelFactory.initPaymentProfile();
        paymentProfileMapper.transfer(dto, pp);
        paymentProfileDao.add(pp);
    }

    public void updatePaymentProfileOnDb(String json, Long customerID) {
        gson = new Gson();
        PaymentProfileDto dto = gson.fromJson(json, PaymentProfileDto.class);
        PaymentProfile oldPP = paymentProfileDao.findById(customerID).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any payment profile on database")
        );
        paymentProfileMapper.transfer(dto, oldPP);
        paymentProfileDao.update(oldPP);
    }

    public void disablePaymentProfileOnDb(boolean disabled, Long id) {
        PaymentProfile paymentProfile = paymentProfileDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any payment profile on database")
        );
        paymentProfile.setDisabled(disabled);
        paymentProfileDao.save(paymentProfile);
    }

}
