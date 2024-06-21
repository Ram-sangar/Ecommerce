
package com.ecommerce.demo.Address;
import com.ecommerce.demo.Users.UserRepository;

import com.ecommerce.demo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
     AddressRepository addressRepository;

    @Autowired
     UserRepository userRepository;

    public void createAddress(Address address) {
        System.out.println("service"+ address.getUserId());
        User user = userRepository.getById(address.getUserId());
        address.setUser(user);
        addressRepository.save(address);
    }

    public  List<Address> listaddress() {
        return addressRepository.findAll();
    }

    public Optional<Address> findaddressbyid(long addressId) {
        return addressRepository.findById(addressId);
    }

    public void editaddress(Long addressid, Address updateaddress) {

        Address address = addressRepository.getById(addressid);


        if(address.getAddressType()!=null){address.setAddressType(address.getAddressType());}

        if(address.getPostal()!=null){ address.setPostal(address.getPostal()); address.setAddressTimestamp(LocalDateTime.now());}

        if(address.getLandmark()!=null){ address.setLandmark(address.getLandmark()); address.setAddressTimestamp(LocalDateTime.now());}

        if(address.getUnitNo()!=null){address.setUnitNo(address.getUnitNo());address.setAddressTimestamp(LocalDateTime.now());}

        if(address.getStreet()!=null){address.setStreet(address.getStreet());address.setAddressTimestamp(LocalDateTime.now());}

        addressRepository.save(address);
    }


    public void deleteAddress(long addressId) {
        addressRepository.deleteById(addressId);
    }


    public boolean findId(Long userId) {
        return addressRepository.findById(userId).isPresent();
    }


}

