package com.javalitterboy.ibk.service.impl;

import com.javalitterboy.ibk.repository.CommonDao;
import com.javalitterboy.ibk.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 14183
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    protected CommonDao commonDao;

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }
}
