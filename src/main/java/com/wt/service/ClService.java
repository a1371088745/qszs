package com.wt.service;

import com.wt.entity.Cl;
import com.wt.util.PageUtils;

import java.util.List;

public interface ClService {
    public List<Cl> selectClass(PageUtils pageUtils,String name,String staffName);
    public Integer countClass();

     public Integer addClass(Cl cl) throws Exception;

    public Integer delClass(Cl cl);

    public Integer updateCl(Cl cl);
}
