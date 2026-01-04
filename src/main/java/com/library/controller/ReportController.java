package com.library.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.BookIssue;
import com.library.repository.BookIssueRepository;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private BookIssueRepository bookIssueRepository;

	@GetMapping("/category-percentage")
	 public ResponseEntity<Map<String, Double>> categoryReport() {

        List<BookIssue> issues = bookIssueRepository.findAll();
        long total = issues.size();

        Map<String, Double> result = issues.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getBook().getCategory(),
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                c -> total == 0 ? 0.0 : (c * 100.0) / total
                        )
                ));

        return ResponseEntity.ok(result);
    }

}
