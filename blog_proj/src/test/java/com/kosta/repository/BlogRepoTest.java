package com.kosta.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kosta.entity.Article;

@DataJpaTest // JPA 관련 TEST
@AutoConfigureTestDatabase(replace=Replace.NONE) // 실제 DB 사용
public class BlogRepoTest {
	@Autowired
	private BlogRepo blogRepo;
	
	@Test
	@DisplayName("게시글 추가 테스트")
	public void saveArticleTest() {
		Article article = Article.builder().title("테스트 제목").content("테스트 내용").build();
		
		Article savedArticle = blogRepo.save(article);
		
		assertThat(savedArticle).isNotNull();
		assertThat(savedArticle.getId()).isNotNull();
		assertThat(savedArticle.getTitle()).isEqualTo("테스트 제목");
		assertThat(savedArticle.getContent()).isEqualTo("테스트 내용");
	}
	
	@Test
	@DisplayName("게시글 전체 조회 테스트")
	public void findAllTest() {
		Article article1 = Article.builder().title("테스트 제목1").content("테스트 내용1").build();
		blogRepo.save(article1);
		Article article2 = Article.builder().title("테스트 제목2").content("테스트 내용2").build();
		blogRepo.save(article2);
		
		List<Article> articleList = blogRepo.findAll();
		
		assertThat(articleList).isNotNull();
		assertThat(articleList.size()).isGreaterThanOrEqualTo(2);
		assertThat(articleList
					.stream()
					.anyMatch(article -> article.getTitle().equals("테스트 제목1")))
					.isTrue();
		assertThat(articleList
					.stream()
					.anyMatch(article -> article.getContent().equals("테스트 내용2")))
					.isTrue();
		
	}
	
	@Test
	@DisplayName("특정 게시물 조회(id)")
	public void findByIdTest() {
		Article article = Article.builder().title("테스트 새로운 제목").content("테스트 새로운 내용").build();
		Article savedArticle = blogRepo.save(article);
		
		Article foundArticle = blogRepo.findById(savedArticle.getId()).get();
		
		assertThat(foundArticle).isNotNull();
		assertThat(foundArticle.getId()).isEqualTo(savedArticle.getId());
		assertThat(foundArticle.getTitle()).isEqualTo(savedArticle.getTitle());
		assertThat(foundArticle.getContent()).isEqualTo(savedArticle.getContent());
	}
	
	@Test
	@DisplayName("특정 게시물 삭제(id)")
	public void deleteArticleTest() {
		int originSize = blogRepo.findAll().size();
		Article article = Article.builder().title("테스트 제목").content("테스트 내용").build();
		Article savedArticle = blogRepo.save(article);
		
		blogRepo.deleteById(savedArticle.getId());
		int newSize = blogRepo.findAll().size();
		
		assertThat(originSize).isEqualTo(newSize);
	}

	@Test
	@DisplayName("특정 게시물 수정(id)")
	public void updateArticleTest() {
		Article article = Article.builder().title("테스트 제목").content("테스트 내용").build();
		Article savedArticle = blogRepo.save(article);
		
		Article foundArticle = blogRepo.findById(savedArticle.getId()).get();
		foundArticle.setTitle("변경된 테스트 제목");
		foundArticle.setContent("변경된 테스트 내용");
		blogRepo.save(foundArticle);
		
		Article updatedArticle = blogRepo.findById(savedArticle.getId()).get();
		assertThat(updatedArticle.getTitle()).isEqualTo(foundArticle.getTitle());
		assertThat(updatedArticle.getContent()).isEqualTo(foundArticle.getContent());
	}

	@Test
	@DisplayName("제목 또는 내용에서 검색")
	public void searchByTitleOrContentTest() {
		Article article1 = Article.builder().title("RiceSnack").content("Crunky").build();
		blogRepo.save(article1);
		Article article2 = Article.builder().title("Choco").content("RiceSnack").build();
		blogRepo.save(article2);
		
		List<Article> result = blogRepo.findAllByTitleContainsOrContentContains("Rice", "Rice");
		assertThat(result).isNotNull();
		assertThat(result.size()).isEqualTo(2);
	}
}
