package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller for managing songs in the music catalog.
 */
@RestController
@RequestMapping("/api/v1/book")
public class LibraryRestController {

    @Autowired
    public LibraryRestController() {
    }
}
