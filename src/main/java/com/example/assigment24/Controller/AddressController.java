package com.example.assigment24.Controller;

import com.example.assigment24.DTO.AddressDTO;
import com.example.assigment24.Model.Address;
import com.example.assigment24.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity getAddress() {
        return ResponseEntity.status(200).body(addressService.getAllAddresses());
    }

    @PostMapping("/add")
    public ResponseEntity addAddress(@RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body("Address was added.");
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity updateAddress(@RequestBody @Valid Address address, @PathVariable Integer addressId) {
        addressService.updateAddress(addressId, address);
        return ResponseEntity.status(200).body("Address updates");
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(200).body("Address deleted");
    }
}
