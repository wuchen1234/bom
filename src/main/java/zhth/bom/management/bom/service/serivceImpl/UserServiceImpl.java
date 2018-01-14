package zhth.bom.management.bom.service.serivceImpl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import zhth.bom.management.bom.domian.User;
import zhth.bom.management.bom.repository.UserRepository;
import zhth.bom.management.bom.service.UserService;
import zhth.bom.management.bom.util.change.ObjectUtils;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isexist(String username, String password) {
        boolean flag=false;
           User user= userRepository.findByUsernameAndPassword(username,password);
           if(ObjectUtils.isNotNull(user)){
               if(user.getState()=='1')
                   flag=true;
            }
        return flag;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Integer updatapassword(String name, String password , String updatapassword) {
       return userRepository.updatapassword(name,password,updatapassword);
    }
}
