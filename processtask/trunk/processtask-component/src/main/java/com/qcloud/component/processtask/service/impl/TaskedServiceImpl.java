package com.qcloud.component.processtask.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.processtask.dao.TaskedDao;
import com.qcloud.component.processtask.model.Tasked;
import com.qcloud.component.processtask.service.TaskedService;
import com.qcloud.component.processtask.model.query.TaskedQuery;

@Service
public class TaskedServiceImpl implements TaskedService {

    @Autowired
    private TaskedDao           taskedDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "processtask_tasked";

    @Override
    public boolean add(Tasked tasked) {

        long id = autoIdGenerator.get(ID_KEY);
        tasked.setId(id);
        tasked.setDealTime(new Date());
        return taskedDao.add(tasked);
    }

    @Override
    public Tasked get(Long id) {

        return taskedDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return taskedDao.delete(id);
    }

    @Override
    public boolean update(Tasked tasked) {

        return taskedDao.update(tasked);
    }

    @Override
    public Page<Tasked> page(TaskedQuery query, int myApply, int start, int count) {

        return taskedDao.page(query, myApply, start, count);
    }

    public List<Tasked> listAll() {

        return taskedDao.listAll();
    }

    @Override
    public List<Tasked> listTaskedByProcessInst(String processInstId) {

        return taskedDao.listTaskedByProcessInst(processInstId);
    }

    @Override
    public Tasked getByWorkitem(String workitemId) {

        return taskedDao.getByWorkitem(workitemId);
    }
}
