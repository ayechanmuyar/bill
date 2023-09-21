package com.cdsg.bill.service.impl;

import com.cdsg.bill.dao.TransactionDao;
import com.cdsg.bill.model.TransactionHistoryResponse;
import com.cdsg.bill.model.TransactionRequest;
import com.cdsg.bill.model.TransactionResponse;
import com.cdsg.bill.repository.TransactionRepository;
import com.cdsg.bill.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponse pay(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();

        if (transactionRequest.getBill_id() == 0 || transactionRequest.getBill_id() < 0
                || transactionRequest.getAmount() == 0 || transactionRequest.getAmount() < 0
                || transactionRequest.getReference_no() == null || transactionRequest.getReference_no().trim().length() == 0
                || transactionRequest.getPhone_number() == null || transactionRequest.getPhone_number().trim().length() == 0
                || !transactionRequest.getPhone_number().startsWith("959")
        ) {
            transactionResponse.setStatus_message("Transaction is not successful!");

            return transactionResponse;
        }

        if (transactionRequest.getAmount() > 100000) {
            transactionResponse.setStatus_message("Transaction is not successful!");

            return transactionResponse;
        }

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.setApi_caller(transactionRequest.getApi_caller());
        transactionDao.setReference_no(transactionRequest.getReference_no());
        transactionDao.setPhone_number(transactionRequest.getPhone_number());
        transactionDao.setAmount(transactionRequest.getAmount());
        transactionDao.setBill_id(transactionRequest.getBill_id());
        transactionDao.setTransaction_date(getCurrentDateTime());

        transactionDao = transactionRepository.save(transactionDao);

        transactionResponse.setStatus_message("Transaction is successful!");
        transactionResponse.setTransaction_id(transactionDao.getTransaction_id());
        transactionResponse.setAmount(transactionDao.getAmount());
        transactionResponse.setTransaction_date(transactionDao.getTransaction_date());
        transactionResponse.setPhone_number(transactionDao.getPhone_number());

        return transactionResponse;
    }

    private String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(new Date());
    }

    @Override
    public TransactionHistoryResponse transaction(long transactionId) {
        TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse();

        Optional<TransactionDao> transactionDao = transactionRepository.findById(transactionId);

        if (!transactionDao.isEmpty()) {
            TransactionDao transactionDaoTemp = transactionDao.get();

            transactionHistoryResponse.setApi_caller(transactionDaoTemp.getApi_caller());
            transactionHistoryResponse.setId(transactionDaoTemp.getTransaction_id());
            transactionHistoryResponse.setAmount(transactionDaoTemp.getAmount());
            transactionHistoryResponse.setReference_no(transactionDaoTemp.getReference_no());
            transactionHistoryResponse.setPhone_number(transactionDaoTemp.getPhone_number());
        }

        return transactionHistoryResponse;
    }

}
