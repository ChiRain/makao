package com.qcloud.component.mvprocesstask.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.mvprocesstask.dao.TaskingDao;
import com.qcloud.component.mvprocesstask.model.Tasking;
import com.qcloud.component.mvprocesstask.model.query.TaskingQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class TaskingDaoCacheImpl implements TaskingDao {

    @Autowired
    private TaskingDao taskingDaoMysqlImpl;

    // @Autowired
    // private TaskingDao taskingDaoRedisImpl;
    @Override
    public boolean add(Tasking tasking) {

        return taskingDaoMysqlImpl.add(tasking);
    }

    @Override
    public Tasking get(Long id) {

        return taskingDaoMysqlImpl.get(id);
        // return CacheLoader.get(taskingDaoRedisImpl, taskingDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return taskingDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Tasking tasking) {

        return taskingDaoMysqlImpl.update(tasking);
    }

    @Override
    public List<Tasking> list(List<Long> idList) {

        return CacheLoader.list(taskingDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Tasking> map(Set<Long> idSet) {

        return CacheLoader.map(taskingDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Tasking> page(int start, int count) {

        return taskingDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Tasking> page(TaskingQuery query, int start, int count) {

        return taskingDaoMysqlImpl.page(query, start, count);
    }

    public List<Tasking> listAll() {

        return taskingDaoMysqlImpl.listAll();
    }

    @Override
    public Tasking getByWorkitem(String workitemId) {

        return taskingDaoMysqlImpl.getByWorkitem(workitemId);
    }

    @Override
    public Tasking getByFormInstanceId(Long formInstanceId) {

        return taskingDaoMysqlImpl.getByFormInstanceId(formInstanceId);
    }
}
