package com.example.firstsb.controller;

import com.example.firstsb.model.TC;
import com.example.firstsb.service.TCService;
import com.example.firstsb.lib.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tc")
public class TCController {
    private final TCService tcService;
    @Autowired
    public TCController(TCService tcService) {
        this.tcService = tcService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<List<TC>>> getAllTC() {
        List<TC> tcList = tcService.findAll();
        ResponseData<List<TC>> responseData = new ResponseData<>(tcList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    public static class EnrollTC {
        public Long tid;
        public Long cid;
        public EnrollTC(Long tid, Long cid) {
            this.tid = tid;
            this.cid = cid;
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseData<TC>> saveTC(@RequestBody EnrollTC data) {
        Long tid = data.tid;
        Long cid = data.cid;

        TC tc = tcService.save(tid,cid);
        if(tc == null) {
            ResponseData<TC> responseData = new ResponseData<>(1, "开课失败");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
        ResponseData<TC> responseData = new ResponseData<>(tc);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTCById(@PathVariable int id) {
        tcService.deleteById(id);
        ResponseData<String> responseData = new ResponseData<>("成功删除教师课程,ID: " + id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }




}
