package com.example.elasticsearch.elasticsearchdemo.repo;

import com.example.elasticsearch.elasticsearchdemo.model.SinhVien;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface sinhvienRepo extends ElasticsearchRepository<SinhVien, String> {
}
