package com.beo.infra.api


import com.beo.action.ContactService
import com.beo.model.Contact
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val contactService: ContactService
) {

    @PostMapping
    fun addContact(@RequestBody contact: Contact): ResponseEntity<Contact> {
        val saved = contactService.createContact(contact)
        return ResponseEntity.ok(saved)
    }

    @GetMapping
    fun getAllContacts(): ResponseEntity<List<Contact>> =
        ResponseEntity.ok(contactService.getAllContacts())

    @GetMapping("/{id}")
    fun getContactById(@PathVariable id: Long): ResponseEntity<Any> {
        val contact = contactService.getContactById(id)
        return if (contact != null) {
            ResponseEntity.ok(contact)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search")
    fun searchByLastName(@RequestParam lastName: String): ResponseEntity<List<Contact>> =
        ResponseEntity.ok(contactService.findByLastName(lastName))

    @PutMapping("/{id}")
    fun updateContact(
        @PathVariable id: Long,
        @RequestBody contact: Contact
    ): ResponseEntity<Contact> {
        val updated = contactService.updateContact(id, contact)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable id: Long): ResponseEntity<Void> {
        contactService.deleteContact(id)
        return ResponseEntity.noContent().build()
    }
}
