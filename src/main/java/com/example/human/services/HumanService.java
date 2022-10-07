package com.example.human.services;
import com.example.human.dto.Human;
import java.io.File;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class HumanService {

    private List<Human> humanList;
    private static Long generatedId=0l;
    public final String targetPath="/home/swm/SWM_ALL/apache-tomcat/temp/TARGET-FOLDER/";

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

    @SneakyThrows
    public void saveFilesToHardDrive(MultipartFile[] files) {
        for (MultipartFile file : files) {
            saveFileToHardDrive(file, targetPath+ file.getOriginalFilename());
        }
    }

    @SneakyThrows
    public void saveFileToHardDrive(MultipartFile file)  {
        log.info("Save file to hardDrive");
        file.transferTo(new File(targetPath+ file.getOriginalFilename()));
    }

    @SneakyThrows
    public void saveFileToHardDrive(MultipartFile file,String path)  {
        log.info("Save file to hardDrive");
        file.transferTo(new File(path));
    }

    @SneakyThrows
    public void persistHumanAndSaveFileToHardDrive(Human human,MultipartFile file) {
        this.humanList.add(human);
        if(file==null){
            return;
        }
        saveFileToHardDrive(file, targetPath+ file.getOriginalFilename());
    }
}
