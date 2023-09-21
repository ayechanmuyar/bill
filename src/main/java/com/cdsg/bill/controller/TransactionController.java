package com.cdsg.bill.controller;

import com.cdsg.bill.model.AddRequest;
import com.cdsg.bill.model.TransactionRequest;
import com.cdsg.bill.model.TransactionResponse;
import com.cdsg.bill.service.BillService;
import com.cdsg.bill.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<?> pay(@RequestBody TransactionRequest transactionRequest) throws Exception {
        return ResponseEntity.ok(transactionService.pay(transactionRequest));
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<?> transaction(@RequestParam(name = "id", required = true) String id) throws Exception {
        return ResponseEntity.ok(transactionService.transaction(Long.parseLong(id)));
    }

}
