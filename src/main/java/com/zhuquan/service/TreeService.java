package com.zhuquan.service;

import com.zhuquan.domain.Tree;
import com.zhuquan.enums.TreeType;
import com.zhuquan.mapper.TreeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TreeService {

    // 缓存字典数据
    private static Map<TreeType ,List<Tree>>  cache = new HashMap<>();

    @Resource
    private TreeMapper treeMapper;

    /**
     * @Date 2021/3/6 16:48
     * @Desc  查询指定 类别树， 并封装成树结构
     */
    public List<Tree> queryTree(TreeType treeType){

        if (cache.containsKey(treeType)){
            return cache.get(treeType);
        }else {
            // 双重检查锁机制
            synchronized (this){
                if (cache.containsKey(treeType)){
                    return cache.get(treeType);
                }else {
                    Tree tree = new Tree();
                    tree.setType(treeType.getValue());
                    List<Tree> positions = treeMapper.select(tree);
                    List<Tree> t =   recursionTree(positions, null, 1,  treeType);
                    cache.put(treeType, t);
                    return t;
                }
            }
        }
    }

    /**
     * @Date 2021/3/6 16:44
     * @Desc  递归将tree list 封装成树结构
     * @param trees 数据集
     */
    private List<Tree> recursionTree(List<Tree> trees, Integer parentId, int depth, TreeType treeType){

        // 过滤找出符合当前 层级 的数据
        List<Tree> pos = trees.stream()
                .filter(p -> p.getLevel() == depth && (parentId == null || parentId.equals(p.getParentId())))
                .collect(Collectors.toList()) ;

        // 向数据中封装 子树
        if (depth < treeType.getMaxDepth()){
            pos.forEach(p ->p.setChildren(recursionTree(trees, p.getId(), depth+1, treeType)));
        }

        return pos;

    }
}
