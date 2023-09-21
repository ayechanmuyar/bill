package com.cdsg.bill.controller;

import com.cdsg.bill.dao.BillDao;
import com.cdsg.bill.model.AddRequest;
import com.cdsg.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody AddRequest addRequest) throws Exception {
        return ResponseEntity.ok(billService.add(addRequest));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(name = "id", required = false) String id) throws Exception {
        return ResponseEntity.ok(billService.list(id));
    }

}
