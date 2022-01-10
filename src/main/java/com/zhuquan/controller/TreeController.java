package com.zhuquan.controller;

import com.zhuquan.domain.Tree;
import com.zhuquan.enums.TreeType;
import com.zhuquan.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    /***
     * @Desc  放回岗位的树装数据
     * @Date 2021/3/5 14:21
     */
    @RequestMapping("/position")
    public List<Tree> queryTreeByPosition(){
        return treeService.queryTree(TreeType.POSITION);
    }

    /***
     * @Desc  放回岗位的树装数据
     * @Date 2021/3/5 14:21
     */
    @RequestMapping("/industry")
    public List<Tree> queryTreeByIndustry(){
        return treeService.queryTree(TreeType.INDUSTRY);
    }

    /***
     * @Desc  放回岗位的树装数据
     * @Date 2021/3/5 14:21
     */
    @RequestMapping("/area")
    public List<Tree> queryTreeByArea(){
        return treeService.queryTree(TreeType.AREA);
    }
}
