package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Tree;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class TreeController {

    private TreeService treeService;

    @Autowired
    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping()
    public String getHome(Model model,
                          @AuthenticationPrincipal User user) {



        final List<Tree> trees = treeService.getTrees();
        model.addAttribute("trees", trees);
        if (Objects.nonNull(user)) {
            model.addAttribute("user", user);
            model.addAttribute("userId", user.getId());
        }
        return "index";
    }

    @GetMapping("/tree-page")
    public String getAllTrees(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 5;
        Page<Tree> treePage = treeService.getTrees(page, pageSize);
        model.addAttribute("treePage", treePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", treePage.getTotalPages());

        return "parts";
    }

    @GetMapping("/trees")
    public String getBooks(Model model) {
        final List<Tree> trees = treeService.getTrees();
        model.addAttribute("trees", trees);
        return "trees";
    }

    @GetMapping("/trees/{id}")
    public String getBooks(Model model, @PathVariable Long id) {
        final Optional<Tree> treeById = treeService.getTreeById(id);
        final Tree tree = treeById.orElse(null);
        model.addAttribute("tree", tree);
        model.addAttribute("id", id);
        return "tree";
    }

    @GetMapping("/tree")
    public String formForTree(Model model) {
        return "treeserv";
    }

    @PostMapping("/add")
    public String addTree(@Valid Tree tree, @AuthenticationPrincipal User user) {
        tree.setIsGreen(true);
        tree.setUser(user);
        treeService.saveTree(tree);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String postEditTree(@PathVariable Long id,
                               Model model,
                               Tree treeBean,
                               @AuthenticationPrincipal User user) {
        final Optional<Tree> treeById = treeService.getTreeById(id);

        if (treeById.isEmpty()) {
            throw new IllegalStateException();
        }

        if (Objects.isNull(treeById.get().getUser()) || !treeById.get().getUser().getId().equals(user.getId())) {
            throw new IllegalStateException();
        }

        final Tree tree1 = treeById.get();
        tree1.setAge(treeBean.getAge());
        tree1.setName(treeBean.getName());

        treeService.saveTree(tree1);

        return "redirect:/";
    }

    @GetMapping("/trees/delete/{id}")
    public String deleteTree(Model model, @PathVariable Long id) {
        final Optional<Tree> treeById = treeService.getTreeById(id);
        final Tree tree = treeById.orElse(null);
        treeService.deleteTree(tree);
        return "redirect:/";
    }

}
