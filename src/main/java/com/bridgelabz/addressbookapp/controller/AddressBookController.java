package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    public ResponseEntity<AddressBook> createEntry(@RequestBody AddressBookDTO dto) {
        AddressBook data = addressBookService.createEntry(dto);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllEntries() {
        return ResponseEntity.ok(addressBookService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getEntryById(@PathVariable int id) {
        AddressBook data = addressBookService.getEntryById(id);
        if (data != null)
            return ResponseEntity.ok(data);
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateEntry(@PathVariable int id, @RequestBody AddressBookDTO dto) {
        AddressBook data = addressBookService.updateEntry(id, dto);
        if (data != null)
            return ResponseEntity.ok(data);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable int id) {
        boolean deleted = addressBookService.deleteEntry(id);
        if (deleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

}
