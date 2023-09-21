package com.cdsg.bill.service;

import com.cdsg.bill.model.AddRequest;
import com.cdsg.bill.model.AddResponse;
import com.cdsg.bill.model.BillListResponse;

public interface BillService {

    public AddResponse add(AddRequest addRequest);

    public BillListResponse list(String billId);

}
