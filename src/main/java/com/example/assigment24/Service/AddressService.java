package com.example.assigment24.Service;

import com.example.assigment24.DTO.AddressDTO;
import com.example.assigment24.Exception.APIException;
import com.example.assigment24.Model.Address;
import com.example.assigment24.Model.Teacher;
import com.example.assigment24.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherService teacherService;


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherService.getTeacherById(addressDTO.getTeacherId());

        if (teacher == null)
            throw new APIException("Teacher with id: " + addressDTO.getTeacherId() + " was not found");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getBuildingNumber(), addressDTO.getStreet(), teacher);
        addressRepository.save(address);
    }

    public void updateAddress(Integer addressId, Address address) {
        Address storedAddress = addressRepository.findById(addressId).orElse(null);

        if (storedAddress == null)
            throw new APIException("Address not found");

        address.setId(addressId);
        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);
        if (address == null)
            throw new APIException("Address not found");

        addressRepository.deleteById(addressId);
    }
}