package com.example.firstsb.controller;

import com.example.firstsb.model.TC;
import com.example.firstsb.service.TCService;
import com.example.firstsb.lib.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin
@RestController
@RequestMapping("/tc")
public class TCController {
    private final TCService tcService;
    @Autowired
    public TCController(TCService tcService) {
        this.tcService = tcService;
    }

    @GetMapping("/all")
    public Response<List<TC>> getAllTC() {
        List<TC> tcList = tcService.findAll();
        return Response.success(tcList);
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
    public Response<TC> saveTC(@RequestBody EnrollTC data) {
        Long tid = data.tid;
        Long cid = data.cid;

        TC tc = tcService.save(tid,cid);
        if(tc == null) {
            return Response.error("开课失败");
        }
        return Response.success(tc);
    }

    @DeleteMapping ("/delete/{id}")
    public Response<String> deleteTCById(@PathVariable int id) {
        tcService.deleteById(id);
        return Response.successM("成功删除开课,id: " + id);
    }




}
