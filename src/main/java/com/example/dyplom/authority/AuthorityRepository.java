package com.example.dyplom.authority;
import com.example.dyplom.enums.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AuthorityRepository  extends JpaRepository <Authority, Long> {
    Authority findByName(AuthorityName name);
}
