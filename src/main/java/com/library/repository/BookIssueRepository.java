package com.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.BookIssue;
import com.library.entity.User;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
	List<BookIssue> findByUserId(Long userId);

	List<BookIssue> findByUser(User user);

}
