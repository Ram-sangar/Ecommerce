
package com.ecommerce.demo.Address;

import com.ecommerce.demo.ApiResponse;
import com.ecommerce.demo.Product.Product;
import com.ecommerce.demo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAddress(@RequestBody Address address){
        System.out.println(address.getUserId());
        if(! addressService.findId(address.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false," user id does not exist"),HttpStatus.NOT_FOUND);}

        if(address.getUserId()==null){
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"), HttpStatus.NOT_FOUND);
        }
        addressService.createAddress(address);
        return new ResponseEntity<>(new ApiResponse(true,"Address create successfully"),HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<Address>> listaddress(){
        List<Address> AddressList=addressService.listaddress();
        return ResponseEntity.ok(AddressList);
    }
    @GetMapping("/List/{addressId}")
    public Optional<Address> findaddressbyid (@PathVariable("addressId") long addressId){
        return addressService.findaddressbyid(addressId);
    }

    @PutMapping("/update/{AddressId}")
    public ResponseEntity<ApiResponse> updateAddress (@PathVariable("addressId") Long addressid ,@RequestBody Address address){
        if(! addressService.findId(addressid)) {
            return new ResponseEntity<>(new ApiResponse(false,"Address id does not exist"),HttpStatus.NOT_FOUND);}

        if(address.getUserId()==null && address.getAddressType()==null && address.getUnitNo()==null && address.getStreet()==null && address.getCity()==null && address.getLandmark()==null && address.getPostal()==null && address.getState() == null && address.getCountry()==null){
            return new ResponseEntity<>(new ApiResponse(false,"address id does not exist"),HttpStatus.NOT_FOUND);
        }

        System.out.println("Address_id : "+addressid);
        addressService.editaddress(addressid,address);
        return new ResponseEntity<>(new ApiResponse(true ,"Address update successfully"),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{AddressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("delete success");
    }
}

