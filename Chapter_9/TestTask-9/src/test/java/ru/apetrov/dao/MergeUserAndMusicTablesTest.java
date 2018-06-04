package ru.apetrov.dao;

import org.junit.Test;
import ru.apetrov.models.UserLoginMusicTypeId;

public class MergeUserAndMusicTablesTest {

    @Test
    public void addMusicTipeToTheUserTest() {
        UserLoginMusicTypeId userLoginMusicTypeId = new UserLoginMusicTypeId();
        userLoginMusicTypeId.setUserLogin("ssdf");
        userLoginMusicTypeId.setMusicTypeId(2);
        MergeUserAndMusicTables mergeUserAndMusicTables = new MergeUserAndMusicTables();
        mergeUserAndMusicTables.addMusicTypeToTheUser(userLoginMusicTypeId);

        for (String s : mergeUserAndMusicTables.getLoginByMusicTypeId(2)) {
            System.out.println(s);
        }
    }

}