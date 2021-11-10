package testCI;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dao.DaoFactory;
import vn.hoapm.springbootV2.dao.UserInfoDao;
import vn.hoapm.springbootV2.entities.UserInfo;
import vn.hoapm.springbootV2.service.impl.UserInfoService;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CiTest {
    @Mock
    DaoFactory daoFactory;

    @Mock
    UserInfoDao userInfoDao;

    @InjectMocks
    UserInfoService userInfoService;

    @Before
    public void setup() {
        when(daoFactory.getUserInfoDao()).thenReturn(userInfoDao);
    }

    @Test
    public void testNotNUll() throws CommonException {
        List<UserInfo> userInfoList = userInfoService.getUserInfos(0, 10);
        assertTrue(userInfoList.size() == 0);
    }
}
