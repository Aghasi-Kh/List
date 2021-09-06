package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id = ?1")
    User getById(@NotNull Long id);

    @Query("Delete from User where id = ?1")
    void deleteUserById(Long id);

    @Query("Select u from User u")
    List<User> users();

    @Query("select u from User u where u.mEmail = :email")
    User getByEmail(String email);


//        @Query("insert into User (mName,mLastName,mEmail,mPhoneNumber,mPassword) values(:name, :lastname,:email, :phoneNumber,:password)")
//        User save(User user);
//    @Query("update User u set u.mName = user.mName,u.mLastName = user.mLastName,u.mEmail = user.mEmail,u.mPhoneNumber = user.mPhoneNumber,u.mPassword = user.mPassword where u.mId = user.mId")
//    User update(User user);
}

