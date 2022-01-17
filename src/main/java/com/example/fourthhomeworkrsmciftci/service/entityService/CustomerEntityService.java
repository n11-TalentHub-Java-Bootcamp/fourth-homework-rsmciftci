package com.example.fourthhomeworkrsmciftci.service.entityService;

import com.example.fourthhomeworkrsmciftci.dao.CustomerDao;
import com.example.fourthhomeworkrsmciftci.entity.Customer;
import com.example.fourthhomeworkrsmciftci.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerDao> {

    public CustomerEntityService(CustomerDao dao) {
        super(dao);
    }


}
