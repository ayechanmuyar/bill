package com.cdsg.bill.service;

import com.cdsg.bill.model.*;

public interface TransactionService {

    public TransactionResponse pay(TransactionRequest transactionRequest);

    public TransactionHistoryResponse transaction(long transactionId);

}
