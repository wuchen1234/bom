package zhth.bom.management.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zhth.bom.management.bom.domian.User;


public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Modifying
    @Query("update User o set o.password=:updatapassword where o.password=:password and o.username=:name")
    Integer updatapassword(@Param("name") String name,@Param("password") String password ,@Param("updatapassword") String updatapassword);
}
