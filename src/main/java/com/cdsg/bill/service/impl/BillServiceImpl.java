package com.cdsg.bill.service.impl;

import com.cdsg.bill.dao.BillDao;
import com.cdsg.bill.model.AddRequest;
import com.cdsg.bill.model.AddResponse;
import com.cdsg.bill.model.BillListResponse;
import com.cdsg.bill.model.BillResponse;
import com.cdsg.bill.repository.BillRepository;
import com.cdsg.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public AddResponse add(AddRequest addRequest) {
        BillDao billDao = new BillDao();
        billDao.setName(addRequest.getName());
        billDao.setDescription(addRequest.getDescription());
        billDao.setDate_time(new Date().toString());
        billDao = billRepository.save(billDao);

        AddResponse addResponse = new AddResponse();
        addResponse.setBill_id(String.valueOf(billDao.getBill_id()));
        addResponse.setName(billDao.getName());
        addResponse.setDescription(billDao.getDescription());
        addResponse.setDate_time(getCurrentDateTime());
        addResponse.setStatus_message("Bill Top up is successfully saved in the system.");

        return addResponse;
    }

    private String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(new Date());
    }

    @Override
    public BillListResponse list(String billId) {
        BillListResponse billListResponse = new BillListResponse();

        if (billId != null && billId.trim().length() > 0) {
            Optional<BillDao> billDao = billRepository.findById(Long.parseLong(billId));

            if (!billDao.isEmpty()) {
                BillDao billDaoTemp = billDao.get();
                BillResponse billResponse = new BillResponse();
                billResponse.setBill_id(String.valueOf(billDaoTemp.getBill_id()));
                billResponse.setName(billDaoTemp.getName());
                billResponse.setDescription(billDaoTemp.getDescription());
                List<BillResponse> billResponseList = new ArrayList<>();
                billResponseList.add(billResponse);

                billListResponse.setStatus_message("Transaction is successful!");
                billListResponse.setDate_time(getCurrentDateTime());
                billListResponse.setBillers(billResponseList);
            }
        } else {
            List<BillDao> billDaoList = billRepository.findAll();

            if (billDaoList != null && billDaoList.size() > 0) {
                List<BillResponse> billResponseList = new ArrayList<>();

                for (BillDao obj : billDaoList) {
                    BillResponse billResponse = new BillResponse();
                    billResponse.setBill_id(String.valueOf(obj.getBill_id()));
                    billResponse.setName(obj.getName());
                    billResponse.setDescription(obj.getDescription());

                    billResponseList.add(billResponse);
                }

                billListResponse.setStatus_message("Transaction is successful!");
                billListResponse.setDate_time(getCurrentDateTime());
                billListResponse.setBillers(billResponseList);
            }
        }

        return billListResponse;
    }

}
