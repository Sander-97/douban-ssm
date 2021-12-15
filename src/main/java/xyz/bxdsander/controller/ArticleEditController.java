package xyz.bxdsander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.bxdsander.service.ArticleEditService;


/**
 * @ClassName ArticleEditController
 * @Direction:
 * @Author: Sander
 * @Date 2021/12/15 16:14
 * @Version 1.0
 **/
@Controller
@RequestMapping("/ArticleEdit")
public class ArticleEditController {

    @Autowired
    private ArticleEditService articleEditService;


}
