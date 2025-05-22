package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressBook createEntry(AddressBookDTO addressBookDTO) {
        AddressBook data = new AddressBook(addressBookDTO);
        return addressBookRepository.save(data);
    }

    public List<AddressBook> getAllEntries() {
        return addressBookRepository.findAll();
    }

    public AddressBook getEntryById(int id) {
        return addressBookRepository.findById(id).orElse(null);
    }

    public AddressBook updateEntry(int id, AddressBookDTO addressBookDTO) {
        Optional<AddressBook> existing = addressBookRepository.findById(id);
        if (existing.isPresent()) {
            AddressBook data = existing.get();
            data.setName(addressBookDTO.getName());
            data.setPhoneNumber(addressBookDTO.getPhoneNumber());
            return addressBookRepository.save(data);
        }
        return null;
    }

    public boolean deleteEntry(int id) {
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
