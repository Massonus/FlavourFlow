package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Tree;
import com.massonus.rccnavigator.repo.TreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreeService {

    private TreeRepo treeRepo;

    @Autowired
    public TreeService(TreeRepo treeRepo) {
        this.treeRepo = treeRepo;
    }

    public void saveTree(final Tree tree) {
        treeRepo.save(tree);
    }

    public List<Tree> getTrees() {
        return treeRepo.findAll();
    }

    public Optional<Tree> getTreeById(final long id) {
        return treeRepo.findById(id);
    }

    public void deleteTree(final Tree tree) {
        treeRepo.delete(tree);
    }

    public Page<Tree> getTrees(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return treeRepo.findAll(pageable);
    }

}
