package com.example.elasticsearch.elasticsearchdemo.controller;

import com.example.elasticsearch.elasticsearchdemo.model.SinhVien;
import com.example.elasticsearch.elasticsearchdemo.service.sinhvienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class sinhvienController {
    @Autowired
    private sinhvienService sinhVienService;

    @PostMapping
    public SinhVien save(@RequestBody SinhVien sinhVien) throws IOException {
        return sinhVienService.save(sinhVien);
    }

    @GetMapping("/{id}")
    public SinhVien findById(@PathVariable String id) throws IOException {
        return sinhVienService.findById(Integer.valueOf(id));
    }

    @PutMapping
    public SinhVien update(@RequestBody SinhVien sinhVien) throws IOException {
        return sinhVienService.update(sinhVien);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) throws IOException {
        return sinhVienService.deleteById(id);
    }



}
