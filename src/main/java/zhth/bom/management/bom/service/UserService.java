package zhth.bom.management.bom.service;

import zhth.bom.management.bom.domian.User;

public interface UserService {

        boolean isexist(String username, String password);

        User findByUsername(String username);

        User save(User user);

        Integer  updatapassword(String name, String password ,String updatapassword);
}
