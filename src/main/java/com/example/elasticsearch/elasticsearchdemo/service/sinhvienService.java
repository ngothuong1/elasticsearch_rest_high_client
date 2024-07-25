package com.example.elasticsearch.elasticsearchdemo.service;

import com.example.elasticsearch.elasticsearchdemo.model.SinhVien;
import com.example.elasticsearch.elasticsearchdemo.repo.sinhvienRepo;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class sinhvienService {

//    @Autowired
//    private RestHighLevelClient client;

    RestHighLevelClient client;

    {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    @Autowired
    private sinhvienRepo sinhVienRepo;

    public SinhVien save(SinhVien sinhVien) throws IOException {

        IndexRequest request = new IndexRequest("sinhvien");
        request.id(sinhVien.getId());
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", sinhVien.getName());
        jsonMap.put("age", sinhVien.getAge());
        request.source(jsonMap);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        return sinhVien;
    }

    public SinhVien findById(Integer id) throws IOException {
        GetRequest getRequest = new GetRequest("sinhvien", id.toString());

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        if (getResponse.isExists()) {
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();

            SinhVien sinhVien = new SinhVien();
            sinhVien.setId(String.valueOf(id));
            sinhVien.setName((String) sourceAsMap.get("name"));
            sinhVien.setAge((Integer) sourceAsMap.get("age"));

            return sinhVien;
        } else {
            return null;
        }
    }

    public final SinhVien update(SinhVien sinhVien) throws IOException {

        UpdateRequest request = new UpdateRequest("sinhvien", sinhVien.getId());
        Map<String, Object> jsonMap = null;
        jsonMap.put("name", sinhVien.getName());
        jsonMap.put("age", sinhVien.getAge());
        request.doc(jsonMap);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        return sinhVien;
        }
    public String deleteById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("product", id);
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        return deleteResponse.getResult().name();
    }

}

