package com.example.firstsb.controller;

import com.example.firstsb.model.SC;
import com.example.firstsb.service.SCService;
import com.example.firstsb.lib.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin
@RestController
@RequestMapping("/sc")
public class SCController {
    private final SCService scService;
    @Autowired
    public SCController(SCService scService) {
        this.scService = scService;
    }

    @GetMapping("/all")
    public Response<List<SC>> getAllSC() {
        List<SC> scList = scService.findAll();
        return Response.success(scList);
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
    public Response<SC> saveSC(EnrollSC data) {
        if(data.cid == null || data.sid == null) {
            return Response.error("参数错误");
        }
        SC sc = scService.save(data.cid, data.sid);
        return Response.success(sc);
    }

    //score
    public static class ScoreSC {
        public int id;
        public Integer score;
        public ScoreSC(int id, Integer score) {
            this.id = id;
            this.score = score;
        }
    }
    @PostMapping("/score")
    public Response<SC> scoreSC(ScoreSC data) {

        //允许score为null，但是如果是数字，必须在0-100之间
        if(data.id <= 0 || (data.score != null && (data.score < 0 || data.score > 100))) {
            return Response.error("score必须在0-100之间,或者为null");
        }

        SC sc = scService.save(data.id, data.score);
        return Response.success(sc);
    }

    //findByCourseName
    @GetMapping("/scores/name")
    public Response<List<SC>> getSCByCourseName(@RequestParam String name) {
        if(name == null || name.isEmpty()) {
            return Response.error("name不能为空");
        }
        List<SC> scList = scService.findByCourseName(name);
        return Response.success(scList);
    }

    //findByStudentName
    @GetMapping("/scores/student")
    public Response<List<SC>> getSCByStudentName(@RequestParam String name) {
        if(name == null || name.isEmpty()) {
            return Response.error("name不能为空");
        }
        List<SC> scList = scService.findByStudentName(name);
        return Response.success(scList);
    }

    @DeleteMapping("/delete")
    public Response<String> deleteSCById(@RequestParam int id) {
        scService.deleteById(id);
        return Response.successM("成功删除选课,id: " + id);
    }


}
