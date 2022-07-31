package com.example.human.services;
import com.example.human.dto.Human;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class HumanService {



    private List<Human> humanList;
    private static Long generatedId=0l;

    public HumanService(){
        this.humanList=new ArrayList<>();
    }


    public void addHuman(Human human) {
        generatedId++;
        human.setId(generatedId);
        humanList.add(human);
    }


    public List<Human> getAllHumans() {
        return humanList;
    }

    public Boolean removeHuman(Long id) {
        Human targetHuman = findHuman(id);
        Boolean result = humanList.remove(targetHuman);
        return result;
    }

    public Boolean updateHuman(Human human, Long id) {
        Human temp = findHuman(id);
        if(temp==null){
            return false;
        }
        temp.setName(human.getName());
        temp.setAge(human.getAge());
        return true;
    }



    public Human findHuman(Long id){
        for(Human current:humanList){
            if(id==current.getId()){
                return current;
            }
        }
        return null;
    }
}
