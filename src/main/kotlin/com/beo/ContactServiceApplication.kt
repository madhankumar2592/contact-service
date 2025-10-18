package com.beo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ContactServiceApplication

fun main(args: Array<String>) {
	runApplication<ContactServiceApplication>(*args)
}
