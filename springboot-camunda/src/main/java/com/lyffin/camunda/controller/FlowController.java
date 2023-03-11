package com.lyffin.camunda.controller;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/deploy")
    public String deplopy(){
        Deployment deploy = repositoryService.createDeployment()
                .name("部署的第二个流程") // 定义部署文件的名称
                .addClasspathResource("pisTest.bpmn") // 绑定需要部署的流程文件
                .deploy();// 部署流程
        return deploy.getId() + ":" + deploy.getName();
    }
}
