package ru.apetrov.dao;

import org.junit.Test;
import ru.apetrov.models.UserLoginMusicTypeId;

public class MergeUserAndMusicTablesTest {

    @Test
    public void addMusicTipeToTheUserTest() {
        UserLoginMusicTypeId userLoginMusicTypeId = new UserLoginMusicTypeId();
//        userLoginMusicTypeId.setUserLogin("ssdf");
//        userLoginMusicTypeId.setMusicTypeId(2);

        userLoginMusicTypeId.setUserLogin("ssdf");
        userLoginMusicTypeId.setMusicTypeId(1);


        MergeUserAndMusicTables mergeUserMusic = new MergeUserAndMusicTables();
        mergeUserMusic.addMusicTypeToTheUser(userLoginMusicTypeId);

        for (String s : mergeUserMusic.getLoginByMusicTypeId(2)) {
            System.out.println(s);
        }
    }

}