package com.springboot.nosql.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.nosql.entity.Actor;
import com.springboot.nosql.repository.ActorRepository;
import com.springboot.nosql.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/actor")
public class ActorController {
    private final static Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    //----------MongoTemplate-------------------------

    /**
     * 保存
     * @param actor
     */
    @RequestMapping("/saveActor")
    public void saveActor(Actor actor ){
        actor.setLastUpdate(new Date());
        actorService.saveActor(actor);
    }

    /**
     * 根据主键id查
     * @param actorId
     * @return
     */
    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId ){
        return actorService.queryByActorId(actorId);
    }

    /**
     * 根据用户名查
     * @param firstName
     * @return
     */
    @RequestMapping("/queryByFirstName")
    public List<Actor> queryByFirstName(String firstName ){
        return actorService.queryByFirstName(firstName);
    }

    /**
     * 更新
     * @param Actor
     * @return
     */
    @RequestMapping("/updateActor")
    public UpdateResult updateActor(Actor Actor ){
        return actorService.updateActor(Actor);
    }

    /**
     * 删除
     * @param actorId
     * @return
     */
    @RequestMapping("/deleteByActorId")
    public DeleteResult deleteByActorId(Long actorId ){
        return actorService.deleteByActorId(actorId);
    }


    //------ActorRepository extends MongoRepository-------
    //------以下示例代码,省略了Service层----------------------
    @RequestMapping("/addActor")
    public Actor addActor(Actor actor ){
        actor.setLastUpdate(new Date());
        return actorRepository.save(actor);
    }

    @RequestMapping("/findAll")
    public List<Actor> findAll( ){
        return actorRepository.findAll();
    }

    @RequestMapping("/findById")
    public Actor findById(Long actorId){
        return actorRepository.findById(actorId).get();
    }

    @RequestMapping("/deleteById")
    public void deleteById(Long actorId){
        actorRepository.deleteById(actorId);
    }

    //MongoRepository没有update方法
}
