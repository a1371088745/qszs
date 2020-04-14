package com.wt.service.impl;

import com.wt.entity.Cl;
import com.wt.mapper.ClMapper;
import com.wt.mapper.GivenLessonMapper;
import com.wt.mapper.StaffMapper;
import com.wt.mapper.StudentMapper;
import com.wt.service.ClService;
import com.wt.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ClServiceImpl implements ClService {
    @Autowired
    private ClMapper clMapper;
    @Autowired
    private GivenLessonMapper givenLessonMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Cl> selectClass(PageUtils pageUtils,String name,String staffName) {
        return clMapper.selectClass(pageUtils,name,staffName);
    }

    @Override
    public Integer countClass() {
        return clMapper.countClass();
    }

    @Override
    @Transactional
    public Integer addClass(Cl cl) {
        Integer integer = clMapper.selectClassId(cl.getClassName());
        if(integer!=null){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        Integer inf = clMapper.addClass(cl);
        Integer staffId = staffMapper.findStaffId(cl.getStaffName());
        return givenLessonMapper.addGivenLesson(staffId,cl.getClassId());
    }

    @Override
    @Transactional
    public Integer delClass(Cl cl) {
        Integer integer = clMapper.graduateClass(cl);
        if(integer==0){
            return -1;
        }
        studentMapper.graduate(cl.getClassId());
        return 0;
    }

    @Override
    @Transactional
    public Integer updateCl(Cl cl) {
        Integer integer = clMapper.updateCl(cl);
        Integer staffId = staffMapper.findStaffId(cl.getStaffName());
        givenLessonMapper.update(cl.getClassId(),staffId);
        return 0;
    }

}
