package com.example.firstsb.controller;

import com.example.firstsb.model.SC;
import com.example.firstsb.service.SCService;
import com.example.firstsb.lib.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sc")
public class SCController {
    private final SCService scService;
    @Autowired
    public SCController(SCService scService) {
        this.scService = scService;
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<List<SC>>> getAllSC() {
        List<SC> scList = scService.findAll();
        ResponseData<List<SC>> responseData = new ResponseData<>(scList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    public static class EnrollSC {
        public Long cid;
        public Long sid;
        public EnrollSC(Long cid, Long sid) {
            this.cid = cid;
            this.sid = sid;
        }
    }


    @PostMapping("/save")
    public ResponseEntity<ResponseData<SC>> saveSC(@RequestBody EnrollSC data) {
        if(data.cid == null || data.sid == null) {
            return new ResponseEntity<>(new ResponseData<>("参数错误"), HttpStatus.OK);
        }
        SC sc = scService.save(data.cid, data.sid);
        ResponseData<SC> responseData = new ResponseData<>(sc);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //score
    public static class ScoreSC {
        public int id;
        public int score;
        public ScoreSC(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }
    @PostMapping("/score")
    public ResponseEntity<ResponseData<SC>> scoreSC(@RequestBody ScoreSC data) {
        if(data.id <= 0 || data.score < 0 || data.score > 100) {
            return new ResponseEntity<>(new ResponseData<>("参数错误"), HttpStatus.OK);
        }
        SC sc = scService.save(data.id, data.score);
        ResponseData<SC> responseData = new ResponseData<>(sc);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteSCById(@PathVariable int id) {
        scService.deleteById(id);
        String message = "成功删除选课,ID: " + id;
        ResponseData<String> responseData = new ResponseData<>(message);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


}
